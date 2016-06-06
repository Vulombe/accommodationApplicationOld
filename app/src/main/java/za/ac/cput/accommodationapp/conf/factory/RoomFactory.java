package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by student on 2016/04/02.
 */
public interface RoomFactory
{
    public Room createRoom(int floorNumber, Booking booking, RoomType roomType);
}
