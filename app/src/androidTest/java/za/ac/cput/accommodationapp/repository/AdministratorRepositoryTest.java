package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.Impl.AdministratorRepositoryImpl;

/**
 * Created by 212068075 on 4/24/2016.
 */
public class AdministratorRepositoryTest extends AndroidTestCase
{
   private static final String TAG="ADMINISTRATOR TEST";
    private Long id;


    public void testName() throws Exception {

    }

    public void testCreateReadUpdateDelete() throws Exception {
        AdministratorRepository repo = new AdministratorRepositoryImpl(this.getContext());
        // CREATE

        PersonDetails personDetails = new PersonDetails.Builder()
                .fName("Vulombe")
                .lName("Makhubele")
                .gender("Male")
                .dob(new Date())
                .email("vvv@FFF")
                .cellNumber("011447141")
                .build();
        List<Location> locations = null;
        Administrator createEntity = new Administrator.Builder()
                .personDetails(personDetails)
                .locations(locations)
                .build();

        Administrator insertedEntity = repo.save(createEntity);
        id=insertedEntity.getEmpID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Administrator> administrators = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",administrators.size()>0);

        //READ ENTITY
        Administrator entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        PersonDetails personDetails2 = new PersonDetails.Builder()
                .copy(personDetails)
                .cellNumber("1233212")
                .build();
        Administrator updateEntity = new Administrator.Builder()
                .copy(entity)
                .personDetails(personDetails2)
                .build();
        repo.update(updateEntity);
        Administrator newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","1233212",personDetails2.getCellNumber());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Administrator deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
