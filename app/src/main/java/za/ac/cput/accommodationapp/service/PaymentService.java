package za.ac.cput.accommodationapp.service;

import java.util.Date;
import java.util.List;

import za.ac.cput.accommodationapp.domain.Payment;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface PaymentService {
    //this service returns Payments details in any form you like
    List<Payment> getAllPayments();
    Payment getPaymentByID(Long id);
    Payment savePayment(Payment entity);
    List<Payment> getPaymentByAmountPaid(double amount);



}
