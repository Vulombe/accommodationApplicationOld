package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.Impl.PersonDetailsRepositoryImpl;
import za.ac.cput.accommodationapp.repository.PersonDetailsRepository;
import za.ac.cput.accommodationapp.service.PersonDetailsService;

public class PersonDetailsServiceImpl extends Service implements PersonDetailsService{


    private final IBinder localBinder = new PersonDetailsServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    PersonDetailsRepository repository;

    private static PersonDetailsServiceImpl service = null;

    public static PersonDetailsServiceImpl getInstance() {
        if (service == null)
            service = new PersonDetailsServiceImpl();
        return service;
    }

    public class PersonDetailsServiceLocalBinder extends Binder {
        public PersonDetailsServiceImpl getService() {
            return PersonDetailsServiceImpl.this;
        }
    }

    public PersonDetailsServiceImpl()
    {
        repository = new PersonDetailsRepositoryImpl(App.getAppContext());
    }

    @Override
    public PersonDetails getDetailsByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    @Override
    public Set<PersonDetails> getAllDetails()
    {
        return repository.findAll();
    }

    @Override
    public PersonDetails savePersonDetails(PersonDetails entity)
    {
        return repository.save(entity);
    }
}
