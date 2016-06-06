package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.User;
import za.ac.cput.accommodationapp.domain.UserAsAdministrator;
import za.ac.cput.accommodationapp.domain.UserAsStudent;

/**
 * Created by student on 2016/04/06.
 */
public class UserTypeFactory {
    private static UserTypeFactory userTypeFactory = null;

    private UserTypeFactory() {
    }

    public static UserTypeFactory getUserTypeFactoryinstance() {
        if (userTypeFactory == null)
            return new UserTypeFactory();
        return userTypeFactory;
    }

    public User userType(String userType) {
        if ("Administrator".equalsIgnoreCase(userType)) {
            return new UserAsAdministrator();
        } else {
            return new UserAsStudent();
        }
    }
}