package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.AddressFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AddressFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PersonDetailsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.StudentFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.conf.factory.StudentFactory;
import za.ac.cput.accommodationapp.conf.factory.ValidationFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;
import za.ac.cput.accommodationapp.domain.Validation;
import za.ac.cput.accommodationapp.service.Impl.StudentServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class StudentServiceTest extends AndroidTestCase
{
    /*
           Student getStudentByID(Long id);


    Student saveStudent(Student entity);

    Set<Student> findAll();
    */

    private StudentServiceImpl studentService;
    private boolean isBound;
    private static final String TAG="STUDENT TEST";
    private PersonDetailsFactory personDetailsFactory;
    private AddressFactory addressFactory;
    private StudentFactory studentFactory;
    private Room room;
    private List<Payment> payments;
    private PersonDetails personDetails;
    private Address address;
    private Student student;
    private String validate;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), StudentServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        personDetailsFactory = PersonDetailsFactoryImpl.getInstance();
        addressFactory = AddressFactoryImpl.getInstance();
        studentFactory = StudentFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StudentServiceImpl.StudentServiceLocalBinder binder
                    = (StudentServiceImpl.StudentServiceLocalBinder)service;
            studentService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {
        Map<String,String> names = new HashMap<String, String>();
        Map<String,String> addressDetails = new HashMap<String, String>();
        Map<String,String> details = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba","Makhubele");
        addressDetails.put("Limpopo","Elim");
        String validate = ValidationFactory.getAverage(200, 6);

        Address address = addressFactory.createAddress(addressDetails, "Denver", "0826");
        PersonDetails personDetails = personDetailsFactory.createPersonDetails(names, details, new Date());
        Student newEntity = studentFactory.createStudent(validate, personDetails, address, payments, room);

        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception {
        Map<String,String> names = new HashMap<String, String>();
        Map<String,String> addressDetails = new HashMap<String, String>();
        Map<String,String> details = new HashMap<String, String>();
        details.put("Male","tMkjgakhubele@gmai.com");
        names.put("Hellow","Makhubele");
        addressDetails.put("Gauteng","Elim");
        String validate = ValidationFactory.getAverage(300, 6);

        Address address = addressFactory.createAddress(addressDetails, "Denver", "0826");
        PersonDetails personDetails = personDetailsFactory.createPersonDetails(names, details, new Date());
        Student createEntity1 = studentFactory.createStudent(validate, personDetails, address, payments, room);

        Map<String,String> names2 = new HashMap<String, String>();
        Map<String,String> addressDetails2 = new HashMap<String, String>();
        Map<String,String> details2 = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba","Makhubele");
        addressDetails.put("Limpopo","Elim");
        String validate2 = ValidationFactory.getAverage(200, 6);

        Address address2 = addressFactory.createAddress(addressDetails2, "Denver", "0826");
        PersonDetails personDetails2 = personDetailsFactory.createPersonDetails(names2, details2, new Date());
        Student createEntity2 = studentFactory.createStudent(validate2, personDetails2, address2, payments, room);

        Map<String,String> names3 = new HashMap<String, String>();
        Map<String,String> addressDetails3 = new HashMap<String, String>();
        Map<String,String> details3 = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba","Makhubele");
        addressDetails.put("Limpopo","Elim");

        String validate3 = ValidationFactory.getAverage(200, 6);

        Address address3 = addressFactory.createAddress(addressDetails3, "Denver", "0826");
        PersonDetails personDetails3 = personDetailsFactory.createPersonDetails(names3, details3, new Date());
        Student createEntity3 = studentFactory.createStudent(validate3, personDetails3, address3, payments, room);

        studentService.saveStudent(createEntity1);
        studentService.saveStudent(createEntity2);
        studentService.saveStudent(createEntity3);

        Set<Student> medicalAidContributions = studentService.findAll();
        Assert.assertTrue(medicalAidContributions.size() > 2);

        id = createEntity1.getStudentID();

    }

    public void testGetStudentByID() throws Exception {
        Student student =  studentService.getStudentByID(id);
    }
}
