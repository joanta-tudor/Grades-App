package test;


import domain.Student;
import org.junit.Before;
import persistence.InMemoryRepository;
import validators.ValidationStudent;
import validators.Validator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

class InMemoryRepositoryTest{

    private Validator valid = new ValidationStudent();
    private InMemoryRepository<Long, Student> repo = new InMemoryRepository(valid);

    private Student s2 = new Student(2L,"Pascalau Tudor",712,"email","prof");
    private Student s3 = new Student(3L,"Bele Paul",111,"email","prof");
    private Student s1 = new Student(1L,"Manole Alexandru",224,"email","prof");

    @Before
    public void setUp() {
        repo.save(s1);
        repo.save(s2);
        repo.save(s3);
    }

    @org.junit.jupiter.api.Test
    void findOne() {
        setUp();
        Student stNou = repo.findOne(1L);
        assertEquals("Manole Alexandru",stNou.getNume());
        assertNull(repo.findOne(5L));
    }


    @org.junit.jupiter.api.Test
    void findAll() {
        setUp();
        int size = 0;
        for(Student s : repo.findAll())
            size++;
        assertEquals(3,size);
    }

    @org.junit.jupiter.api.Test
    void save() {
        setUp();
        Student s4 = new Student(4L,"Lujerdeanu Vlad",224,"email","prof");
        assertNull(repo.save(s4));
        int size = 0;
        for(Student s : repo.findAll())
            size++;
        assertEquals(4,size);

        Student s5 = new Student(4L,"Mihawk George",1,"mail","prof");
        Student s6 = repo.save(s5);
        assertEquals("Mihawk George",s6.getNume());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        setUp();
        Student s4 = repo.delete(3L);
        assertEquals("Bele Paul",s4.getNume());

        int size = 0;
        for(Student s : repo.findAll())
            size++;
        assertEquals(2,size);

        assertNull(repo.delete(5L));

    }

    @org.junit.jupiter.api.Test
    void update() {
        setUp();
        Student s4 = new Student(1L,"Lujerdeanu Vlad",224,"email","prof");
        assertNull(repo.update(s4));
        assertEquals("Lujerdeanu Vlad",repo.findOne(1L).getNume());

        Student s5 = new Student(5L,"Camasarean Horia",224,"email","prof");
        Student s6 = repo.update(s5);
        assertEquals("Camasarean Horia",s6.getNume());

    }
}