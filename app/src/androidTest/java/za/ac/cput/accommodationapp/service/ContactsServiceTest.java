package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.ContactsFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.ContactsFactoryImpl;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.repository.BookingRepository;
import za.ac.cput.accommodationapp.repository.ContactsRepository;
import za.ac.cput.accommodationapp.service.Impl.ContactsServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class ContactsServiceTest extends AndroidTestCase {
    private ContactsServiceImpl contactsService;
    private ContactsFactory contactsFactory;
    private boolean isBound;
    private static final String TAG="CONTACTS TEST";


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), ContactsServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        contactsFactory = ContactsFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ContactsServiceImpl.ContactsServiceLocalBinder binder
                    = (ContactsServiceImpl.ContactsServiceLocalBinder)service;
            contactsService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {

        Contacts contacts = contactsFactory.createContacts("Vulombe@gmail.com", "098221232");
        Contacts newEntity = contactsService.save(contacts);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }



}
