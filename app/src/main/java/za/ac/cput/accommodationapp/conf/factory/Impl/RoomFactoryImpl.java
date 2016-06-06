package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.RoomFactory;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by student on 2016/04/02.
 */
public class RoomFactoryImpl implements RoomFactory
{
    private static RoomFactoryImpl roomFactory = null;

    private RoomFactoryImpl() {
    }
    public static RoomFactoryImpl getInstance(){
        if(roomFactory ==null)
            roomFactory = new RoomFactoryImpl();
        return roomFactory;
    }

    @Override
    public Room createRoom(int floorNumber, Booking booking, RoomType roomType)
    {
        Room room = new Room.Builder().floorNumber(floorNumber)
                            .roomType(roomType)
                            .booking(booking).build();
        return room;
    }
}
