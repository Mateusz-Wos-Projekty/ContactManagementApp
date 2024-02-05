package cma.api.dto;

import java.time.LocalDate;
public class ContactDTO {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private int mobileNumber;
    public ContactDTO(){
        id = 0;
        firstName = "";
        lastName = "";
        dateOfBirth = null;
        address = "";
        mobileNumber = 0;
    }
    public ContactDTO(int id, String firstName, String lastName, LocalDate dateOfBirth, String address, int mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public int getMobileNumber() {
        return mobileNumber;
    }
}