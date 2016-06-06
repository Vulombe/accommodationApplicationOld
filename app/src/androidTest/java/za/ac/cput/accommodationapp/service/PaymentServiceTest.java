package za.ac.cput.accommodationapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentMethodFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PaymentFactory;
import za.ac.cput.accommodationapp.conf.factory.PaymentMethodFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;
import za.ac.cput.accommodationapp.repository.PaymentRepository;
import za.ac.cput.accommodationapp.service.Impl.PaymentServiceImpl;

/**
 * Created by 212068075 on 2016/05/08.
 */
public class PaymentServiceTest extends AndroidTestCase
{

    private PaymentServiceImpl paymentService;
    private PaymentFactory paymentFactory;
    private boolean isBound;
    private static final String TAG="PAYMENT TEST";
    private PaymentMethodFactory paymentMethodFactory;
    private final double BALANCE = 40000.00;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), PaymentServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        paymentFactory = PaymentFactoryImpl.getInstance();
        paymentMethodFactory = PaymentMethodFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PaymentServiceImpl.PaymentServiceLocalBinder binder
                    = (PaymentServiceImpl.PaymentServiceLocalBinder)service;
            paymentService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception {

       PaymentMethod paymentMethod = paymentMethodFactory.createPaymentMethod("Cheque");
        Payment payment = paymentFactory.createPayment(2000.00, paymentMethod,BALANCE);


    }

    public void testCreateAndFindByDate() throws Exception {
        PaymentMethod paymentMethod = paymentMethodFactory.createPaymentMethod("Cheque");
        Payment createEntity1 = paymentFactory.createPayment(3000.00, paymentMethod,BALANCE);

        PaymentMethod paymentMethod2 = paymentMethodFactory.createPaymentMethod("Cash");
        Payment createEntity2 = paymentFactory.createPayment(4000.00, paymentMethod2,BALANCE);

        PaymentMethod paymentMethod3 = paymentMethodFactory.createPaymentMethod("Cheque");
        Payment createEntity3 = paymentFactory.createPayment(5000.00, paymentMethod3,BALANCE);

        paymentService.savePayment(createEntity1);
        paymentService.savePayment(createEntity2);
        paymentService.savePayment(createEntity3);


        List<Payment> bookings = paymentService.getAllPayments();
        Assert.assertTrue(bookings.size() > 2);


    }
}
