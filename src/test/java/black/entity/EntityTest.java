package black.entity;

import black.model.entity.Booking;
import black.model.entity.Guest;
import black.model.entity.Room;
import black.model.entity.enums.GuestType;
import black.model.entity.enums.RoomClass;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EntityTest {
    public static void main(String[] args) {
        Guest guest = Guest
                .builder()
                .id(1)
                .firstName("Siya")
                .lastName("Afil")
                .guestType(GuestType.DOMESTIC)
                .nationalID("123456789")
                .build();

        System.out.println(guest.getDisplayGuest());

        Room room = Room
                .builder()
                .id(1)
                .roomNumber(01)
                .pricePerNight(BigDecimal.valueOf(243.21))
                .roomCapacity(4)
                .roomClass(RoomClass.DELUXE)
                .build();

        System.out.println(room.getDisplayRoom());

        Booking booking = Booking
                .builder()
                .id(1)
                .guest(guest)
                .room(room)
                .arrivalDate(LocalDate.of(1998,11,15))
                .departureDate(LocalDate.of(1998,11,21))
                .partySize(3)
                .build();

        System.out.println(booking.toString());
    }
}
