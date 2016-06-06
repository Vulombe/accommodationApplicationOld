package za.ac.cput.accommodationapp.service;

import za.ac.cput.accommodationapp.domain.PaymentMethod;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface PaymentMethodService {
    //this service returns PaymentMethod details in any form you like
    PaymentMethod getItemByID(Long id);
}
