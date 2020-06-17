package test;

import domain.Tema;

import static junit.framework.TestCase.assertEquals;

class TemaTest {

    private final Tema t1 = new Tema(1L,"FileRepository",7,12);


    @org.junit.jupiter.api.Test
    void getDesc() {
        assertEquals("FileRepository",t1.getDesc());
    }

    @org.junit.jupiter.api.Test
    void setDesc() {
        t1.setDesc("GUI");
        assertEquals("GUI",t1.getDesc());
    }

    @org.junit.jupiter.api.Test
    void getStartWeek() {
        assertEquals(7,t1.getStartWeek());
    }

    @org.junit.jupiter.api.Test
    void setStartWeek() {
        t1.setStartWeek(8);
        assertEquals(8,t1.getStartWeek());
    }

    @org.junit.jupiter.api.Test
    void getDeadlineWeek() {
        assertEquals(12,t1.getDeadlineWeek());
    }

    @org.junit.jupiter.api.Test
    void setDeadlineWeek() {
        t1.setDeadlineWeek(13);
        assertEquals(13,t1.getDeadlineWeek());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals(  "1." +
                " desc = " + "FileRepository" + " | " +
                " startWeek = " + "7" + " | " +
                " deadlineWeek =" + "12",t1.toString());
    }
}