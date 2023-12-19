package org.launchcode.buildMyApptriangle.models;

public class Contract {

    private int id;

    private boolean completion;

    //import name from Employee
    private String employeeName;

    //import address from Customer
    private String workSite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public Contract(int id, boolean completion, String employeeName, String workSite) {
        this.id = id;
        this.completion = completion;
        this.employeeName = employeeName;
        this.workSite = workSite;
    }
}
