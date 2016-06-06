package za.ac.cput.accommodationapp.repository;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Location;

import za.ac.cput.accommodationapp.repository.Impl.LocationRepositoryImpl;

/**
 * Created by 212068075 on 4/24/2016.
 */
public class LocationRepositoryTest extends AndroidTestCase
{
    private static final String TAG="LOCATION TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        LocationRepository repo = new LocationRepositoryImpl(this.getContext());
        // CREATE

         Address address = new Address.Builder()
                .province("Mpumalanga")
                .street("Denver")
                .city("Hazyview")
                .cityCode("1245")
                .build();

         Location createEntity = new Location.Builder()
                .buildingName("NMJ")
                .address(address)
                .build();

        Location insertedEntity = repo.save(createEntity);
        id=insertedEntity.getLocationID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Location> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Location entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Location updateEntity = new Location.Builder()
                .copy(entity)
                .buildingName("Plein Street")
                .build();
        repo.update(updateEntity);
        Location newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Plein Street",newEntity.getBuildingName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Location deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
