package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.SimpleStyleableStringProperty;

public class UserDetails
{
    private StringProperty firstname;

    private StringProperty lastname;
    private StringProperty gender;
    private StringProperty groupname;
    private StringProperty email;
    private StringProperty phone;
    private StringProperty id;
    private StringProperty dob;

    public UserDetails(String id,String firstname, String lastname, String gender, String groupname, String email, String phone,  String dob) {
        this.id = new SimpleStringProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname =new SimpleStringProperty(lastname);
        this.gender =new SimpleStringProperty(gender);
        this.groupname = new SimpleStringProperty(groupname);
        this.email =new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.dob = new SimpleStringProperty(dob);
    }


    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getGroupname() {
        return groupname.get();
    }

    public StringProperty groupnameProperty() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname.set(groupname);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDob() {
        return dob.get();
    }

    public StringProperty dobProperty() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }
}
