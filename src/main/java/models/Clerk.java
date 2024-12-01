package models;

public class Clerk {
    private String firstName;
    private String lastName;
    private String shiftStartTime;
    private String shiftEndTime;

    public Clerk(String firstName, String lastName, String shiftStartTime, String shiftEndTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(String shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public String getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(String shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
        System.out.println("Shift end time is set to " + shiftEndTime);
    }

}