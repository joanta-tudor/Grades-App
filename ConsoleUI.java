package ui;

import business.ServiceNota;
import business.ServiceStudent;
import business.ServiceTema;
import domain.Nota;
import domain.NotaDTO;
import domain.Student;
import domain.Tema;
import persistence.*;
import validators.ValidationNota;
import validators.ValidationStudent;
import validators.ValidationTema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private ValidationStudent validS = new ValidationStudent();
    private ValidationTema validT = new ValidationTema();
    private ValidationNota validN = new ValidationNota();

    //private InFileStudentRepository repoS = new InFileStudentRepository(validS, "C:\\Users\\Tudor\\Desktop\\Facultate\\MAP\\Lab4\\Lab4\\src\\student.txt");
    private InXMLStudentRepository repoS = new InXMLStudentRepository(validS, "C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\student.xml");

    //private InFileTemaRepository repoT = new InFileTemaRepository(validT, "C:\\Users\\Tudor\\Desktop\\Facultate\\MAP\\Lab4\\Lab4\\src\\tema.txt");
    private InXMLTemaRepository repoT = new InXMLTemaRepository(validT, "C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\tema.xml");

   // private InFileNotaRepository repoN = new InFileNotaRepository(validN, "C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\note.txt");
   private InXMLNotaRepository repoN = new InXMLNotaRepository(validN, "C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\nota.xml");

    private ServiceStudent servS = new ServiceStudent(repoS);
    private ServiceTema servT = new ServiceTema(repoT);
    private ServiceNota servN = new ServiceNota(repoN, repoS, repoT);

    private static int citire() {
        try {
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception ex) {
            return -1;
        }
    }

    private static void meniu() {
        System.out.println("\nMENU");
        System.out.println("1.Adaugati Student");
        System.out.println("2.Stergeti  Student");
        System.out.println("3.Updatati Student");
        System.out.println("4.Printati Studentii");
        System.out.println("---------------------");
        System.out.println("5.Adaugati Tema");
        System.out.println("6.Stergeti  Tema");
        System.out.println("7.Updatati Tema");
        System.out.println("8.Printati Temele");
        System.out.println("---------------------");
        System.out.println("9.Adaugati Nota");
        System.out.println("10.Stergeti Nota");
        System.out.println("11.Updatati Nota");
        System.out.println("12.Printati Notele");
        System.out.println("13.Adaugati Nota - Intarziere");
        System.out.println("---------------------");
        System.out.println("14.Filtrare studenti - grupa");
        System.out.println("15.Filtrare studenti - tema");
        System.out.println("16.Filtrare studenti - tema,prof");
        System.out.println("17.Filtrare note - tema,saptamana");

        System.out.println("0.EXIT");
    }

    private void UIaddStudent() {
        String nume, email, cadruDidactic;
        int grupa;
        Long id;
        Student returned;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            System.out.println("Introduceti nume:");
            scanner = new Scanner(System.in);
            nume = scanner.nextLine();
            System.out.println("Introduceti grupa:");
            scanner = new Scanner(System.in);
            grupa = Integer.parseInt(scanner.nextLine());
            System.out.println("Introduceti email:");
            scanner = new Scanner(System.in);
            email = scanner.nextLine();
            System.out.println("Introduceti cadrul didactic:");
            scanner = new Scanner(System.in);
            cadruDidactic = scanner.nextLine();

            returned = servS.addStudent(id, nume, grupa, email, cadruDidactic);

            if (returned == null)
                System.out.println("Student adaugat cu succes!");
            else
                System.out.println("ID-ul exista deja in repo!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIdeleteStudent() {
        Long id;
        Student returned;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            returned = servN.deleteStudent(id);
            if (returned == null)
                System.out.println("Nu exista id-ul dat!");
            else
                System.out.println("Studentul adaugat cu succes!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIupdateStudent() {
        int grupa;
        Long id;
        Student returned;
        String nume, email, cadruDidactic;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            System.out.println("Introduceti nume:");
            scanner = new Scanner(System.in);
            nume = scanner.nextLine();
            System.out.println("Introduceti grupa:");
            scanner = new Scanner(System.in);
            grupa = Integer.parseInt(scanner.nextLine());
            System.out.println("Introduceti email:");
            scanner = new Scanner(System.in);
            email = scanner.nextLine();
            System.out.println("Introduceti cadrul didactic:");
            scanner = new Scanner(System.in);
            cadruDidactic = scanner.nextLine();

            returned = servN.updateStudent(id, nume, grupa, email, cadruDidactic);

            if (returned == null)
                System.out.println("Student modificat!");
            else
                System.out.println("ID-ul exista deja in repo!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIprintStudent() {
        repoS.findAll().forEach(System.out::println);
    }

    private void UIaddTema() {
        String desc;
        int deadlineWeek;
        Long id;
        Tema returned;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            System.out.println("Introduceti desc:");
            scanner = new Scanner(System.in);
            desc = scanner.nextLine();
            System.out.println("Introduceti deadlineWeek:");
            scanner = new Scanner(System.in);
            deadlineWeek = Integer.parseInt(scanner.nextLine());

            returned = servT.addTema(id,desc,deadlineWeek);

            if (returned == null)
                System.out.println("Tema adaugata cu succes!");
            else
                System.out.println("ID-ul exista deja in repo!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIdeleteTema() {
        Tema returned;
        Long id;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            returned = servN.deleteTema(id);
            if (returned == null)
                System.out.println("Nu exista id-ul dat!");
            else
                System.out.println("Studentul adaugat cu succes!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIupdateTema() {
        int deadlineWeek,currentWeek;
        Long id;
        Tema returned;
        String desc;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            id = Long.parseLong(scanner.nextLine());
            System.out.println("Introduceti desc:");
            scanner = new Scanner(System.in);
            desc = scanner.nextLine();
            System.out.println("Introduceti currentWeek:");
            scanner = new Scanner(System.in);
            currentWeek = Integer.parseInt(scanner.nextLine());
            System.out.println("Introduceti deadlineWeek:");
            scanner = new Scanner(System.in);
            deadlineWeek = Integer.parseInt(scanner.nextLine());

            returned = servT.updateTema(id,desc,currentWeek,deadlineWeek);

            if (returned == null)
                System.out.println("Tema a fost modificata!");
            else
                System.out.println("ID-ul exista deja in repo!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIprintTema() {
        repoT.findAll().forEach(System.out::println);
    }

    private void UIaddNota() {
        Long idStudent,idTema;
        Float motivare;
        String feedback;
        Float value;
        Nota returned;
        try {
            System.out.println("Introduceti id student:");
            Scanner scanner = new Scanner(System.in);
            idStudent = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti id tema:");
            scanner = new Scanner(System.in);
            idTema = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti nota:");
            scanner = new Scanner(System.in);
            value = Float.parseFloat(scanner.nextLine());

            System.out.println("Introduceti feedback:");
            scanner = new Scanner(System.in);
            feedback = scanner.nextLine();

            if(servN.verificaST(idStudent,idTema) == 0) {
                System.out.println("ID student sau ID tema invalid");
                return;
            }

            if(servN.verificaSapt(idTema) == 0) {
                //Verificare motivare
                System.out.println("Are elevul motivare?Cate saptamani?");
                scanner = new Scanner(System.in);
                motivare = Float.parseFloat(scanner.nextLine());
                value = servN.calcNota(motivare,idTema,value);
            }

            returned = servN.addNota(idStudent,idTema,feedback,value);

            if (returned == null)
                System.out.println("Tema adaugata cu succes!");
            else
                System.out.println("ID-ul exista deja in repo!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIdeleteNota() {
        Nota returned;
        Long idS,idT;
        try {
            System.out.println("Introduceti id:");
            Scanner scanner = new Scanner(System.in);
            idS = Long.parseLong(scanner.nextLine());
            scanner = new Scanner(System.in);
            idT = Long.parseLong(scanner.nextLine());
            returned = servN.deleteNota(idS,idT);
            if (returned == null)
                System.out.println("Nu exista id-ul dat!");
            else
                System.out.println("Studentul sters cu succes!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UIupdateNota() {
            Long idStudent,idTema;
            String feedback;
            Float value;
            Nota returned;
            try {
                System.out.println("Introduceti id student:");
                Scanner scanner = new Scanner(System.in);
                idStudent = Long.parseLong(scanner.nextLine());

                System.out.println("Introduceti id tema:");
                scanner = new Scanner(System.in);
                idTema = Long.parseLong(scanner.nextLine());

                System.out.println("Introduceti nota:");
                scanner = new Scanner(System.in);
                value = Float.parseFloat(scanner.nextLine());

                System.out.println("Introduceti feedback:");
                scanner = new Scanner(System.in);
                feedback = scanner.nextLine();

                returned = servN.updateNota(idStudent, idTema, feedback, value);

                if (returned == null)
                    System.out.println("Nota a fost modificata!");
                else
                    System.out.println("ID-ul exista deja in repo!");
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    private void UIprintNota() {
        repoN.findAll().forEach(System.out::println);
    }

    private void UIaddNotaIntarziere() {
        Long idStudent, idTema;
        int an, zi, luna;
        float motivare;
        String feedback;
        Float value;
        Nota returned;
        try {
            System.out.println("Introduceti id student:");
            Scanner scanner = new Scanner(System.in);
            idStudent = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti id tema:");
            scanner = new Scanner(System.in);
            idTema = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti nota:");
            scanner = new Scanner(System.in);
            value = Float.parseFloat(scanner.nextLine());

            System.out.println("Introduceti feedback:");
            scanner = new Scanner(System.in);
            feedback = scanner.nextLine();

            System.out.println("Introduceti anul predarii");
            scanner = new Scanner(System.in);
            an = Integer.parseInt(scanner.nextLine());

            System.out.println("Introduceti luna predarii");
            scanner = new Scanner(System.in);
            luna = Integer.parseInt(scanner.nextLine());

            System.out.println("Introduceti zi predarii");
            scanner = new Scanner(System.in);
            zi = Integer.parseInt(scanner.nextLine());

            if (servN.verificaST(idStudent, idTema) == 0) {
                System.out.println("ID student sau ID tema invalid");
                return;
            }

            if (servN.verificaSaptIntarziere(idTema, an, luna, zi) == 0) {
                //Verificare motivare
                System.out.println("Are elevul motivare?Cate saptamani?");
                scanner = new Scanner(System.in);
                motivare = Float.parseFloat(scanner.nextLine());
                value = servN.calcNotaIntarziere(motivare, idTema, value, an, luna, zi);
            }

            returned = servN.addNota(idStudent, idTema, feedback, value);

            if (returned == null)
                System.out.println("Tema adaugata cu succes!");
            else
                System.out.println("ID-ul exista deja in repo!");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void consStart() {
        int cmd;
        meniu();
        while (true) {
            cmd = citire();
            switch (cmd) {
                case 1:
                    UIaddStudent();
                    break;
                case 2:
                    UIdeleteStudent();
                    break;
                case 3:
                    UIupdateStudent();
                    break;
                case 4:
                    UIprintStudent();
                    break;
                case 5:
                    UIaddTema();
                    break;
                case 6:
                    UIdeleteTema();
                    break;
                case 7:
                    UIupdateTema();
                    break;
                case 8:
                    UIprintTema();
                    break;
                case 9:
                    UIaddNota();
                    break;
                case 10:
                    UIdeleteNota();
                case 11:
                    UIupdateNota();
                case 12:
                    UIprintNota();
                    break;
                case 13:
                    UIaddNotaIntarziere();
                    break;
                case 14:
                    uiRaport1();
                    break;
                case 15:
                    uiRaport2();
                    break;
                case 16:
                    uiRaport3();
                    break;
                case 17:
                    uiReport4();
                    break;
                case 0:
                    System.out.println("EXIT");
                    return;
                default:
                    System.out.println("Comanda invalida");
            }
        }
    }

    private void uiRaport1() {
        try{
            System.out.println("Introduceti grupa studentului:");
            Scanner scanner = new Scanner(System.in);
            int grupa = Integer.parseInt(scanner.nextLine());
            List<Student> rez = servS.report1(grupa);
            for (Student s:rez) {
                System.out.println(s);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void uiRaport2() {
        try {
            System.out.println("Introduceti id-ul temei:");
            Scanner scanner = new Scanner(System.in);
            long id_tema = Long.parseLong(scanner.nextLine());
            List<Nota> rez = servN.report2(id_tema);
            for (Nota n:rez) {
                System.out.println(servN.getStudent(n.getIdStudent()));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void uiRaport3() {
        try{
            System.out.println("Introduceti id-ul temei:");
            Scanner scanner = new Scanner(System.in);
            long id_tema = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti cadrul didactic:");
            scanner = new Scanner(System.in);
            String cadruDidactic = scanner.nextLine();

            List<NotaDTO> rez = servN.report3(id_tema,cadruDidactic);
            for (NotaDTO n:rez) {
                System.out.println(n.getNumeElev());
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void uiReport4() {
        try {
            System.out.println("Introduceti id-ul temei:");
            Scanner scanner = new Scanner(System.in);
            long id_tema = Long.parseLong(scanner.nextLine());

            System.out.println("Introduceti saptamana:");
            scanner = new Scanner(System.in);
            int week = Integer.parseInt(scanner.nextLine());

            List<NotaDTO> rez = servN.report4(id_tema,week);
            for (NotaDTO n:rez) {
                System.out.println(n.getNumeElev());
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }




}
