package persistence;

import domain.Nota;
import domain.Pair;
import validators.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class InFileNotaRepository extends InMemoryRepository<Pair, Nota> {

    String fileName;

    public InFileNotaRepository(Validator<Nota> validator,String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData(){
        Path path= Paths.get(fileName);
        try {
            List<String> lines= Files.readAllLines(path);
            lines.forEach(linie->{
                String[] atr=linie.split(";");
                //line->entity
                LocalDateTime d1 = LocalDateTime.of(Integer.parseInt(atr[2]),Integer.parseInt(atr[3]),Integer.parseInt(atr[4]),0,0);
                Nota n =new Nota(Long.parseLong(atr[0]),Long.parseLong(atr[1]),d1,Float.parseFloat(atr[5]),atr[6]);
                super.save(n);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(){
        try{
            File file= new File(fileName);
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            for (Nota n  : super.findAll()) {
                //entity->line
                int an = n.getData().getYear();
                int luna = n.getData().getMonthValue();
                int zi = n.getData().getDayOfMonth();
                writer.write(n.getId().getFirst()+";"+ n.getId().getSecond()+";"+an +";"+luna+";"+zi+";"+n.getValue() +";"+n.getFeedback());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Nota save(Nota elem) {
        Nota st=super.save(elem);
        writeData();
        return st;
    }

    @Override
    public Nota delete(Pair aLong) {
        Nota st= super.delete(aLong);
        writeData();
        return st;
    }

    @Override
    public Nota update(Nota entity) {
        Nota st= super.update(entity);
        writeData();
        return st;
    }

}
