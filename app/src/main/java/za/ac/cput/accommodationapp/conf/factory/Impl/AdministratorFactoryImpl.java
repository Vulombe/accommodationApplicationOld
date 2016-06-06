package za.ac.cput.accommodationapp.conf.factory.Impl;

import java.util.List;

import za.ac.cput.accommodationapp.conf.factory.AdministratorFactory;
import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by student on 2016/04/02.
 */
public class AdministratorFactoryImpl implements AdministratorFactory
{
    private static AdministratorFactoryImpl administratorFactory = null;

    private AdministratorFactoryImpl(){}
    public static AdministratorFactoryImpl getInstance(){
        if(administratorFactory ==null)
            administratorFactory = new AdministratorFactoryImpl();
        return administratorFactory;
    }
    @Override
    public Administrator createAdmin(PersonDetails personDetails, List<Location> locations,
                                      Contacts contacts)
    {
        Administrator administrator = new Administrator.Builder().personDetails(personDetails)
                .contacts(contacts)
                                            .locations(locations)
                                            .build();
        return administrator;
    }
}
