package black.controller;

import black.model.bl.BookingBL;
import black.model.entity.Booking;
import black.model.entity.Guest;
import black.model.entity.Room;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class BookingController {
    private BookingBL bookingBL = new BookingBL();

    public void save(int guestId, int roomId, LocalDate arrivalDate, LocalDate departureDate, int partySize){
        log.debug("Booking Controller Save");
        try{
            Guest guest = Guest.builder()
                    .id(guestId)
                    .build();
            Room room = Room.builder()
                    .id(roomId)
                    .build();
            Booking booking = Booking.builder()
                    .guest(guest)
                    .room(room)
                    .arrivalDate(arrivalDate)
                    .departureDate(departureDate)
                    .partySize(partySize)
                    .build();
            bookingBL.save(booking);
            log.info("Booking saved: " + booking);
        }catch (Exception e){
            log.error("Failed to save Booking");
        }
    }
    public void update(int id, int guestId, int roomId, LocalDate arrivalDate, LocalDate departureDate, int partySize){
        log.debug("Booking Controller Update");
        try{
            Guest guest = Guest.builder()
                    .id(guestId)
                    .build();
            Room room = Room.builder()
                    .id(roomId)
                    .build();
            Booking booking = Booking.builder()
                    .id(id)
                    .guest(guest)
                    .room(room)
                    .arrivalDate(arrivalDate)
                    .departureDate(departureDate)
                    .partySize(partySize)
                    .build();
            bookingBL.update(booking);
            log.info("Booking updated: " + booking);
        }catch (Exception e){
            log.error("Failed to update Booking");
        }
    }
    public void delete(int id){
        log.debug("Booking Controller Delete");
        try {
            bookingBL.delete(id);
            log.info("Booking Deleted");
        } catch (Exception e){
            log.error("Failed to delete Booking");
        }
    }
    public List<Booking> findAll(){
        log.debug("Booking Controller Find All");
        try{
            List<Booking> bookings = bookingBL.findAll();
            log.info("Bookings founded");
            return bookings;
        } catch (Exception e){
            log.error("Found nothing");
            return null;
        }
    }
}
