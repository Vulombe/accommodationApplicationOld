package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.repository.AdministratorRepository;
import za.ac.cput.accommodationapp.repository.Impl.AdministratorRepositoryImpl;
import za.ac.cput.accommodationapp.service.AdminService;

public class AdminServiceImpl extends Service implements AdminService{


    private final IBinder localBinder = new AdminServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    AdministratorRepository repository;

    private static AdminServiceImpl service = null;

    public static AdminServiceImpl getInstance() {
        if (service == null)
            service = new AdminServiceImpl();
        return service;
    }
    public class AdminServiceLocalBinder extends Binder {
        public AdminServiceImpl getService() {
            return AdminServiceImpl.this;
        }
    }
    public AdminServiceImpl()
    {
        repository = new AdministratorRepositoryImpl(App.getAppContext());
    }

    @Override
    public Administrator getAdminByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public Administrator saveAdmin(Administrator entity)
    {
        return repository.save(entity);
    }

    public Set<Administrator> findAll()
    {
        return repository.findAll();
    }
}

