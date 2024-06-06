package pe.edu.utp.blackdog.model;

public class Administrator {
    private long admin_id;
    private String full_name;
    private String email;
    private String pwd;

    public Administrator() {
    }

    public Administrator(long admin_id, String full_name, String email, String pwd) {
        this.admin_id = admin_id;
        this.full_name = full_name;
        this.email = email;
        this.pwd = pwd;
    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "admin_id=" + admin_id +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
