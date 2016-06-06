package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.RoomTypeFactory;
import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by student on 2016/04/02.
 */
public class RoomTypeFactoryImpl implements RoomTypeFactory
{
    private static RoomTypeFactoryImpl roomTypeFactory = null;

    private RoomTypeFactoryImpl() {
    }
    public static RoomTypeFactoryImpl getInstance(){
        if(roomTypeFactory ==null)
            roomTypeFactory = new RoomTypeFactoryImpl();
        return roomTypeFactory;
    }

    @Override
    public RoomType createRoomType(String status, String type)
    {
        RoomType roomType = new RoomType.Builder().status(status).type(type)
                                        .build();
        return roomType;
    }
}
