package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.repository.Impl.RoomRepositoryImpl;
import za.ac.cput.accommodationapp.repository.RoomRepository;
import za.ac.cput.accommodationapp.service.RoomService;

public class RoomServiceImpl extends Service implements RoomService{


    private final IBinder localBinder = new RoomServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    RoomRepository repository;

    private static RoomServiceImpl service = null;
    public class RoomServiceLocalBinder extends Binder {
        public RoomServiceImpl getService() {
            return RoomServiceImpl.this;
        }
    }


    public static RoomServiceImpl getInstance() {
        if (service == null)
            service = new RoomServiceImpl();
        return service;
    }

    public RoomServiceImpl()
    {
        repository = new RoomRepositoryImpl(App.getAppContext());
    }

    @Override
    public Room getRoomByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public Room saveRoom(Room entity)
    {
        return repository.save(entity);
    }

    public Set<Room> findAll()
    {
        return repository.findAll();
    }

}
