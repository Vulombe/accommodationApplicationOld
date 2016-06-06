package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.repository.Impl.ContactsRepositoryImpl;

/**
 * Created by mphuser on 5/8/2016.
 */
public class ContactsRepositoryTest extends AndroidTestCase
{
    private static final String TAG="ADMINISTRATOR TEST";
    private Long id;


    public void testName() throws Exception {

    }

    public void testCreateReadUpdateDelete() throws Exception {
        ContactsRepository repo = new ContactsRepositoryImpl(this.getContext());
        // CREATE

        Contacts createEntity = new Contacts.Builder()
                .email("vvv@FFF")
                .cellNumber("011447141")
                .build();


        Contacts insertedEntity = repo.save(createEntity);
        id=insertedEntity.getID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Contacts> contactses = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",contactses.size()>0);

        //READ ENTITY
        Contacts entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Contacts updateEntity = new Contacts.Builder()
                .copy(createEntity)
                .cellNumber("1233212")
                .build();

        repo.update(updateEntity);
        Contacts newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","1233212",newEntity.getCellNumber());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Contacts deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
