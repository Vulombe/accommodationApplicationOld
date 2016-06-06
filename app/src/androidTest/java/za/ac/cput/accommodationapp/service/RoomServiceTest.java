package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;

import za.ac.cput.accommodationapp.conf.factory.BookingFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.BookingFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.RoomFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.RoomTypeFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.RoomFactory;
import za.ac.cput.accommodationapp.conf.factory.RoomTypeFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.RoomRepository;
import za.ac.cput.accommodationapp.service.Impl.RoomServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class RoomServiceTest extends AndroidTestCase {

    private RoomServiceImpl roomService;
    private RoomFactory roomFactory;
    private RoomTypeFactory roomTypeFactory;
    private BookingFactory bookingFactory;
    private boolean isBound;
    private static final String TAG="ROOM TEST";


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), RoomServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        roomFactory = RoomFactoryImpl.getInstance();
        roomTypeFactory = RoomTypeFactoryImpl.getInstance();
        bookingFactory = BookingFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RoomServiceImpl.RoomServiceLocalBinder binder
                    = (RoomServiceImpl.RoomServiceLocalBinder)service;
            roomService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {

        Booking booking = bookingFactory.createBooking(new Date(), "150");
        RoomType roomType = roomTypeFactory.createRoomType("available", "Single");

        Room newEntity = roomFactory.createRoom(2, booking, roomType);
        roomService.saveRoom(newEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }
}
