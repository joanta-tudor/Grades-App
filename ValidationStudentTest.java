package test;

import domain.Student;
import validators.ValidationStudent;

import static org.junit.jupiter.api.Assertions.*;

class ValidationStudentTest {

    private ValidationStudent valid = new ValidationStudent();
    private final Student st1 = new Student(1L,"Manole Alexandru",224,"mail","prof");
    private final Student st2 = new Student(2L,"",224,"","");


    @org.junit.jupiter.api.Test
    void validate() {
        try{
            valid.validate(st2);
            assertEquals(1,0);
        }catch (Exception ex) {
            assertEquals("Numele nu poate fi vid " +
                    "Emailul nu poate fi vid " +
                    "Profesorul nu poate fi vid ",ex.getMessage());
        }

        try{
            valid.validate(st1);
        }
        catch (Exception ex) {
            assertEquals(1,0);
        }
    }
}