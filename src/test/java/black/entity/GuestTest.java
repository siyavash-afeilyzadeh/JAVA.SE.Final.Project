package black.entity;

import black.model.entity.Guest;
import black.model.entity.enums.GuestType;

public class GuestTest {
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
    }
}
