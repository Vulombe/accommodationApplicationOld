package za.ac.cput.accommodationapp.conf.factory;

import java.util.Date;
import java.util.Map;

import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by student on 2016/04/02.
 */
public interface PersonDetailsFactory
{
    public PersonDetails createPersonDetails(Map<String,String> names,Map<String,String> details,
                                             Date dob);
}
