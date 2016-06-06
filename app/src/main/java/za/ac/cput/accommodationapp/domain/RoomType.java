package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class RoomType implements Serializable
{
    private String status;
    private String type;
    private Long ID;
    private RoomType()
    {}
    public RoomType(Builder builder)
    {

        ID = builder.ID;
        status = builder.status;
        type = builder.type;
    }
    public String getStatus() {
        return status;
    }
    public String getType() {
        return type;
    }
    public Long getID() {
        return ID;
    }
    public static class Builder{
        private String status;
        private String type;
        private Long ID;
        public Builder status(String value)
        {
            this.status = value;
            return this;
        }
        public Builder type(String value)
        {
            this.type = value;
            return this;
        }
        public Builder ID(Long value)
        {
            this.ID = value;
            return this;
        }
        public Builder copy(RoomType value)
        {
            this.status = value.getStatus();
            this.type = value.getType();

            this.ID = value.getID();
            return this;
        }
        public RoomType build(){return new RoomType(this);}
    }
}
