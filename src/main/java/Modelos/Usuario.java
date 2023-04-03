package Modelos;
public class Usuario {
    private int id;
    private String username, pssword,email;
    public Usuario(){

    }
    public Usuario(int id, String username, String email, String pssword) {
        this.id = id;
        this.username = username;
        this.pssword = pssword;
        this.email = email;
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

    public String getPssword() {
        return pssword;
    }

    public void setPssword(String pssword) {
        this.pssword = pssword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " | " + username + " | " + email + " | " + pssword + " | ";
    }
}