package za.ac.cput.accommodationapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import za.ac.cput.accommodationapp.factory.TestAdministrator;
import za.ac.cput.accommodationapp.factory.TestPayment;
import za.ac.cput.accommodationapp.factory.TestRoom;
import za.ac.cput.accommodationapp.factory.TestStudent;
import za.ac.cput.accommodationapp.factory.TestUsers;


/**
 * Created by 212068075 on 4/17/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAdministrator.class,
        TestPayment.class,
        TestRoom.class,
        TestStudent.class,
        TestUsers.class})
public class AppTestSuite {
}
