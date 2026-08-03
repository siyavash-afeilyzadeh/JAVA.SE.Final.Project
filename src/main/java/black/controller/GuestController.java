package black.controller;

import black.model.bl.GuestBL;
import black.model.entity.Guest;
import black.model.entity.enums.GuestType;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class GuestController {
    private GuestBL guestBL = new GuestBL();

    public void save(String firstName,String lastName, GuestType guestType, String passportNumber, String nationalID, LocalDate birthDate){
        log.debug("Guest Controller Save");
        try{
            Guest guest = Guest
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .guestType(guestType)
                    .passportNumber(passportNumber)
                    .nationalID(nationalID)
                    .birthDate(birthDate)
                    .build();
            guestBL.save(guest);
            log.info("Guest saved: " + guest.getDisplayGuest());
        } catch (Exception e){
            log.error("Failed to save guest: " + e.getMessage());
        }
    }
    public void update(String firstName,String lastName, GuestType guestType, String passportNumber, String nationalID, LocalDate birthDate){
        log.debug("Guest Controller Update");
        try{
            Guest guest = Guest
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .guestType(guestType)
                    .passportNumber(passportNumber)
                    .nationalID(nationalID)
                    .birthDate(birthDate)
                    .build();
            guestBL.update(guest);
            log.info("Guest updated: " + guest.getDisplayGuest());
        } catch (Exception e){
            log.error("Failed to update guest: " + e.getMessage());
        }
    }
    public void delete(int id){
        log.debug("Guest Controller Delete");
        try {
            guestBL.delete(id);
            log.info("Guest deleted");
        } catch (Exception e) {
            log.error("Failed to delete guest: " + e.getMessage());
        }
    }
    public Guest findByID(int id){
        log.debug("Find Guest by ID");
        try{
            Guest guest = guestBL.findByID(id);
            log.info("Guest " + id + "is found.");
            return guest;
        } catch (Exception e){
            log.error("Guest not found" + e.getMessage());
            return null;
        }
    }
    public List<Guest> findByName(String firstName){
        log.debug("Find Guest by Name");
        try {
            List<Guest> guests = guestBL.findByName(firstName);
            log.info("Guests founded");
            return guests;
        } catch (Exception e){
            log.error("Guest not found" + e.getMessage());
            return null;
        }
    }
    public List<Guest> findByFamily(String lastName){
        log.debug("Find Guest by Family");
        try {
            List<Guest> guests = guestBL.findByFamily(lastName);
            log.info("Guests founded");
            return guests;
        } catch (Exception e){
            log.error("Guest not found" + e.getMessage());
            return null;
        }
    }
    public Guest findByNationalID(String nationalId){
        log.debug("Find Guest by National ID");
        try {
            Guest guest = guestBL.findByNationalID(nationalId);
            log.info("Guest with National ID " + nationalId + "is founded.");
            return guest;
        } catch (Exception e){
            log.error("Guest not found" + e.getMessage());
            return null;
        }
    }
    public Guest findByPassportNumber(String passportNumber){
        log.debug("Find Guest by Passport Number");
        try {
            Guest guest = guestBL.findByPassportNumber(passportNumber);
            log.info("Guest with National ID " + passportNumber + "is founded.");
            return guest;
        } catch (Exception e){
            log.error("Guest not found" + e.getMessage());
            return null;
        }
    }
}
