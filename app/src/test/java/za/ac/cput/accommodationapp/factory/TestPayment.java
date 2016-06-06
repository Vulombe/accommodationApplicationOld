package za.ac.cput.accommodationapp.factory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentMethodFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PaymentFactory;
import za.ac.cput.accommodationapp.conf.factory.PaymentMethodFactory;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by student on 2016/04/02.
 */
public class TestPayment
{


    private PaymentFactory paymentFactory;
    private Payment payment;
    private PaymentMethodFactory paymentMethodFactory;
    private PaymentMethod paymentMethod;

    @Before
    public void setUpPayment() throws Exception
    {
        paymentFactory = PaymentFactoryImpl.getInstance();
        paymentMethodFactory = PaymentMethodFactoryImpl.getInstance();
    }

    @Test
    public void testCreateUpdatePayment() throws Exception
    {
        paymentMethod = paymentMethodFactory.createPaymentMethod("Cheque");
        payment = paymentFactory.createPayment(2000.00, paymentMethod);

        Assert.assertEquals(payment.getAmountPaid(), 2000.00, .00);

        Payment updatePayment = new Payment.Builder().copy(payment).amountPaid(3000.00).build();
        Assert.assertEquals(updatePayment.getAmountPaid(),3000.00,.00);
    }

    @Test
    public void testUpdatePayment() throws Exception
    {

    }
}
