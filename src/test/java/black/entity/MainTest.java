package black.entity;

import black.controller.GuestController;
import black.controller.RoomController;
import black.model.bl.GuestBL;
import black.model.bl.RoomBL;
import black.model.da.BookingDA;
import black.model.da.GuestDA;
import black.model.da.RoomDA;
import black.model.entity.Booking;
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
                .id(11)
                .firstName("Ethan")
                .lastName("Balmour")
                .guestType(GuestType.FOREIGN)
                .passportNumber("T45235545")
                .birthDate(LocalDate.of(2005, 11, 11))
                .build();

        Room room1 = Room
                .builder()
                .roomNumber(1)
                .pricePerNight(BigDecimal.valueOf(243.21))
                .roomCapacity(6)
                .roomClass(RoomClass.DELUXE)
                .build();

        Room room2 = Room
                .builder()
                .roomNumber(5)
                .pricePerNight(BigDecimal.valueOf(110))
                .roomCapacity(2)
                .roomClass(RoomClass.STANDARD)
                .build();

        Booking booking1 = Booking
                .builder()
                .guest(Guest.builder().id(10).build())
                .room(Room.builder().id(8).build())
                .arrivalDate(LocalDate.of(2020,11,8 ))
                .departureDate(LocalDate.of(2020,11,15))
                .partySize(2)
                .build();
        Booking booking2 = Booking
                .builder()
                .guest(Guest.builder().id(11).build())
                .room(Room.builder().id(7).build())
                .arrivalDate(LocalDate.of(2020,10,12 ))
                .departureDate(LocalDate.of(2020,10,17))
                .partySize(5)
                .build();



//        try(BookingDA bookingDA = new BookingDA()){
//            bookingDA.save(booking1);
//            bookingDA.save(booking2);
//            bookingDA.delete(3);
//            bookingDA.delete(4);
//        }catch (Exception e){
//            log.error("Faild to Run" + e.getMessage());}



//        try (RoomDA roomDA = new RoomDA()){
//            roomDA.save(room1);
//            roomDA.save(room2);
//            roomDA.update(room2);
//            roomDA.delete(1);
//            roomDA.delete(2);
//        } catch (Exception e) {
//            log.error("Faild to Run" + e.getMessage());
//        }
//        RoomBL roomBL = new RoomBL();
//        try {
//            roomBL.save(room1);
//            roomBL.save(room2);
//            roomBL.update(room2);
//            roomBL.delete(3);
//            roomBL.delete(4);
//        } catch (Exception e){
//            log.error("Faild to Run " + e.getMessage());
//        }
//        RoomController roomController = new RoomController();
//        roomController.save(
//                1,
//                BigDecimal.valueOf(243.21),
//                4,
//                RoomClass.DELUXE
//        );
//
//        roomController.save(
//                5,
//                BigDecimal.valueOf(110),
//                2,
//                RoomClass.STANDARD
//        );
//        roomController.update(6,
//                5,
//                BigDecimal.valueOf(988),
//                6,
//                RoomClass.DELUXE
//                );
//        roomController.delete(5);
//        roomController.delete(6);


//        try (GuestDA guestDA = new GuestDA()) {
//            guestDA.save(guest1);
//            guestDA.save(guest2);
//            guestDA.update(guest2);
//            guestDA.delete(1);
//            guestDA.delete(2);
//        } catch (Exception e) {
//            log.error("Faild to Run" + e.getMessage());
//        }


//        System.out.println(guest2.getId());;
//        GuestBL guestBL = new GuestBL();
//        try {
//            guestBL.delete(6);
//            guestBL.delete(7);
//            guestBL.save(guest1);
//            guestBL.save(guest2);
//            guestBL.update(guest2);
//        } catch (Exception e){
//            log.error("Faild to Run " + e.getMessage());
//        }

//        GuestController guestController = new GuestController();
//        guestController.save(
//                "Siyavash",
//                "Afeilyzadeh",
//                GuestType.DOMESTIC,
//                "",
//                "123456789",
//                LocalDate.of(1981, 1, 23));
//        guestController.update(
//                9,
//                "John",
//                "MacTovish",
//                GuestType.FOREIGN,
//                "T45225545",
//                "",
//                LocalDate.of(1984, 5, 17));
//        guestController.delete(8);
//        guestController.delete(9);

//    }


    }
}


