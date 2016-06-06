package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.AddressFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AddressFactoryImpl;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.repository.AddressRepository;
import za.ac.cput.accommodationapp.service.Impl.AddressServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class AddressServiceTest extends AndroidTestCase {
    private AddressServiceImpl addressService;
    private boolean isBound;
    private static final String TAG="ADDRESS TEST";
    private AddressFactory addressFactory;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), AddressServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        addressFactory = AddressFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AddressServiceImpl.AddressServiceLocalBinder binder
                    = (AddressServiceImpl.AddressServiceLocalBinder)service;
            addressService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {
        Map<String, String> addressDetails = new HashMap<>();

        addressDetails.put("Western Cape","Cape Town");

        Address createEntity =addressFactory.createAddress(addressDetails,"Dorset","8001");
        Address newEntity = addressService.saveAddress(createEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

}
