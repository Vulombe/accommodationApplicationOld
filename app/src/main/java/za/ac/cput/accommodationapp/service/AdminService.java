package za.ac.cput.accommodationapp.service;

import java.util.Set;

import za.ac.cput.accommodationapp.domain.Administrator;

/**
 * Created by mphuser on 5/8/2016.
 */
public interface AdminService {
    //this service returns Administrator details in any form you like

    Administrator getAdminByID(Long id);

    Administrator saveAdmin(Administrator entity);

    Set<Administrator> findAll();
}
