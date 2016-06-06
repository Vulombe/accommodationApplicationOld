package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.Impl.RoomTypeRepositoryImpl;

/**
 * Created by mphuser on 5/8/2016.
 */
public class RoomTypeRepositoryTest extends AndroidTestCase
{
    private static final String TAG="ROOMTYPE TEST";
    private Long id;



    public void testCreateReadUpdateDelete() throws Exception {
        RoomTypeRepository repo = new RoomTypeRepositoryImpl(this.getContext());
        // CREATE



        RoomType createEntity = new RoomType.Builder()
                .status("Not Available")
                .type("Single")
                .build();



        RoomType insertedEntity = repo.save(createEntity);
        id=insertedEntity.getID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<RoomType> roomTypes = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",roomTypes.size()>0);

        //READ ENTITY
        RoomType entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        RoomType updateEntity = new RoomType.Builder()
                .copy(entity)
                .status("Available")
                .build();
        repo.update(updateEntity);
        RoomType newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1,newEntity.getStatus());

        // DELETE ENTITY
        repo.delete(updateEntity);
        RoomType deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
