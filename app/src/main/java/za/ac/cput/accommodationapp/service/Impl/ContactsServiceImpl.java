package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.Impl.ContactsFactoryImpl;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.repository.ContactsRepository;
import za.ac.cput.accommodationapp.repository.Impl.ContactsRepositoryImpl;
import za.ac.cput.accommodationapp.service.ContactsService;

public class ContactsServiceImpl extends Service implements ContactsService{


    private final IBinder localBinder = new ContactsServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    ContactsRepository repository;

    private static ContactsServiceImpl service = null;

    public static ContactsServiceImpl getInstance() {
        if (service == null)
            service = new ContactsServiceImpl();
        return service;
    }

    public class ContactsServiceLocalBinder extends Binder {
        public ContactsServiceImpl getService() {
            return ContactsServiceImpl.this;
        }
    }
    public ContactsServiceImpl() {
        repository = new ContactsRepositoryImpl(App.getAppContext());
    }

    public Contacts getContactsByID(Long id)
    {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public Contacts save(Contacts entity)
    {
        return repository.save(entity);
    }

    public Set<Contacts> findAll()
    {
        return repository.findAll();
    }
}
