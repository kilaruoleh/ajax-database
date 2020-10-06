package co.inventorsoft.models;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String codeWord;
    private String favFilm;

    public User(Integer id, String firstName, String lastName, String codeWord, String favFilm) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.codeWord = codeWord;
        this.favFilm = favFilm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }

    public String getFavFilm() {
        return favFilm;
    }

    public void setFavFilm(String favFilm) {
        this.favFilm = favFilm;
    }
}