package persistence;

import domain.Student;
import domain.Tema;
import validators.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InFileTemaRepository extends  InMemoryRepository<Long, Tema> {

    String fileName;

    public InFileTemaRepository(Validator<Tema> validator,String fileName) {
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
                Tema t =new Tema(Long.parseLong(atr[0]),atr[1],Integer.parseInt(atr[2]),Integer.parseInt(atr[3]));
                super.save(t);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(){
        try{
            File file= new File(fileName);
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            for (Tema t  : super.findAll()) {
                //entity->line
                writer.write(t.getId()+";"+t.getDesc()+";"+t.getStartWeek()+";"+t.getDeadlineWeek());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tema save(Tema elem) {
        Tema st=super.save(elem);
        writeData();
        return st;
    }

    @Override
    public Tema delete(Long aLong) {
        Tema st= super.delete(aLong);
        writeData();
        return st;
    }

    @Override
    public Tema update(Tema entity) {
        Tema st= super.update(entity);
        writeData();
        return st;
    }
}
