package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by student on 2016/04/02.
 */
public interface PaymentFactory
{
    public Payment createPayment(double amountPaid, PaymentMethod paymentMethod,double studentBalance);
}
