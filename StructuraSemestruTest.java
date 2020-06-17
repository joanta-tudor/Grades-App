package test;

import domain.StructuraSemestru;

import java.time.LocalDateTime;
import java.time.Month;

import static junit.framework.TestCase.assertEquals;

class StructuraSemestruTest {
    private final LocalDateTime d1 = LocalDateTime.of(2020, Month.MAY,30,0,0);
    private final LocalDateTime d2 = LocalDateTime.of(2019, Month.NOVEMBER,11,0,0);
    private final LocalDateTime d3 = LocalDateTime.of(2020,Month.JANUARY,7,0,0);
    private final LocalDateTime d4 = LocalDateTime.of(2020,Month.FEBRUARY,23,0,0);
    private final LocalDateTime d5 = LocalDateTime.of(2020,Month.JUNE,22,0,0);


    private final StructuraSemestru s1 = new StructuraSemestru(2019,1);
    private final StructuraSemestru s2 = new StructuraSemestru(2019,2);


    @org.junit.jupiter.api.Test
    void getWeek() {
        assertEquals(13,s2.getWeek(d1));
        assertEquals(7,s1.getWeek(d2));
        assertEquals(13,s1.getWeek(d3));
        assertEquals(15,s1.getWeek(d4));
        assertEquals(-1,s2.getWeek(d4));
        assertEquals(15,s2.getWeek(d5));
    }
}