package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.LocationFactory;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Location;

/**
 * Created by student on 2016/04/02.
 */
public class LocationFactoryImpl implements LocationFactory
{
    private static LocationFactoryImpl locationFactory = null;

    private LocationFactoryImpl() {
    }
    public static LocationFactoryImpl getInstance(){
        if(locationFactory ==null)
            locationFactory = new LocationFactoryImpl();
        return locationFactory;
    }
    @Override
    public Location createLocation(String buildingName, Address bAddress)
    {
        Location location = new Location.Builder().address(bAddress).buildingName(buildingName)
                                        .build();
        return location;
    }
}
