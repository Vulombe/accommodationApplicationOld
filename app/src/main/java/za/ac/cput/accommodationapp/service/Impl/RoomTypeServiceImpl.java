package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.Impl.RoomTypeRepositoryImpl;
import za.ac.cput.accommodationapp.repository.RoomTypeRepository;
import za.ac.cput.accommodationapp.service.RoomTypeService;

public class RoomTypeServiceImpl extends Service implements RoomTypeService
{


    private final IBinder localBinder = new RoomTypeServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }
    RoomTypeRepository repository;

    private static RoomTypeServiceImpl service = null;

    public static RoomTypeServiceImpl getInstance() {
        if (service == null)
            service = new RoomTypeServiceImpl();
        return service;
    }

    public RoomTypeServiceImpl()
    {
        repository = new RoomTypeRepositoryImpl(App.getAppContext());
    }
    public class RoomTypeServiceLocalBinder extends Binder {
        public RoomTypeServiceImpl getService() {
            return RoomTypeServiceImpl.this;
        }
    }
    @Override
    public RoomType getRoomTypeByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public RoomType saveRoomType(RoomType entity)
    {
        return repository.save(entity);
    }

    public Set<RoomType> findAll()
    {
        return repository.findAll();
    }
}
