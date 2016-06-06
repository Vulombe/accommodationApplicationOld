package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class Location implements Serializable
{
    private Long locationID;
    private String buildingName;
    private Address address;

    private Location(){}
    public Location(Builder builder)
    {
        locationID = builder.locationID;
        buildingName = builder.buildingName;
        address = builder.address;
    }
    public Long getLocationID() {
        return locationID;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public Address getAddress() {
        return address;
    }

    public static class Builder
    {
        private Long locationID;
        private String buildingName;
        private Address address;

        public Builder locationID(Long value)
        {
            this.locationID = value;
            return this;
        }
        public Builder buildingName(String value){
            this.buildingName = value;
            return this;
        }
        public Builder address(Address value)
        {
            this.address = value;
            return this;
        }
        public Builder copy(Location value)
        {
            this.locationID = value.getLocationID();
            this.buildingName = value.getBuildingName();
            this.address = value.getAddress();
            return this;
        }
        public Location build(){return new Location(this);}
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        if (!locationID.equals(location.locationID)) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return locationID.hashCode();
    }

}
