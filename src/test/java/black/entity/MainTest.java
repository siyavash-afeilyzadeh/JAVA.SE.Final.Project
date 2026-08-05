package black.entity;

import black.model.bl.GuestBL;
import black.model.da.GuestDA;
import black.model.entity.Guest;
import black.model.entity.Room;
import black.model.entity.enums.GuestType;
import black.model.entity.enums.RoomClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class MainTest {
    public static void main(String[] args) {

        Guest guest1 = Guest
                .builder()
                .firstName("Siyavash")
                .lastName("Afeilyzadeh")
                .guestType(GuestType.DOMESTIC)
                .nationalID("123456789")
                .birthDate(LocalDate.of(1994, 11, 15))
                .build();

        Guest guest2 = Guest
                .builder()
                .firstName("Ethan")
                .lastName("HornSmith")
                .guestType(GuestType.FOREIGN)
                .passportNumber("T45678945")
                .birthDate(LocalDate.of(1997, 5, 14))
                .build();

//        try (GuestDA guestDA = new GuestDA()) {
//            guestDA.save(guest1);
//            guestDA.save(guest2);
//            guestDA.update(guest2);
//            guestDA.delete(1);
//            guestDA.delete(2);
//        } catch (Exception e) {
//            log.error("Faild to Run" + e.getMessage());
//        }


        GuestBL guestBL = new GuestBL();
        try {
            guestBL.save(guest1);
            guestBL.save(guest2);
        } catch (Exception e){
            log.error("Faild to Run " + e.getMessage());
        }

    }
}

//    Room room1 = Room
//            .builder()
//            .roomNumber(1)
//            .pricePerNight(BigDecimal.valueOf(243.21))
//            .roomCapacity(4)
//            .roomClass(RoomClass.DELUXE)
//            .build();
//
//    Room room2 = Room
//            .builder()
//            .roomNumber(8)
//            .pricePerNight(BigDecimal.valueOf(180.11))
//            .roomCapacity(2)
//            .roomClass(RoomClass.STANDARD)
//            .build();


