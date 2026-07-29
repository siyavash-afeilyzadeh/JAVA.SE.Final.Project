package black.model.entity;

import black.model.entity.enums.GuestType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Guest {
    private int id;
    private String firstName;
    private String lastName;
    private GuestType guestType;
    private String passportNumber;
    private String nationalID;
    private LocalDate birthDate;

    public String getDisplayGuest() {
        String identity;
        if (passportNumber != null && !passportNumber.isBlank()) {
            identity = "Passport Number: " + passportNumber;
        } else if (nationalID != null && !nationalID.isBlank()) {
            identity = "National ID: " + nationalID;
        } else {
            identity = "No Identification Registered";
        }

        return firstName + " " + lastName + "/ " + identity;
    }

    public Guest(int id, String firstName, String lastName, GuestType guestType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestType = guestType;
    }
}
