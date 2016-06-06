package za.ac.cput.accommodationapp.service;

import java.util.Set;


import za.ac.cput.accommodationapp.domain.Room;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface RoomService
{
    //this service returns Room details in any form you like
    Room getRoomByID(Long id);

    Room saveRoom(Room entity);

    Set<Room> findAll();
}
