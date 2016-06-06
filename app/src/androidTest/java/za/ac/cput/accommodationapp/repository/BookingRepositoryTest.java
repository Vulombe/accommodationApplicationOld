package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.repository.Impl.BookingRepositoryImpl;

/**
 * Created by mphuser on 5/8/2016.
 */
public class BookingRepositoryTest extends AndroidTestCase
{
    private static final String TAG="BOOKING TEST";
    private Long id;



    public void testCreateReadUpdateDelete() throws Exception {
        BookingRepository repo = new BookingRepositoryImpl(this.getContext());
        // CREATE

        Booking createEntity = new Booking.Builder()
                .date(new Date())
                .numOfDays("16")
                .build();

        Booking insertedEntity = repo.save(createEntity);
        id=insertedEntity.getID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Booking> bookings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",bookings.size()>0);

        //READ ENTITY
        Booking entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Booking updateEntity = new Booking.Builder()
                .copy(entity)
                .numOfDays("17")
                .build();
        repo.update(updateEntity);
        Booking newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1,newEntity.getNumOfDays());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Booking deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
