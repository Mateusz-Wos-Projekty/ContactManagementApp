package CMA.API.DTO;

import java.time.LocalDate;
public class ContactDTO {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String address;
    private final int mobileNumber;
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
    public ContactDTO(int id, String firstName, String lastName, LocalDate dateOfBirth, String address, int mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }
}
