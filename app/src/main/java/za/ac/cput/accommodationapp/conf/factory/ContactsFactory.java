package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.Contacts;

/**
 * Created by student on 2016/04/02.
 */
public interface ContactsFactory
{
    public Contacts createContacts(String email, String cellNumber);
}
