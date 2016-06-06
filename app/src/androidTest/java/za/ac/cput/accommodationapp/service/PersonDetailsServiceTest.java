package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.Impl.PersonDetailsFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.conf.factory.ValidationFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.PersonDetailsRepository;
import za.ac.cput.accommodationapp.service.Impl.PersonDetailsServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class PersonDetailsServiceTest extends AndroidTestCase {

    private PersonDetailsServiceImpl personDetailsService;
    private PersonDetailsFactory personDetailsFactory;
    private boolean isBound;
    private static final String TAG = "PERSONDETAILS TEST";


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), PersonDetailsServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        personDetailsFactory = PersonDetailsFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PersonDetailsServiceImpl.PersonDetailsServiceLocalBinder binder
                    = (PersonDetailsServiceImpl.PersonDetailsServiceLocalBinder) service;
            personDetailsService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };



    public void testCreateAndFindByDate() throws Exception {
        Map<String,String> names = new HashMap<String, String>();
        Map<String,String> details = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba","Makhubele");

        PersonDetails createEntity1 = personDetailsFactory.createPersonDetails(names,details,new Date());

        personDetailsService.savePersonDetails(createEntity1);
        Map<String,String> names2 = new HashMap<String, String>();
        Map<String,String> details2 = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba", "Makhubele");

        PersonDetails createEntity2 = personDetailsFactory.createPersonDetails(names2,details2,new Date());
        personDetailsService.savePersonDetails(createEntity2);
        Map<String,String> names3 = new HashMap<String, String>();
        Map<String,String> details3 = new HashMap<String, String>();
        details.put("Male","tMakhubele@gmai.com");
        names.put("Themba", "Makhubele");

        PersonDetails createEntity3 = personDetailsFactory.createPersonDetails(names3,details3,new Date());
        personDetailsService.savePersonDetails(createEntity3);


        Set<PersonDetails> bookings = personDetailsService.getAllDetails();
        Assert.assertTrue(bookings.size() > 2);
    }
}