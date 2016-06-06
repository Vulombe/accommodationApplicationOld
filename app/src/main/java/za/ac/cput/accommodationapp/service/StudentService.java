package za.ac.cput.accommodationapp.service;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.Student;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface StudentService {
    //this service returns Students details in any form you like
    Student getStudentByID(Long id);

    Student saveStudent(Student entity);

    Set<Student> findAll();
}
