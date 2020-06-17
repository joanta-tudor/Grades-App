package test;

import domain.Nota;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotaTest  {

    private LocalDateTime d1 = LocalDateTime.of(2019,11,11,0,0);
    private LocalDateTime d2 = LocalDateTime.of(2019,11,12,0,0);


    private final Nota n = new Nota(1L,1L,d1,10,"Foarte bine!");


    @Test
    void getIdStudent() {
        assertEquals(1L,n.getIdStudent());
    }

    @Test
    void setIdStudent() {
        n.setIdStudent(2L);
        assertEquals(2L,n.getIdStudent());
    }

    @Test
    void getIdTema() {
        assertEquals(1L,n.getIdTema());
    }

    @Test
    void setIdTema() {
        n.setIdTema(2L);
        assertEquals(2L,n.getIdTema());
    }

    @Test
    void getData() {
        assertEquals(d1,n.getData());
    }

    @Test
    void setData() {
        n.setData(d2);
        assertEquals(d2,n.getData());
    }

    @Test
    void getValue() {
        assertEquals(10,n.getValue());
    }

    @Test
    void setValue() {
        n.setValue(9);
        assertEquals(9,n.getValue());
    }

    @Test
    void getFeedback() {
        assertEquals("Foarte bine!",n.getFeedback());
    }

    @Test
    void setFeedback() {
        n.setFeedback("Foarte rau!");
        assertEquals("Foarte rau!",n.getFeedback());
    }

    @Test
    void testToString() {
        assertEquals(n.getId().getFirst() + " " +n.getId().getSecond() + " | " +
                " feedback = " +  n.getFeedback() + " | " +
                " nota = " + n.getValue(),n.toString());
    }
}