package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by student on 2016/04/01.
 */
public class Booking implements Serializable
{
    private Date date;
    private String numOfDays;
    private Long ID;

    private Booking(){}
    public Booking(Builder builder)
    {
        date = builder.date;
        numOfDays = builder.numOfDays;
        ID = builder.ID;
    }
    public String getNumOfDays() {
        return numOfDays;
    }
    public Date getDate() {
        return date;
    }
    public Long getID() {
        return ID;
    }
    public static class Builder
    {
        private Date date;
        private String numOfDays;
        private Long ID;

        public Builder date(Date value)
        {
            this.date = value;
            return this;
        }
        public Builder ID(Long value)
        {
            this.ID = value;
            return this;
        }
        public Builder numOfDays(String value)
        {
            this.numOfDays = value;
            return this;
        }
        public Builder copy(Booking value)
        {
            this.numOfDays = value.getNumOfDays();
            this.date = value.getDate();
            this.ID = value.getID();
            return this;
        }

        public Booking build(){return new Booking(this);}
    }
}
