package za.ac.cput.accommodationapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;
import za.ac.cput.accommodationapp.repository.Impl.PaymentRepositoryImpl;

/**
 * Created by 212068075 on 4/24/2016.
 */
public class PaymentRepositoryTest extends AndroidTestCase
{
    private static final String TAG="PAYMENT TEST";
    private Long id;



    public void testCreateReadUpdateDelete() throws Exception {
        PaymentRepository repo = new PaymentRepositoryImpl(this.getContext());
        // CREATE

        PaymentMethod paymentMethod = new PaymentMethod.Builder()
                .methodType("Cash")
                .build();


        Payment createEntity = new Payment.Builder()
                .amountPaid(1064.4)
                .build();

        Payment insertedEntity = repo.save(createEntity);
        id=insertedEntity.getPaymentNumber();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Payment> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Payment entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        PaymentMethod updatePaymentMethod = new PaymentMethod.Builder()
                .methodType("Cheque")
                .build();
        Payment updateEntity = new Payment.Builder()
                .copy(entity)
                .paymentMethod(updatePaymentMethod)
                .build();
        repo.update(updateEntity);
        Payment newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Cheque",updatePaymentMethod.getMethodType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Payment deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}
