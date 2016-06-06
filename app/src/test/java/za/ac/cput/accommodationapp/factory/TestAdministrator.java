package za.ac.cput.accommodationapp.factory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.ac.cput.accommodationapp.conf.factory.AdministratorFactory;
import za.ac.cput.accommodationapp.conf.factory.ContactsFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AdministratorFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.ContactsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PersonDetailsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by student on 2016/04/02.
 */
public class TestAdministrator
{

    private ContactsFactory contactsFactory;
    private PersonDetailsFactory personDetailsFactory;
    private AdministratorFactory administratorFactory;
    private Contacts contacts;
    private PersonDetails personDetails;
    private Administrator administrator;
    private List<Location> location;

    @Before
    public void setUpAdministrator() throws Exception
    {
        contactsFactory = ContactsFactoryImpl.getInstance();
        personDetailsFactory = PersonDetailsFactoryImpl.getInstance();
        administratorFactory = AdministratorFactoryImpl.getInstance();
    }

    @Test
    public void testCreateUpdateAdministrator() throws Exception
    {
        Map<String,String> names = new HashMap<String, String>();
        Map<String,String> details = new HashMap<String, String>();
        details.put("Male","vmakhubs@gmail.com");
        names.put("Vulombe","Makhubele");

        contacts = contactsFactory.createContacts("vma@gg.com","05422147");
        personDetails = personDetailsFactory.createPersonDetails(names,details,new Date());
        administrator = administratorFactory.createAdmin(personDetails,location,contacts);

        Assert.assertEquals(contacts.getCellNumber(), "05422147");

        Contacts contactsUpdate = new Contacts.Builder().copy(contacts).cellNumber("087444").build();
        Administrator updateAdministrator = new Administrator.Builder().copy(administrator)
                .contacts(contactsUpdate).build();
        Assert.assertEquals(contactsUpdate.getCellNumber(),"087444");
    }

    @Test
    public void testUpdateAdministrator() throws Exception {

    }
}
