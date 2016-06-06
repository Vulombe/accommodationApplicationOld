package za.ac.cput.accommodationapp.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Booking;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface BookingService
{
    //this service returns Booking details in any form you like
    Set<Booking> getAllBookings();
    Booking saveBooking(Booking entity);
    Booking getBookingsByID(Long id);
    Set<Booking> getBookingsByDate(Date date);
    // CREATE
}
