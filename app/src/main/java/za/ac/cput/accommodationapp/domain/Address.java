package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class Address implements Serializable
{
    private Long ID;
    private String province;
    private String city;
    private String street;
    private String cityCode;

    private Address(){}
    public Address(Builder builder){
        ID = builder.ID;
        province = builder.province;
        city = builder.city;
        street = builder.street;
        cityCode = builder.cityCode;
    }
    public String getProvince()
    {
        return province;
    }
    public String getStreet() {
        return street;
    }
    public String getCityCode() {
        return cityCode;
    }
    public String getCity() {
        return city;
    }

    public Long getID() {
        return ID;
    }

    public static class Builder
    {
        private Long ID;
        private String province;
        private String city;
        private String street;
        private String cityCode;
        public Builder city(String value)
        {
            this.city = value;
            return this;
        }
        public Builder street(String value)
        {
            this.street = value;
            return this;
        }
        public Builder ID(Long value)
        {
            this.ID = value;
            return this;
        }
        public Builder cityCode(String value)
        {
            this.cityCode = value;
            return this;
        }
        public Builder province(String value)
        {
            this.province = value;
            return this;
        }
        public Builder copy(Address value)
        {
            this.ID = value.getID();
            this.province = value.getProvince();
            this.city = value.getCity();
            this.street = value.getStreet();
            this.cityCode = value.getCityCode();
            return this;
        }
        public Address build(){return new Address(this);}
    }
}
