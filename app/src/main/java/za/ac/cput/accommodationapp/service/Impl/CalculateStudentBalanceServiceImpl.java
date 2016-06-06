package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.Student;
import za.ac.cput.accommodationapp.repository.Impl.PaymentRepositoryImpl;
import za.ac.cput.accommodationapp.repository.Impl.StudentRepositoryImpl;
import za.ac.cput.accommodationapp.repository.PaymentRepository;
import za.ac.cput.accommodationapp.repository.StudentRepository;
import za.ac.cput.accommodationapp.service.CalculateStudentBalanceService;

public class CalculateStudentBalanceServiceImpl extends Service implements CalculateStudentBalanceService
{


    private final IBinder localBinder = new CalculateServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }


    PaymentRepository paymentRepository;


    public class CalculateServiceLocalBinder extends Binder {
        public CalculateStudentBalanceServiceImpl getService() {
            return CalculateStudentBalanceServiceImpl.this;
        }
    }

    private static CalculateStudentBalanceServiceImpl service = null;

    public static CalculateStudentBalanceServiceImpl getInstance() {
        if (service == null)
            service = new CalculateStudentBalanceServiceImpl();
        return service;
    }

    public CalculateStudentBalanceServiceImpl()
    {
        paymentRepository =  new PaymentRepositoryImpl(App.getAppContext());
    }


    public double calculateStudentBalance(double amountPaid,Payment entity)
    {
        Long idPayment = entity.getPaymentNumber();
        entity =  paymentRepository.findById(idPayment);
        double currentBalance =  entity.getStudentBalance();
        return currentBalance - amountPaid;
    }
    public Payment updatePayment(Payment entity)
    {
        return paymentRepository.update(entity);
    }

}
