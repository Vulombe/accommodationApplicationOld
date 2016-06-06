package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;
import za.ac.cput.accommodationapp.repository.Impl.StudentRepositoryImpl;

/**
 * Created by 212068075 on 4/24/2016.
 */
public class StudentRepositoryTest extends AndroidTestCase
{
    private static final String TAG="STUDENT TEST";
    private Long id;


    public void testName() throws Exception {

    }

    public void testCreateReadUpdateDelete() throws Exception {
        StudentRepository repo = new StudentRepositoryImpl(this.getContext());
        // CREATE

        PersonDetails personDetails = new PersonDetails.Builder()
                .fName("Vulombe")
                .lName("Makhubele")
                .gender("Male")
                .dob(new Date())
                .email("vvv@FFF")
                .cellNumber("011447141")
                .build();

        Address address = new Address.Builder()
                .province("Mpumalanga")
                .street("Denver")
                .city("Hazyview")
                .cityCode("1245")
                .build();
        List<Payment> payments = null;
        Room room = null;

        Student createEntity = new Student.Builder()
                .personDetails(personDetails)
                .payments(payments)
                .address(address)
                .room(room)
                .build();

        Student insertedEntity = repo.save(createEntity);
        id=insertedEntity.getStudentID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Student> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Student entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        PersonDetails personDetails2 = new PersonDetails.Builder()
                .copy(personDetails)
                .cellNumber("1233212")
                .build();
        Student updateEntity = new Student.Builder()
                .copy(entity)
                .personDetails(personDetails2)
                .build();
        repo.update(updateEntity);
        Student newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","1233212",personDetails2.getCellNumber());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Student deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
