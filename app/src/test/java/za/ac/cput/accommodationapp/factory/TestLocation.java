package za.ac.cput.accommodationapp.factory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import za.ac.cput.accommodationapp.conf.factory.AddressFactory;
import za.ac.cput.accommodationapp.conf.factory.Impl.AddressFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.LocationFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.LocationFactory;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Location;

/**
 * Created by student on 2016/04/02.
 */
public class TestLocation
{
    private AddressFactory addressFactory;
    private LocationFactory locationFactory;
    private Address address;
    private Location location;

    @Before
    public void setUpLocation() throws Exception
    {
        addressFactory = AddressFactoryImpl.getInstance();
        locationFactory = LocationFactoryImpl.getInstance();
    }

    @Test
    public void testCreateUpdateLocation() throws Exception
    {
        Map<String,String> addressDetails = new HashMap<String, String>();
        String buildingName = "NMJ";
        addressDetails.put("Western Cape","Cape Town");
        address = addressFactory.createAddress(addressDetails,"Dorset","8001");
        location = locationFactory.createLocation(buildingName, address);

        Assert.assertEquals(location.getBuildingName(), "NMJ");


        Location updateLocation = new Location.Builder().copy(location).buildingName("NMJ2").build();
        Assert.assertEquals(updateLocation.getBuildingName(),"NMJ2");
    }

    @Test
    public void testUpdateLocation() throws Exception {
    }

}
