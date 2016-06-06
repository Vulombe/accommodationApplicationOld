package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.Impl.RoomRepositoryImpl;

/**
 * Created by 212068075 on 4/24/2016.
 */
public class RoomRepositoryTest extends AndroidTestCase
{
    private static final String TAG="ROOM TEST";
    private Long id;



    public void testCreateReadUpdateDelete() throws Exception {
        RoomRepository repo = new RoomRepositoryImpl(this.getContext());
        // CREATE

        Booking booking = new Booking.Builder()
                .date(new Date())
                .numOfDays("16")
                .build();

        RoomType roomType = new RoomType.Builder()
                .status("Not Available")
                .type("Single")
                .build();

        Room createEntity = new Room.Builder()
                .floorNumber(2)
                .roomType(roomType)
                .booking(booking)
                .build();

        Room insertedEntity = repo.save(createEntity);
        id=insertedEntity.getRoomNumber();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Room> rooms = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",rooms.size()>0);

        //READ ENTITY
        Room entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Room updateEntity = new Room.Builder()
                .copy(entity)
                .floorNumber(1)
                .build();
        repo.update(updateEntity);
        Room newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1,newEntity.getFloorNumber());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Room deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}
