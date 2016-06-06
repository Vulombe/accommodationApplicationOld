package za.ac.cput.accommodationapp.conf.factory;

import java.util.Date;

import za.ac.cput.accommodationapp.domain.Booking;

/**
 * Created by student on 2016/04/02.
 */
public interface BookingFactory
{
    public Booking createBooking(Date date, String numOfDays);
}
