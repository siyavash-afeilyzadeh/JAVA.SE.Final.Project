package black.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class Booking {
    private Guest guest;
    private Room room;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int partySize;
}
