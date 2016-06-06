package za.ac.cput.accommodationapp.conf.factory;

import java.util.List;

import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;

/**
 * Created by student on 2016/04/02.
 */
public interface StudentFactory
{

    public Student createStudent( String validate, PersonDetails personDetails, Address address,
                                   List<Payment> payments, Room room);

}
