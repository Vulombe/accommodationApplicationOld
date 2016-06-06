package za.ac.cput.accommodationapp.domain;

/**
 * Created by student on 2016/04/06.
 */
public class AverageHandlerC extends Validation
{
    public  String getAverage(int sum, int subjectNumber)
    {
        double average = sum/subjectNumber;

        if(average < 60)
            return "Allocate No Room";
        else
        if (nextAverage != null)
            return nextAverage.getAverage(sum,subjectNumber);
        else
            return "No Average";
    }
}
