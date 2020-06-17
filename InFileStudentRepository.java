package persistence;

import domain.Student;
import validators.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InFileStudentRepository extends InMemoryRepository<Long, Student>  {
    private String fileName;

    public InFileStudentRepository(Validator validator, String fileName) {
        super(validator);
        this.fileName= fileName;
        loadData();
    }

    private void loadData(){
        Path path= Paths.get(fileName);
        try {
            List<String> lines= Files.readAllLines(path);
            lines.forEach(linie->{
                String[] atr=linie.split(";");
                /*
                for(String st : atr)
                    System.out.println(st);

                 */
                Student s =new Student(Long.parseLong(atr[0]),atr[1],Integer.parseInt(atr[2]),atr[3],atr[4]);
                super.save(s);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(){
        try{
            File file= new File(fileName);
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            for (Student st  : super.findAll()) {
                //entity->line
                writer.write(st.getId()+";"+st.getNume()+";"+st.getGrupa()+";"+st.getEmail()+";"+st.getProf()+"\n");

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student save(Student elem) {
        Student st=super.save(elem);
        writeData();
        return st;
    }

    @Override
    public Student delete(Long aLong) {
        Student st= super.delete(aLong);
        writeData();
        return st;
    }

    @Override
    public Student update(Student entity) {
        Student st= super.update(entity);
        writeData();
        return st;
    }
}
