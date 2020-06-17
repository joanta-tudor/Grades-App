package test;

import domain.Tema;
import validators.ValidationTema;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTemaTest {

    private ValidationTema valid = new ValidationTema();
    private final Tema t1 = new Tema(1L,"desc",-1,2);
    private final Tema t2 = new Tema(2L,"desc",7,2);
    private final Tema t3 = new Tema(3L,"desc",1,2);
    private final Tema t4 = new Tema(4L,"",9,11);

    private final Tema t5 = new Tema(1L,"desc",9,12);

    @org.junit.jupiter.api.Test
    void validate() {
        try{
            valid.validate(t1);
            assertEquals(1,0);
        }catch(Exception ex){
            assertEquals("StartWeek sau Deadline nu este in timpul semestrului!",ex.getMessage());
        }

        try{
            valid.validate(t2);
            assertEquals(1,0);
        }catch(Exception ex){
            assertEquals("StartWeek nu poate fi dupa deadline!",ex.getMessage());
        }

        try{
            valid.validate(t3);
            assertEquals(1,0);
        }catch(Exception ex){
            assertEquals("A trecut sapt " + "2" +"!",ex.getMessage());
        }

        try{
            valid.validate(t4);
            assertEquals(1,0);
        }catch(Exception ex){
            assertEquals("Desc nu poate fi vida!",ex.getMessage());
        }

        try{
            valid.validate(t5);
        }catch (Exception ex){
            assertEquals(1,0);
        }
    }
}