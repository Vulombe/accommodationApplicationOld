package za.ac.cput.accommodationapp.service;

import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.Student;

/**
 * Created by thembamalungani on 16/06/04.
 */
public interface CalculateStudentBalanceService
{
    //This service calculates the student remaining balance as the student continue to make payments



    double calculateStudentBalance(double amountPaid, Payment entity);

     Payment updatePayment(Payment entity);


}
