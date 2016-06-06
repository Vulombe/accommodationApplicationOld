package za.ac.cput.accommodationapp.conf.factory.Impl;

import java.util.List;

import za.ac.cput.accommodationapp.conf.factory.StudentFactory;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;

/**
 * Created by student on 2016/04/02.
 */
public class StudentFactoryImpl implements StudentFactory
{
    private static StudentFactoryImpl studentFactory = null;

    private StudentFactoryImpl() {
    }
    public static StudentFactoryImpl getInstance(){
        if(studentFactory ==null)
            studentFactory = new StudentFactoryImpl();
        return studentFactory;
    }

    @Override
    public Student createStudent( String validate, PersonDetails personDetails, Address address,
                                  List<Payment> payments, Room room)
    {
        Student student = new Student.Builder().personDetails(personDetails)
                                    .address(address)
                                    .room(room)
                                    .payments(payments)
                                    .validate(validate)
                                    .build();
        return student;
    }
}
