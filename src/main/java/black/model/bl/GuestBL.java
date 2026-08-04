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
        try (GuestDA guestDA = new GuestDA()) {
            return guestDA.findByID(id);
        }
    }

    public List<Guest> findByName(String firstName) throws Exception {
        try (GuestDA guestDA = new GuestDA()) {
            return guestDA.findByName(firstName);
        }
    }

    public List<Guest> findByFamily(String lastName) throws Exception {
        try (GuestDA guestDA = new GuestDA()) {
            return guestDA.findByFamily(lastName);
        }
    }

    public Guest findByNationalID(String nationalId) throws Exception{
        try (GuestDA guestDA = new GuestDA()){
            return guestDA.findByNationalID(nationalId);
        }
    }

    public Guest findByPassportNumber(String passportNumber) throws Exception{
        try (GuestDA guestDA = new GuestDA()) {
            return guestDA.findByPassportNumber(passportNumber);
        }
    }
}

