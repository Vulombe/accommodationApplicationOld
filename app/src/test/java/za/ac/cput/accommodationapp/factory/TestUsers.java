package za.ac.cput.accommodationapp.factory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import za.ac.cput.accommodationapp.conf.factory.UserTypeFactory;
import za.ac.cput.accommodationapp.domain.User;

/**
 * Created by student on 2016/04/06.
 */
public class TestUsers
{
    private UserTypeFactory userTypeFactory;

    @Before
    public void setUpUserType() throws Exception {
        userTypeFactory = UserTypeFactory.getUserTypeFactoryinstance();
    }

    @Test
    public void testUserType() throws Exception
    {
        User userType = userTypeFactory.userType("Administrator");
        Assert.assertNotNull(userType);
    }
}
