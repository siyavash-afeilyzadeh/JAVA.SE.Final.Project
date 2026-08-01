package black.model.bl;

import black.model.da.GuestDA;
import black.model.entity.Guest;

import java.time.LocalDate;
import java.time.Period;

public class GuestBL {
    public void save(Guest guest) throws Exception{
        try(GuestDA guestDA = new GuestDA()) {
            if(!guest.getBirthDate().isAfter(LocalDate.now().minusYears(18))){
                guestDA.save(guest);
            }
        }
    }
    public void update(Guest guest) throws Exception{
        try(GuestDA guestDA = new GuestDA()){
            if(!guest.getBirthDate().isAfter(LocalDate.now().minusYears(18))){
                guestDA.update(guest);
            }
        }
    }
    public void delete(int id) throws Exception{
        try(GuestDA guestDA = new GuestDA()){
            guestDA.delete(id);
        }
    }
    public Guest findByID(int id) throws Exception{
        try(GuestDA guestDA = new GuestDA()){
            return guestDA.findByID(id);
        }
    }
    public Guest findByName(String firstName) throws Exception{
        try(GuestDA guestDA = new GuestDA()){
            return guestDA.findByName(firstName);
        }
    }
    public Guest findByFamily(String lastName) throws Exception{
        try(GuestDA guestDA = new GuestDA()){
            return guestDA.findByFamily(lastName);
        }
    }
}
