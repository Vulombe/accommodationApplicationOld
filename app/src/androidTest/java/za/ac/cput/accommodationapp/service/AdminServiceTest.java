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

import za.ac.cput.accommodationapp.conf.factory.AdministratorFactory;
import za.ac.cput.accommodationapp.conf.factory.ContactsFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AdministratorFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.ContactsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PersonDetailsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.AdministratorRepository;
import za.ac.cput.accommodationapp.service.Impl.AdminServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class AdminServiceTest extends AndroidTestCase {
    private static final String TAG="ADMINISTRATOR TEST";
    private AdminServiceImpl service;


    private AdministratorRepository repository;
    private ContactsFactory contactsFactory;
    private PersonDetailsFactory personDetailsFactory;
    private AdministratorFactory administratorFactory;
    private Contacts contacts;
    private PersonDetails personDetails;
    private Administrator administrator;
    private List<Location> location1;
    private List<Location> location2;
    private List<Location> location3;
    private AdminService adminService;
    private boolean isBound;


    private AdministratorFactory factory;
    public void setUp() throws Exception
    {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), AdminServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        factory = AdministratorFactoryImpl.getInstance();
        contactsFactory = ContactsFactoryImpl.getInstance();
        personDetailsFactory = PersonDetailsFactoryImpl.getInstance();
    }



    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AdminServiceImpl.AdminServiceLocalBinder binder
                    = (AdminServiceImpl.AdminServiceLocalBinder)service;
            adminService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


    public void testCreateAndFindListOfEntities() throws Exception {

        Map<String,String> names1 = new HashMap<>();
        Map<String,String> details1 = new HashMap<String, String>();
        details1.put("Female","vmakhubs@gmail.com");
        names1.put("Tshembhani","Maluks");

        Contacts contacts1 = contactsFactory.createContacts("vma@gg.com", "05422147");
        PersonDetails personDetails1 = personDetailsFactory.createPersonDetails(names1, details1, new Date());
        Administrator administrator1 = administratorFactory.createAdmin(personDetails1, location1, contacts1);

        adminService.saveAdmin(administrator1);


        Map<String,String> names2 = new HashMap<>();
        Map<String,String> details2 = new HashMap<String, String>();
        details2.put("Male","vmakhubs@gmail.com");
        names2.put("Dion","Dreezy");

        Contacts contacts2 = contactsFactory.createContacts("vma@gg.com", "05422147");
        PersonDetails personDetails2 = personDetailsFactory.createPersonDetails(names2, details2, new Date());
        Administrator administrator2 = administratorFactory.createAdmin(personDetails2, location2, contacts2);

        adminService.saveAdmin(administrator2);


        Map<String,String> names3 = new HashMap<>();
        Map<String,String> details3 = new HashMap<String, String>();
        details3.put("Male","vmakhubs@gmail.com");
        names3.put("Vulombe","Makhubele");

        Contacts contacts3 = contactsFactory.createContacts("vma@gg.com", "05422147");
        PersonDetails personDetails3 = personDetailsFactory.createPersonDetails(names3, details3, new Date());
        Administrator administrator3 = administratorFactory.createAdmin(personDetails3, location3, contacts3);

        adminService.saveAdmin(administrator3);
        Set<Administrator> administrators = adminService.findAll();
        Assert.assertTrue(administrators.size() > 2);

    }
}
