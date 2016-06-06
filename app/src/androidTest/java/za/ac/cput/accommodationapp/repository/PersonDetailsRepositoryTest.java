package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.Impl.PersonDetailsRepositoryImpl;

/**
 * Created by mphuser on 5/8/2016.
 */
public class PersonDetailsRepositoryTest extends AndroidTestCase
{
    private static final String TAG="PERSON TEST";
    private Long id;


    public void testName() throws Exception {

    }

    public void testCreateReadUpdateDelete() throws Exception {
        PersonDetailsRepository repo = new PersonDetailsRepositoryImpl(this.getContext());
        // CREATE

        PersonDetails insertedEntity = new PersonDetails.Builder()
                .fName("Vulombe")
                .lName("Makhubele")
                .gender("Male")
                .dob(new Date())
                .email("vvv@FFF")
                .cellNumber("011447141")
                .build();

        id=insertedEntity.getID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<PersonDetails> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        PersonDetails entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        PersonDetails updateEntity = new PersonDetails.Builder()
                .copy(entity)
                .cellNumber("1233212")
                .build();

        repo.update(updateEntity);
        PersonDetails newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","1233212",newEntity.getCellNumber());

        // DELETE ENTITY
        repo.delete(updateEntity);
        PersonDetails deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
