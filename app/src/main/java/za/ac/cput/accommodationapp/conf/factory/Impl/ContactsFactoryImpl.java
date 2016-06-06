package za.ac.cput.accommodationapp.conf.factory.Impl;

import za.ac.cput.accommodationapp.conf.factory.ContactsFactory;
import za.ac.cput.accommodationapp.domain.Contacts;

/**
 * Created by student on 2016/04/02.
 */
public class ContactsFactoryImpl implements ContactsFactory
{
    private static ContactsFactoryImpl contactsFactory = null;

    private ContactsFactoryImpl() {
    }
    public static ContactsFactoryImpl getInstance(){
        if(contactsFactory ==null)
            contactsFactory = new ContactsFactoryImpl();
        return contactsFactory;
    }

    @Override
    public Contacts createContacts(String email, String cellNumber)
    {
        Contacts contacts = new Contacts.Builder().cellNumber(cellNumber).email(email)
                                        .build();
        return contacts;
    }
}
