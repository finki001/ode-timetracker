package fhtw.timetracker.model;

public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String passwordHash;

    public User() {

    }

    public User(String username, String firstname, String lastname, String passwordPlain) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        // TODO: hash password
        this.passwordHash = passwordPlain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
