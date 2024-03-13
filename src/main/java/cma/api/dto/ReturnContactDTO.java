package cma.api.dto;

import java.time.LocalDate;

public class ReturnContactDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private Integer mobileNumber;

    public ReturnContactDTO() {
        id = 0;
        firstName = "";
        lastName = "";
        dateOfBirth = null;
        address = "";
        mobileNumber = 0;
    }

    public ReturnContactDTO(Integer id, String firstName, String lastName, LocalDate dateOfBirth, String address, Integer mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }


    public void setId(Integer id) {
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

    public void setMobileNumber(Integer mobileNumber) {
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

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
