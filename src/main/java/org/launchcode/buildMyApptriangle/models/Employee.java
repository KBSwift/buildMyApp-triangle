package org.launchcode.buildMyApptriangle.models;

public class Employee {

    private int id;

    private String name;

    private String email;

    private String phoneNum;

    private boolean availability;

//import address for site form Customer

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isAvailability() {
        return availability;
    }



    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Employee(int id, String name, String email, String phoneNum, boolean availability) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.availability = availability;
    }

}
