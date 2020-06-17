package business;

import domain.*;
import persistence.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceNota extends ServiceEntity<Pair, Nota> {

    private LocalDate startSemesterAux= LocalDate.of(2020, Month.FEBRUARY,24);
    private LocalDateTime d2 = LocalDateTime.of(startSemesterAux, LocalTime.MIDNIGHT);
    private LocalDateTime d1 = LocalDateTime.now();


    //private InMemoryRepository<Long, Student> repoS;
    private InXMLStudentRepository repoS;


    //private InMemoryRepository<Long, Tema> repoT;
    private InXMLTemaRepository repoT;

    private StructuraSemestru ss;

    private void initSemestru() {
        int semestru = 2;
        if(d1.compareTo(d2) < 0)
            semestru = 1;
        ss = new StructuraSemestru(d1.getYear(),semestru);
    }

    /*
    public ServiceNota(InMemoryRepository<Pair, Nota> repo, InMemoryRepository<Long, Student> repoS, InMemoryRepository<Long, Tema> repoT) {
        super(repo);
        this.repoS = repoS;
        this.repoT = repoT;
    }
     */
    //in  functie de tema
    public List<Nota> report2(Long idTema) {
        List<Nota> all = new ArrayList<>();
        for(Nota s : this.getRepo().findAll())
            all.add(s);

        Predicate<Nota> p = x-> x.getIdTema().equals(idTema);

        return all.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    private List<NotaDTO> init() {
        List<NotaDTO> all = new ArrayList<>();

        for(Nota n : this.getRepo().findAll()) {
            Student s = repoS.findOne(n.getIdStudent());
            Tema t = repoT.findOne(n.getIdTema());
            all.add(new NotaDTO(s.getId(),t.getId(),s.getNume(),s.getProf(),n.getFeedback(),n.getValue(),ss.getWeek(n.getData())));
        }
        return all;
    }

    //in functie de tema si rpofesor
    public List<NotaDTO> report3(Long idTema,String cadruDidactic) {
        List<NotaDTO> all = init();

        Predicate<NotaDTO> p1 = x-> x.getId().getFirst().equals(idTema);
        Predicate<NotaDTO> p2 = x-> x.getProf().equals(cadruDidactic);

        Predicate<NotaDTO> p = p1.and(p2);

        return all.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    //in functie de tema si saptamana
    public List<NotaDTO> report4(Long idTema,int week) {
        List<NotaDTO> all = init();

        Predicate<NotaDTO> p1 = x-> x.getId().getFirst().equals(idTema);
        Predicate<NotaDTO> p2 = x-> x.getSapt() == week;

        Predicate<NotaDTO> p = p1.and(p2);

        return all.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    public Student getStudent(Long idStudent) {
        return repoS.findOne(idStudent);
    }

    public ServiceNota(InXMLNotaRepository repo, InXMLStudentRepository repoS, InXMLTemaRepository repoT) {
        super(repo);
        this.repoS = repoS;
        this.repoT = repoT;
        initSemestru();
    }

    public int verificaST(Long idStudent,Long idTema) {
        Student stReturned = repoS.findOne(idStudent);
        Tema tReturned = repoT.findOne(idTema);
        if (stReturned == null || tReturned == null)
            return 0;
        return 1;
    }

    public int verificaSapt(Long idTema) {

        Tema tReturned = repoT.findOne(idTema);
        LocalDateTime currentDate = LocalDateTime.now();

        int currentWeek = ss.getWeek(currentDate);
        int deadlineWeek = tReturned.getDeadlineWeek();

        if(currentWeek != deadlineWeek)
            return 0;
        return 1;

    }

    public Float calcNota(Float motivare,Long idTema,Float value) {

        LocalDateTime currentDate = LocalDateTime.now();
        return calcNotaComun(motivare,idTema,value,currentDate);
    }

    public int verificaSaptIntarziere(Long idTema,int an,int luna, int zi) {

        Tema tReturned = repoT.findOne(idTema);
        LocalDateTime currentDate = LocalDateTime.of(an,luna,zi,0,0);

        int currentWeek = ss.getWeek(currentDate);
        int deadlineWeek = tReturned.getDeadlineWeek();

        if(currentWeek != deadlineWeek)
            return 0;
        return 1;

    }

    private float calcNotaComun(Float motivare,Long idTema,Float value,LocalDateTime currentDate) {
        Tema t = repoT.findOne(idTema);
        //Nota n = new Nota(idStudent, idTema, currentDate, value, feedback);

        int currentWeek = ss.getWeek(currentDate);
        int deadlineWeek = t.getDeadlineWeek();


        if(currentWeek - deadlineWeek - motivare > 2 )
            return 1f;
        else if(currentWeek - deadlineWeek - motivare > 0) {
            return value - currentWeek + deadlineWeek + motivare;
        }
        else
            return value;
    }

    public Float calcNotaIntarziere(Float motivare,Long idTema,Float value,int an,int luna, int zi) {

        LocalDateTime currentDate = LocalDateTime.of(an,luna,zi,0,0);
        return calcNotaComun(motivare,idTema,value,currentDate);
    }


    public Nota addNota(Long idStudent, Long idTema, String feedback, Float value) {
        LocalDateTime currentDate = LocalDateTime.now();
        Tema t = repoT.findOne(idTema);
        //Nota n = new Nota(idStudent, idTema, currentDate, value, feedback);

        int currentWeek = ss.getWeek(currentDate);
        int deadlineWeek = t.getDeadlineWeek();

        try{
                Nota n = new Nota(idStudent, idTema, currentDate, value, feedback);
                Nota returned = this.getRepo().save(n);
                if (returned == null)
                    studentFile(n,currentWeek,deadlineWeek,feedback);
            this.notifyObservers();
                return returned;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Nota deleteNota(Long idStudent, Long idTema) {
        Pair id = new Pair(idStudent, idTema);
        return this.getRepo().delete(id);
    }

    public Student deleteStudent(Long idStudent) {
        Student returned = this.repoS.delete(idStudent);
        if(returned != null) {
            for(Nota n : this.getRepo().findAll()) {
                if(n.getId().getFirst() == idStudent)
                    this.getRepo().delete(new Pair(idStudent,n.getId().getSecond()));
            }
            this.fileDelete(returned);
        }
        return  returned;
    }

    public Student updateStudent(Long id,String nume,int grupa,String email,String cadruDidactic){
        try{
            Student returned = repoS.findOne(id);
            Student st = new Student(id,nume,grupa,email,cadruDidactic);
            Student rez = this.repoS.update(st);
            if(!returned.getNume().equals(nume)) {
                this.fileDelete(returned);
                this.getRepo().findAll().forEach(n -> {
                    if(n.getId().getFirst() == returned.getId()) {
                        Tema t = repoT.findOne(n.getIdTema());
                        int weekPredat = ss.getWeek(n.getData());
                        this.studentFile(n,weekPredat,t.getDeadlineWeek(),n.getFeedback());
                    }
                });
            }
            return rez;
        }catch(Exception ex) {
            throw ex;
        }
    }

    public Tema deleteTema(Long idTema) {
        Tema returned = this.repoT.delete(idTema);
        if(returned != null) {
            for(Nota n : this.getRepo().findAll()) {
                if(n.getId().getSecond() == idTema)
                    this.getRepo().delete(new Pair(n.getId().getFirst(),idTema));
            }
        }
        return returned;
    }

    public Nota updateNota(Long idStudent, Long idTema, String feedback, Float value) {
        LocalDateTime currentDate = LocalDateTime.now();
        Nota n = new Nota(idStudent, idTema, currentDate, value, feedback);
        return  this.getRepo().update(n);
    }

    private void studentFile (Nota n, int gradeWeek, int deadlineWeek, String feedback) {

        String path = "C:\\Users\\Tudor\\Desktop\\Studenti";

        /*
        for(Student s: repoS.findAll()){
            if(s.getId() == n.getIdStudent())
                nume=s.getNume();
        }
        */

        String nume = repoS.findOne(n.getId().getFirst()).getNume();

        path = path +"\\" + nume +".txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(path, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        out.print("ID-ul temei:");
        out.println(n.getIdTema());
        //
        out.print("Nota:");
        out.println(n.getValue());
        //
        out.print("Predata in saptamana:");
        out.println(gradeWeek);
        //
        out.print("Deadline:");
        out.println(deadlineWeek);
        //
        out.print("Feedback:");
        out.println(feedback);
        out.println();
        out.close();
    }

    private void fileDelete(Student s){

            String  path = "C:\\Users\\Tudor\\Desktop\\Studenti";
            path = path + "\\" + s.getNume() + ".txt";

            File file = new File(path);
            try {
                Files.deleteIfExists(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
