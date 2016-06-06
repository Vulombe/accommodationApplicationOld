package za.ac.cput.accommodationapp.factory;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import za.ac.cput.accommodationapp.conf.factory.BookingFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.BookingFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.RoomFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.RoomTypeFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.RoomFactory;
import za.ac.cput.accommodationapp.conf.factory.RoomTypeFactory;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by student on 2016/04/02.
 */
public class TestRoom
{
    private RoomFactory roomFactory;
    private BookingFactory bookingFactory;
    private RoomTypeFactory roomTypeFactory;
    private Room room;
    private Booking booking;
    private RoomType roomType;

    @Before
    public void setUpRoom() throws Exception {
        roomFactory = RoomFactoryImpl.getInstance();
        bookingFactory = BookingFactoryImpl.getInstance();
        roomTypeFactory = RoomTypeFactoryImpl.getInstance();
    }

    @Test
    public void testCreateUpdateRoom() throws Exception {
        booking = bookingFactory.createBooking(new Date(),"150");
        roomType = roomTypeFactory.createRoomType("available", "Single");

        room = roomFactory.createRoom(2, booking, roomType);
        Assert.assertEquals(room.getFloorNumber(), 2);

        Room updateRoom = new Room.Builder().copy(room)
                .floorNumber(3).build();
        Assert.assertEquals(updateRoom.getFloorNumber(),3);

    }

    @Test
    public void testUpdateRoom() throws Exception
    {

    }
}
