package black.model.bl;

import black.model.da.BookingDA;
import black.model.entity.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingBL {
    public void save(Booking booking) throws Exception{
        try(BookingDA bookingDA = new BookingDA()){
            bookingDA.save(booking);
        }
    }
    public void update(Booking booking) throws Exception{
        try(BookingDA bookingDA = new BookingDA()){
            bookingDA.update(booking);
        }
    }
    public void delete(int id) throws Exception{
        try(BookingDA bookingDA = new BookingDA()){
            bookingDA.delete(id);
        }
    }
    public List<Booking> findAll() throws Exception{
        try(BookingDA bookingDA = new BookingDA()){
            return bookingDA.findAll();
        }
    }
}
