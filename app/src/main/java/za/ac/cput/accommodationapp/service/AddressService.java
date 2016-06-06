package za.ac.cput.accommodationapp.service;

import android.content.Context;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.Address;


/**
 * Created by mphuser on 5/8/2016.
 */
public interface AddressService
{
    //this service returns Address details in any form you like
    Address getAddressByID(Long id);


    Address saveAddress(Address entity);

    Set<Address> findAll();
}
