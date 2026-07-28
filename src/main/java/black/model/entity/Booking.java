package black.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class Booking {
    private int id;
    private Guest guest;
    private Room room;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int partySize;

    @Override
    public String toString(){
        return "Booking No." + id +
               " | Guest: " + guest.getDisplayGuest() +
               " | " + room.getDisplayRoom() +
               " | Number of Guests: " + partySize +
               "\nArrival Date: " + arrivalDate +
               " | Departure Date: " + departureDate;
    }
}
