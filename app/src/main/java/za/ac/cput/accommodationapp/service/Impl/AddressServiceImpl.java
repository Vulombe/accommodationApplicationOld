package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.repository.AddressRepository;
import za.ac.cput.accommodationapp.repository.Impl.AddressRepositoryImpl;
import za.ac.cput.accommodationapp.service.AddressService;

public class AddressServiceImpl extends Service implements AddressService
{
    private AddressRepository repository;

    private final IBinder localBinder = new AddressServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }




    private static AddressServiceImpl service = null;
    public AddressServiceImpl() {

        repository = new AddressRepositoryImpl(App.getAppContext());
    }

    public static AddressServiceImpl getInstance() {
        if (service == null)
            service = new AddressServiceImpl();
        return service;
    }
    public class AddressServiceLocalBinder extends Binder {
        public AddressServiceImpl getService() {
            return AddressServiceImpl.this;
        }
    }


    @Override
    public Address getAddressByID(Long id)
    {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public Address saveAddress(Address entity)
    {
        return repository.save(entity);
    }

    public  Set<Address> findAll()
    {
        return repository.findAll();
    }

}
