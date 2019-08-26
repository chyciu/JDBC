package zadanieDrugie;

public class Ksiazka {

    private Integer id;
    private String tytul;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook() {
        return tytul;
    }

    public void setBook(String tytul) {
        this.tytul = tytul;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                '}';
    }


}
