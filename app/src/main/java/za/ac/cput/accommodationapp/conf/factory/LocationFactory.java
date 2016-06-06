package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Location;

/**
 * Created by student on 2016/04/02.
 */
public interface LocationFactory
{
    public Location createLocation(String buildingName, Address bAddress);
}
