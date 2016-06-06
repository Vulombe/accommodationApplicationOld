package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.repository.Impl.PaymentRepositoryImpl;
import za.ac.cput.accommodationapp.repository.PaymentRepository;
import za.ac.cput.accommodationapp.service.PaymentService;

public class PaymentServiceImpl extends Service implements PaymentService
{


    private final IBinder localBinder = new PaymentServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    PaymentRepository repository;
    public PaymentServiceImpl() {
        repository = new PaymentRepositoryImpl(App.getAppContext());
    }

    public class PaymentServiceLocalBinder extends Binder {
        public PaymentServiceImpl getService() {
            return PaymentServiceImpl.this;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> items = new ArrayList<>();
        Iterable<Payment> values = repository.findAll();
        for (Payment value : values) {
            items.add(value);
        }
        return items;
    }

    @Override
    public Payment getPaymentByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    @Override
    public List<Payment> getPaymentByAmountPaid(double cost) {
        List<Payment> items = new ArrayList<>();
        List<Payment> result = new ArrayList<>();
        items = getAllPayments();
        for(Payment payment: items)
        {
            if(payment.getAmountPaid() == cost)
            {
                result.add(payment);
            }
        }
        if(result == null)
            System.out.println("Found Nothing!");
        return result;
    }

    public Payment savePayment(Payment entity)
    {
        return repository.save(entity);
    }


}
