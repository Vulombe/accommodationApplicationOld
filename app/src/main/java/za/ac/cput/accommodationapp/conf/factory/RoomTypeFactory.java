package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by student on 2016/04/02.
 */
public interface RoomTypeFactory
{
    public RoomType createRoomType(String status, String type);
}
