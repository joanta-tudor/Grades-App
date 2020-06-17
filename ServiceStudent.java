package business;

import domain.Student;
import persistence.CrudRepository;
import persistence.InFileStudentRepository;
import persistence.InMemoryRepository;
import persistence.InXMLStudentRepository;
import validators.ValidationException;
import validators.Validator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceStudent extends ServiceEntity<Long, Student> {

    /*
    public ServiceStudent(InMemoryRepository<Long, Student> repo) {
        super(repo);
    }
     */
    public ServiceStudent(InXMLStudentRepository repo) {
        super(repo);
    }

    //in functie de grupa
    public List<Student> report1(int grupa) {
        List<Student> all = new ArrayList<>();
        for(Student s : this.getRepo().findAll())
            all.add(s);

        Predicate<Student> p = x-> x.getGrupa() == grupa;

        return all.stream()
                .filter(p)
                .collect(Collectors.toList());

    }

    public Student addStudent(Long id,String nume,int grupa,String email,String cadruDidactic) {
        try {
            Student st = new Student(id, nume, grupa, email, cadruDidactic);
            Student returned = this.getRepo().save(st);
            this.notifyObservers();
            return returned;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteStudent(Long id) {
        try{
            Student returned = this.getRepo().delete(id);
            this.notifyObservers();
            if(returned == null)
                System.out.println("Nu exista ID-ul in repo!");
            else
                System.out.println("Elemetul sters cu succes!");
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Student updateStudent(Long id,String nume,int grupa,String email,String cadruDidactic){
        try{
            Student st = new Student(id,nume,grupa,email,cadruDidactic);
            Student returned = this.getRepo().update(st);
            this.notifyObservers();
            return returned;
        }catch(Exception ex) {
            throw ex;
        }
    }


}
