package za.ac.cput.accommodationapp.domain;

/**
 * Created by student on 2016/04/06.
 */
public class AverageHandlerB extends Validation
{

    public  String getAverage(int sum, int subjectNumber)
    {
        double average = sum/subjectNumber;

        if(average > 60 & average < 75)
            return "Allocate Double Room";
        else
        if (nextAverage != null)
            return nextAverage.getAverage(sum,subjectNumber);
        else
            return "No Average";
    }
}
