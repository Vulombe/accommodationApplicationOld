package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public abstract class Validation implements Serializable
{
    Validation nextAverage;

    public void setNextAverage(Validation nextAverage)
    {
        this.nextAverage = nextAverage;
    }

    public abstract String getAverage(int sum, int subjectNumber);
}
