package za.ac.cput.accommodationapp.service;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.test.AndroidTestCase;
import android.content.ServiceConnection;
import android.content.ComponentName;

import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;

import za.ac.cput.accommodationapp.conf.factory.Impl.AddressFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.Impl.PaymentMethodFactoryImpl;
import za.ac.cput.accommodationapp.conf.factory.PaymentFactory;
import za.ac.cput.accommodationapp.conf.factory.PaymentMethodFactory;
import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;
import za.ac.cput.accommodationapp.repository.PaymentRepository;
import za.ac.cput.accommodationapp.service.Impl.AddressServiceImpl;
import za.ac.cput.accommodationapp.service.Impl.CalculateStudentBalanceServiceImpl;

/**
 * Created by thembamalungani on 16/06/05.
 */
public class CalculateStudentBalanceServiceTest extends AndroidTestCase
{
    private CalculateStudentBalanceServiceImpl calculateStudentBalanceService;
    private boolean isBound;
    private PaymentRepository paymentRepository;
    private PaymentFactory paymentFactory;
    private Long idPayment;
    private PaymentMethodFactory paymentMethodFactory;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), CalculateStudentBalanceServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        paymentFactory = PaymentFactoryImpl.getInstance();
        paymentMethodFactory = PaymentMethodFactoryImpl.getInstance();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CalculateStudentBalanceServiceImpl.CalculateServiceLocalBinder binder
                    = (CalculateStudentBalanceServiceImpl.CalculateServiceLocalBinder)service;
            calculateStudentBalanceService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCalculateBalance() throws Exception {
        PaymentMethod paymentMethod = paymentMethodFactory.createPaymentMethod("Cheque");
        Payment payment = paymentFactory.createPayment(10000.00,paymentMethod,40000.00);
        double amountPaid = payment.getAmountPaid();
        double currentBalance = payment.getStudentBalance();
       double remainingBalance =  calculateStudentBalanceService.calculateStudentBalance(amountPaid,payment);

    }
}
