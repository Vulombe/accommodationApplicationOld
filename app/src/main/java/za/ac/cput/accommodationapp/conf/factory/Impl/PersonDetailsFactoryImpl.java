package za.ac.cput.accommodationapp.conf.factory.Impl;

import java.util.Date;
import java.util.Map;

import za.ac.cput.accommodationapp.conf.factory.PersonDetailsFactory;
import za.ac.cput.accommodationapp.domain.PersonDetails;

/**
 * Created by student on 2016/04/02.
 */
public class PersonDetailsFactoryImpl implements PersonDetailsFactory
{
    private static PersonDetailsFactoryImpl personDetailsFactory = null;

    private PersonDetailsFactoryImpl() {
    }
    public static PersonDetailsFactoryImpl getInstance(){
        if(personDetailsFactory ==null)
            personDetailsFactory = new PersonDetailsFactoryImpl();
        return personDetailsFactory;
    }
    @Override
    public PersonDetails createPersonDetails(Map<String,String> names,Map<String,String> details,
                                                    Date dob)
    {
        PersonDetails personDetails = new PersonDetails.Builder()
                .fName(names.get("fName"))
                .lName(names.get("lName"))
                .gender(details.get("Gender"))
                .email(details.get("email"))
                .dob(dob)
                .build();
        return personDetails;

    }
}
