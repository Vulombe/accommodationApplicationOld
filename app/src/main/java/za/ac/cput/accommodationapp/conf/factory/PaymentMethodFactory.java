package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by student on 2016/04/02.
 */
public interface PaymentMethodFactory
{
    public PaymentMethod createPaymentMethod(String paymentMethod);
}
