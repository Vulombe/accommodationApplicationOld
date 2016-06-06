package za.ac.cput.accommodationapp.service;

import android.content.Context;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.Contacts;


/**
 * Created by mphuser on 5/8/2016.
 */
public interface ContactsService
{
   //this service returns Contact details in any form you like
   Contacts getContactsByID(Long id);

   Contacts save(Contacts entity);

   Set<Contacts> findAll();
}
