package fhtw.timetracker.model;

public class SignUpUser {
    private String login;
    private String firstname;
    private String lastname;
    private Role role;
    private String passwordPlain;

    public SignUpUser(String login, String firstname, String lastname, Role role, String passwordPlain) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.passwordPlain = passwordPlain;
    }

    public SignUpUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain;
    }
}
