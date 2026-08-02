package black.model.bl;

import black.model.da.BookingDA;
import black.model.entity.Booking;
import java.time.LocalDate;
import java.util.List;

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
        validateDate(booking);
        try (BookingDA bookingDA = new BookingDA()) {
            validateConflict(booking, bookingDA);
            bookingDA.save(booking);
        }
    }

    public void update(Booking booking) throws Exception {
        validateDate(booking);
        try (BookingDA bookingDA = new BookingDA()) {
            validateConflict(booking, bookingDA);
            bookingDA.update(booking);
        }
    }

    public void delete(int id) throws Exception {
        try (BookingDA bookingDA = new BookingDA()) {
            bookingDA.delete(id);
        }
    }

    public List<Booking> findAll() throws Exception {
        try (BookingDA bookingDA = new BookingDA()) {
            return bookingDA.findAll();
        }
    }
}
