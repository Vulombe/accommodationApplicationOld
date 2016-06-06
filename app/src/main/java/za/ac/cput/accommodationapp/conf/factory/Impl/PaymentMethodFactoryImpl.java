package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.PaymentMethodFactory;
import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by student on 2016/04/02.
 */
public class PaymentMethodFactoryImpl implements PaymentMethodFactory
{
    private static PaymentMethodFactoryImpl paymentMethodFactory = null;

    private PaymentMethodFactoryImpl() {
    }
    public static PaymentMethodFactoryImpl getInstance(){
        if(paymentMethodFactory ==null)
            paymentMethodFactory = new PaymentMethodFactoryImpl();
        return paymentMethodFactory;
    }

    @Override
    public PaymentMethod createPaymentMethod(String methodType)
    {
        PaymentMethod paymentMethod = new PaymentMethod.Builder().methodType(methodType)
                                                        .build();
        return paymentMethod;
    }
}
