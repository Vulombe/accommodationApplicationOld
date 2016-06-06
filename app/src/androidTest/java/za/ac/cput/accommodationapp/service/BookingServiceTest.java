package za.ac.cput.accommodationapp.service;

import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.BookingFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.BookingFactoryImpl;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.repository.BookingRepository;
import za.ac.cput.accommodationapp.service.Impl.BookingServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class BookingServiceTest extends AndroidTestCase
{
    private BookingServiceImpl bookingService;
    private BookingFactory bookingFactory;
    private boolean isBound;
    private static final String TAG="BOOKING TEST";


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), BookingServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        bookingFactory = BookingFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BookingServiceImpl.BookingServiceLocalBinder binder
                    = (BookingServiceImpl.BookingServiceLocalBinder)service;
            bookingService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {

        Booking booking = bookingFactory.createBooking(new Date(),"150");
        Booking newEntity = bookingService.saveBooking(booking);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindByDate() throws Exception {
        Booking createEntity1 = bookingFactory.createBooking(new Date(), "150");
        Booking createEntity2 = bookingFactory.createBooking(new Date(), "120");
        Booking createEntity3 = bookingFactory.createBooking(new Date(), "130");

        bookingService.saveBooking(createEntity1);
        bookingService.saveBooking(createEntity2);
        bookingService.saveBooking(createEntity3);


        Set<Booking> bookings = bookingService.getBookingsByDate(new Date());
        Assert.assertTrue(bookings.size() > 2);

    }
}
