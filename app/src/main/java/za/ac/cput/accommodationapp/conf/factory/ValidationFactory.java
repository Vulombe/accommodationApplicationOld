package za.ac.cput.accommodationapp.conf.factory;

import za.ac.cput.accommodationapp.domain.AverageHandlerA;
import za.ac.cput.accommodationapp.domain.AverageHandlerB;
import za.ac.cput.accommodationapp.domain.AverageHandlerC;
import za.ac.cput.accommodationapp.domain.Validation;

/**
 * Created by student on 2016/04/06.
 */
public class ValidationFactory
{
    public static String getAverage(int sum, int subjectNumber){
        Validation chain = setUpChain();
        return chain.getAverage(sum,subjectNumber);
    }
    public static Validation setUpChain(){
        AverageHandlerA a = new AverageHandlerA();
        AverageHandlerB b = new AverageHandlerB();
        AverageHandlerC c = new AverageHandlerC();
        a.setNextAverage(b);
        b.setNextAverage(c);
        return a;
    }
}
