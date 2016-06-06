package za.ac.cput.accommodationapp.factory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.ac.cput.accommodationapp.conf.factory.AddressFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AddressFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PersonDetailsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.StudentFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.conf.factory.StudentFactory;
import za.ac.cput.accommodationapp.conf.factory.ValidationFactory;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;

/**
 * Created by student on 2016/04/02.
 */
public class TestStudent
{

    private PersonDetailsFactory personDetailsFactory;
    private AddressFactory addressFactory;
    private StudentFactory studentFactory;
    private Room room;
    private List<Payment> payments;
    private PersonDetails personDetails;
    private Address address;
    private Student student;
    private String validate;

    @Before
    public void setUpStudent() throws Exception {
        personDetailsFactory = PersonDetailsFactoryImpl.getInstance();
        addressFactory = AddressFactoryImpl.getInstance();
        studentFactory = StudentFactoryImpl.getInstance();
    }

    @Test
    public void testCreateUpdateStudent() throws Exception {
        Map<String,String> names = new HashMap<String, String>();
        Map<String,String> addressDetails = new HashMap<String, String>();
        Map<String,String> details = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba","Makhubele");
        addressDetails.put("Limpopo","Elim");
        validate = ValidationFactory.getAverage(200, 6);

        address = addressFactory.createAddress(addressDetails, "Denver", "0826");
        personDetails = personDetailsFactory.createPersonDetails(names,details,new Date());
        student = studentFactory.createStudent(validate,personDetails,address,payments,room);

        Assert.assertEquals(address.getStreet(), "Denver");

        Address updateAddress = new Address.Builder().copy(address).street("Dorset").build();
        Student updateStudent = new Student.Builder().copy(student).address(updateAddress).build();
        Assert.assertEquals(updateAddress.getStreet(),"Dorset");
    }

    @Test
    public void testUpdateStudent() throws Exception {

    }
}
