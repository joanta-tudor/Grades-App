package business;

import domain.StructuraSemestru;
import domain.Tema;
import persistence.CrudRepository;
import persistence.InFileTemaRepository;
import persistence.InMemoryRepository;
import persistence.InXMLTemaRepository;
import validators.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class ServiceTema extends ServiceEntity<Long, Tema> {

    private LocalDate startSemesterAux= LocalDate.of(2020, Month.FEBRUARY,24);
    private LocalDateTime d2 = LocalDateTime.of(startSemesterAux, LocalTime.MIDNIGHT);
    private LocalDateTime d1 = LocalDateTime.now();
    private StructuraSemestru ss;

    void initSemestru() {
        int semestru = 2;
        if(d1.compareTo(d2) < 0)
            semestru = 1;
        ss = new StructuraSemestru(d1.getYear(),semestru);
    }

    /*
    public ServiceTema(InMemoryRepository<Long, Tema> repo) {
        super(repo);
        initSemestru();
    }

     */

    public ServiceTema(InXMLTemaRepository repo) {
        super(repo);
        initSemestru();
    }

    public Tema addTema(Long id, String desc, int deadlineWeek) {
        try {
            int currentWeek = ss.getWeek(LocalDateTime.now());
            Tema t = new Tema(id,desc,currentWeek,deadlineWeek);
            this.notifyObservers();
            return this.getRepo().save(t);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteTema(Long id) {
        try{
            Tema returned = this.getRepo().delete(id);
            this.notifyObservers();
            if(returned == null)
                System.out.println("Elemetul sters cu succes!");
            else
                System.out.println("Nu exista ID-ul in repo!");
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Tema updateTema(Long id,String desc,int currentWeek,int deadlineWeek){
        try{
            Tema st = new Tema(id,desc,currentWeek,deadlineWeek);
            Tema returned = this.getRepo().update(st);
            this.notifyObservers();
            return returned;
        }catch(Exception ex) {
            throw ex;
        }
    }
}
