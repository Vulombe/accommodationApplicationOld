package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.repository.Impl.AddressRepositoryImpl;

/**
 * Created by mphuser on 5/8/2016.
 */
public class AddressRepositoryTest extends AndroidTestCase
{
    private static final String TAG="ADDRESS TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        AddressRepository repo = new AddressRepositoryImpl(this.getContext());
        // CREATE

        Address createEntity = new Address.Builder()
                .province("Mpumalanga")
                .street("Denver")
                .city("Hazyview")
                .cityCode("1245")
                .build();



        Address insertedEntity = repo.save(createEntity);
        id=insertedEntity.getID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Address> addresses = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",addresses.size()>0);

        //READ ENTITY
        Address entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Address updateEntity = new Address.Builder()
                .copy(entity)
                .street("Plein Street")
                .build();
        repo.update(updateEntity);
        Address newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Plein Street",newEntity.getStreet());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Address deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
