package pe.edu.utp.blackdog.model;

public class Client {
    private long client_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private String pwd;

    public Client() {
    }

    public Client(Builder builder) {
    }

    //INNER CLASS: BUILDER
    public static class Builder{
        private long client_id;
        private String first_name;
        private String last_name;
        private String phone_number;
        private String email;
        private String pwd;

        public Builder(long client_id, String first_name, String last_name, String phone_number, String email, String pwd){
            this.client_id = client_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone_number = phone_number;
            this.email = email;
            this.pwd = pwd;
        }

        public Client build(){return new Client(this);}
    }

    // GETTERS
    public long getClient_id() {
        return client_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
