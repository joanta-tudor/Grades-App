package domain;

public class StudentMedie extends Entity<Long> {
    private String nume;
    private Float medie;

    public StudentMedie(Long id,String nume, Float medie) {
        this.setId(id);
        this.nume = nume;
        this.medie=medie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Float getMedie(){return medie;}

    public void setMedie(Float medie){this.medie=medie;}
}
