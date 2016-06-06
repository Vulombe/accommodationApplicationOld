package za.ac.cput.accommodationapp.conf.factory;

import java.util.Map;

import za.ac.cput.accommodationapp.domain.Address;

/**
 * Created by student on 2016/04/02.
 */
public interface AddressFactory
{
    public Address createAddress(Map<String, String> values,
                                 String street, String cityCode);
}
