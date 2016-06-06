package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.PaymentFactory;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by student on 2016/04/02.
 */
public class PaymentFactoryImpl implements PaymentFactory
{
    private static PaymentFactoryImpl paymentFactory = null;

    private PaymentFactoryImpl() {
    }
    public static PaymentFactoryImpl getInstance(){
        if(paymentFactory ==null)
            paymentFactory = new PaymentFactoryImpl();
        return paymentFactory;
    }

    @Override
    public Payment createPayment(double amountPaid, PaymentMethod paymentMethod,double studentBalance)
    {
        Payment payment = new Payment.Builder().paymentMethod(paymentMethod)
                                     .amountPaid(amountPaid)
                                     .studentBalance(studentBalance)
                                     .build();
        return payment;
    }
}
