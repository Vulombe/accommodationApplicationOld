package za.ac.cput.accommodationapp.service;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.RoomType;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface RoomTypeService
{
    //this service returns RoomType details in any form you like
    RoomType getRoomTypeByID(Long id);

    RoomType saveRoomType(RoomType entity);

    Set<RoomType> findAll();
}
