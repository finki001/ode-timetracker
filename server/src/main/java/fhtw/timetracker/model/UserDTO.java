package fhtw.timetracker.model;

public class UserDTO {

    private final int id;
    private final String login;
    private final String firstname;
    private final String lastname;
    private final Role role;

    public UserDTO(int id, String login, String firstname, String lastname, Role role) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Role getRole() {
        return role;
    }
}
