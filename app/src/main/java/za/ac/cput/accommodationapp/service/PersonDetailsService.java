package za.ac.cput.accommodationapp.service;

import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface PersonDetailsService
{
    //this service returns PersonDetails details in any form you like
    Set<PersonDetails> getAllDetails();
    PersonDetails getDetailsByID(Long id);
    PersonDetails savePersonDetails(PersonDetails entity);

}
