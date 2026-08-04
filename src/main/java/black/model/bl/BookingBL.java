package black.model.bl;

import black.model.da.BookingDA;
import black.model.entity.Booking;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class BookingBL {
    //--------------Validation Methods--------------
    public void validateDate(Booking booking) throws Exception{
        if (!booking.getArrivalDate().isBefore(booking.getDepartureDate())){
            throw new Exception("Arrival date must be before departure date.");
        }
        if (booking.getArrivalDate().isBefore(LocalDate.now())){
            throw new Exception("Arrival date cannot be in the past.");
        };
    }

    public void validateConflict(Booking booking, BookingDA bookingDA) throws Exception{
        List<Integer> conflicts = bookingDA.findReserveDates(
                booking.getRoom().getRoomNumber(),
                booking.getArrivalDate(),
                booking.getDepartureDate()
        );
        for(int conflictID : conflicts){
            if(conflictID != booking.getId()){
                throw new Exception("The room "
                        + booking.getRoom().getRoomNumber()
                        + " is already booked for the selected dates.");
            }
        }
    }
    //--------------Business Logic Methods--------------
    public void save(Booking booking) throws Exception {
        log.debug("Booking Business Logic Save");
        validateDate(booking);
        try (BookingDA bookingDA = new BookingDA()) {
            validateConflict(booking, bookingDA);
            bookingDA.save(booking);
            log.info("Booking Business Logic save" + booking + "successfully");
        }
    }

    public void update(Booking booking) throws Exception {
        log.debug("Booking Business Logic Update");
        validateDate(booking);
        try (BookingDA bookingDA = new BookingDA()) {
            validateConflict(booking, bookingDA);
            bookingDA.update(booking);
            log.info("Booking Business Logic update" + booking + "successfully");
        }
    }

    public void delete(int id) throws Exception {
        log.debug("Booking Business Logic Delete");
        try (BookingDA bookingDA = new BookingDA()) {
            bookingDA.delete(id);
            log.info("Booking Business Logic delete Booking successfully");
        }
    }

    public List<Booking> findAll() throws Exception {
        log.debug("Booking Business Logic run findAll");
        try (BookingDA bookingDA = new BookingDA()) {
            log.info("Booking Business Logic run 'Find All' successfully.");
            return bookingDA.findAll();
        }
    }
}
