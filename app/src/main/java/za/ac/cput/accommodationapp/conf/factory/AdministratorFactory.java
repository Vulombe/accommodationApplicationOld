package za.ac.cput.accommodationapp.conf.factory;

import java.util.List;

import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by student on 2016/04/02.
 */
public interface AdministratorFactory
{
    public Administrator createAdmin(PersonDetails personDetails,List<Location> locations,
                                     Contacts contacts);

}
