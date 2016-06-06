package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.repository.BookingRepository;
import za.ac.cput.accommodationapp.repository.Impl.BookingRepositoryImpl;
import za.ac.cput.accommodationapp.service.BookingService;

public class BookingServiceImpl extends Service implements BookingService{


    private final IBinder localBinder = new BookingServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    BookingRepository repository;

    public class BookingServiceLocalBinder extends Binder {
        public BookingServiceImpl getService() {
            return BookingServiceImpl.this;
        }
    }

    private static BookingServiceImpl service = null;

    public static BookingServiceImpl getInstance() {
        if (service == null)
            service = new BookingServiceImpl();
        return service;
    }

    public BookingServiceImpl() {
        repository = new BookingRepositoryImpl(App.getAppContext());
    }

    @Override
    public Set<Booking> getAllBookings() {
        Set<Booking> bookings = new HashSet<>();
        Iterable<Booking> values = repository.findAll();
        for (Booking value : values) {
            bookings.add(value);
        }
        return bookings;
    }

    @Override
    public Booking getBookingsByID(Long id) {
        if (repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }
   public Booking saveBooking(Booking entity)
   {
       return repository.save(entity);
   }

    @Override
    public Set<Booking> getBookingsByDate(Date date) {
        Set<Booking> bookings = new HashSet<>();
        Set<Booking> results = new HashSet<>();
        bookings = getAllBookings();
        for (Booking booking : bookings) {
            if (booking.getDate() == date) {
                results.add(booking);
            }
        }

        if (results == null) {
            System.out.println("Found Nothing!");
        }
        return results;
    }
}
