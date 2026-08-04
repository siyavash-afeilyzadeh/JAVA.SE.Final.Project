package black.model.bl;

import black.model.da.GuestDA;
import black.model.entity.Guest;
import black.model.entity.enums.GuestType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
public class GuestBL {
    //--------------Validation Methods--------------
    public void validateAge(Guest guest) throws Exception {
        if (guest.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new Exception("Guest must be at least 18 years old.");
        }
    }

    public void validateIdentity(Guest guest, GuestDA guestDA) throws Exception {
        if (guest.getGuestType() == GuestType.DOMESTIC) {
            Guest dup = guestDA.findByNationalID(guest.getNationalID());
            if (dup != null && dup.getId() != guest.getId()) {
                throw new Exception("This National ID is already registered.");
            }
        } else if (guest.getGuestType() == GuestType.FOREIGN) {
            Guest dup = guestDA.findByPassportNumber(guest.getPassportNumber());
            if (dup != null && dup.getId() != guest.getId()) {
                throw new Exception("This passport number is already registered.");
            }
        }
    }
    //--------------Business Logic Methods--------------
    public void save(Guest guest) throws Exception {
        log.debug("Guest Business Logic Save");
        validateAge(guest);
        try (GuestDA guestDA = new GuestDA()) {
            validateIdentity(guest, guestDA);
            guestDA.save(guest);
            log.info("Guest Business Logic save" + guest.getDisplayGuest() + "successfully");
        }
    }

    public void update(Guest guest) throws Exception {
        log.debug("Guest Business Logic Update");
        validateAge(guest);
        try (GuestDA guestDA = new GuestDA()) {
            validateIdentity(guest, guestDA);
            guestDA.update(guest);
            log.info("Guest Business Logic update" + guest.getDisplayGuest() + "successfully");
        }
    }

    public void delete(int id) throws Exception {
        log.debug("Guest Business Logic Delete");
        try (GuestDA guestDA = new GuestDA()) {
            guestDA.delete(id);
            log.info("Guest Business Logic delete Room successfully");
        }
    }

    public Guest findByID(int id) throws Exception {
        log.debug("Guest Business Logic run 'Find by ID'");
        try (GuestDA guestDA = new GuestDA()) {
            log.info("Guest Business Logic run 'Find by ID' successfully.");
            return guestDA.findByID(id);
        }
    }

    public List<Guest> findByName(String firstName) throws Exception {
        log.debug("Guest Business Logic run 'Find by Name'");
        try (GuestDA guestDA = new GuestDA()) {
            log.info("Guest Business Logic run 'Find by Name' successfully.");
            return guestDA.findByName(firstName);
        }
    }

    public List<Guest> findByFamily(String lastName) throws Exception {
        log.debug("Guest Business Logic run 'Find by Family'");
        try (GuestDA guestDA = new GuestDA()) {
            log.info("Guest Business Logic run 'Find by Family' successfully.");
            return guestDA.findByFamily(lastName);
        }
    }

    public Guest findByNationalID(String nationalId) throws Exception{
        log.debug("Guest Business Logic run 'Find by National ID'");
        try (GuestDA guestDA = new GuestDA()){
            log.info("Guest Business Logic run 'Find by National ID' successfully.");
            return guestDA.findByNationalID(nationalId);
        }
    }

    public Guest findByPassportNumber(String passportNumber) throws Exception{
        log.debug("Guest Business Logic run 'Find by Passport Number'");
        try (GuestDA guestDA = new GuestDA()) {
            log.info("Guest Business Logic run 'Find by Passport Number' successfully.");
            return guestDA.findByPassportNumber(passportNumber);
        }
    }
}

