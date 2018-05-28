package sample;

public class User{
    private String firstname;
    private String lastname;
    private String gender;
    private String groupname;
    private String email;
    private String phone;
    private String id;
    private String dob;

    public User(String firstname, String lastname, String gender, String groupname, String email, String phone, String id, String dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.groupname = groupname;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.dob = dob;
    }

    public User(String firstname, String lastname, String gender, String groupname, String email, String phone, String dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.groupname = groupname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public User(){};


    public User(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGenger() {
        return gender;
    }

    public void setGenger(String genger) {
        this.gender = genger;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  "Users: ["+id + "\t" + firstname + "\t" + lastname + "\t" + email + "\t" + phone+"\t"+gender+"\t"+groupname+"\t"+dob+"]";
    }
}
