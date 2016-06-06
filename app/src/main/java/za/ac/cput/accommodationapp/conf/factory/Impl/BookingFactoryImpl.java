package za.ac.cput.accommodationapp.conf.factory.Impl;

import java.util.Date;

import za.ac.cput.accommodationapp.conf.factory.BookingFactory;
import za.ac.cput.accommodationapp.domain.Booking;

/**
 * Created by student on 2016/04/02.
 */
public class BookingFactoryImpl implements BookingFactory
{
    private static BookingFactoryImpl bookingFactory = null;

    private BookingFactoryImpl() {
    }
    public static BookingFactoryImpl getInstance(){
        if(bookingFactory ==null)
            bookingFactory = new BookingFactoryImpl();
        return bookingFactory;
    }
    @Override
    public Booking createBooking(Date date, String numOfDays)
    {
        Booking booking = new Booking.Builder()
                .date(date)
                .numOfDays(numOfDays)
                .build();
        return booking;
    }

}
