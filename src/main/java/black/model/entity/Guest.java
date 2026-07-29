package black.model.entity;

import black.model.entity.enums.GuestType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class Guest {
    private int id;
    private String firstName;
    private String lastName;
    private GuestType guestType;
    private String passportNumber;
    private String nationalID;
    private LocalDate birthDate;

    public String getDisplayGuest(){
        String identity = (passportNumber != null && !passportNumber.isBlank()
        ? "Passport Number: " + passportNumber
        : "National ID: " + nationalID);

        return firstName + " " + lastName + "/ " + identity;
    }
}
