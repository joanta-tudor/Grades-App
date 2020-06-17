package test;

import domain.Student;

import static junit.framework.TestCase.assertEquals;

class StudentTest {

    private final Student s1 = new Student(1L,"Manole Alexandru",224,"mair2457@scs.ubbcluj.ro","Serban Camelia");


    @org.junit.jupiter.api.Test
    void getNume() {
        assertEquals("Manole Alexandru",s1.getNume());
    }

    @org.junit.jupiter.api.Test
    void setNume() {
        s1.setNume("Mihoc George");
        assertEquals("Mihoc George",s1.getNume());
    }

    @org.junit.jupiter.api.Test
    void getGrupa() {
        assertEquals(224,s1.getGrupa());
    }

    @org.junit.jupiter.api.Test
    void setGrupa() {
        s1.setGrupa(214);
        assertEquals(214,s1.getGrupa());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        assertEquals("mair2457@scs.ubbcluj.ro",s1.getEmail());
    }

    @org.junit.jupiter.api.Test
    void setEmail() {
        s1.setEmail("mair2458@scs.ubbcluj.ro");
        assertEquals("mair2458@scs.ubbcluj.ro",s1.getEmail());
    }

    @org.junit.jupiter.api.Test
    void getProf() {
        assertEquals("Serban Camelia",s1.getProf());
    }

    @org.junit.jupiter.api.Test
    void setProf() {
        s1.setProf("Czibula Istvan");
        assertEquals("Czibula Istvan",s1.getProf());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("1." +
                " nume = " + "Manole Alexandru" + " | " +
                " grupa = " + "224" + " | " +
                " email =" + "mair2457@scs.ubbcluj.ro" + " | " +
                " cadruDidactic = " + "Serban Camelia",s1.toString());
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Student s2 = new Student(2L,"Pascalau Tudor",712,"email","prof");
        Student s3 = new Student(3L,"Bele Paul;",712,"email","prof");
        Student s4 = new Student(4L,"Manole Alexandru",712,"email","prof");
        if(s1.compareTo(s2) >= 0)
            assertEquals(1,0);
        if(s1.compareTo(s4) != 0)
            assertEquals(1,0);
        if(s1.compareTo(s3) <= 0)
            assertEquals(1,0);
    }
}