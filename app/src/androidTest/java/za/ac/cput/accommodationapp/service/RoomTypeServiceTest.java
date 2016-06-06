package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import za.ac.cput.accommodationapp.conf.factory.Impl.RoomTypeFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.RoomTypeFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.service.Impl.RoomTypeServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class RoomTypeServiceTest extends AndroidTestCase {

    private RoomTypeServiceImpl roomTypeService;
    private RoomTypeFactory roomTypeFactory;
    private boolean isBound;
    private static final String TAG="ROOMTYPE TEST";


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), RoomTypeServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        roomTypeFactory = RoomTypeFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RoomTypeServiceImpl.RoomTypeServiceLocalBinder binder
                    = (RoomTypeServiceImpl.RoomTypeServiceLocalBinder)service;
            roomTypeService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {

        RoomType roomType = roomTypeFactory.createRoomType("Available","Double");
        RoomType newEntity = roomTypeService.saveRoomType(roomType);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }
}
