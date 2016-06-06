package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by student on 2016/04/01.
 */
public class PersonDetails implements Serializable
{

    private String lName;
    private String fName;
    private String gender;
    private Date dob;
    private String email;
    private String cellNumber;
    private Long ID;

    private PersonDetails(){}
    public PersonDetails(Builder builder)
    {
        ID = builder.ID;
        lName = builder.lName;
        fName = builder.fName;
        gender = builder.gender;
        dob = builder.dob;
        email=builder.email;
        cellNumber = builder.cellNumber;
    }
    public Long getID() {
        return ID;
    }
    public String getlName() {
        return lName;
    }
    public String getfName() {
        return fName;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getCellNumber() {
        return cellNumber;
    }

    public Date getDob() {
        return dob;
    }
    public static class Builder
    {
        private String lName;
        private String fName;
        private String gender;
        private Date dob;
        private String email;
        private String cellNumber;
        private Long ID;

        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder cellNumber(String value){
            this.cellNumber=value;
            return this;
        }
        public Builder lName(String value){
            this.lName = value;
            return this;
        }
        public Builder ID(Long value)
        {
            this.ID = value;
            return this;
        }
        public Builder fName(String value){
            this.fName = value;
            return this;
        }
        public Builder gender(String value){
            this.gender = value;
            return this;
        }

        public Builder dob(Date value){
            this.dob = value;
            return this;
        }

        public Builder copy(PersonDetails value){
            this.fName = value.getfName();
            this.lName = value.getlName();
            this.gender = value.getGender();
            this.dob = value.getDob();
            this.cellNumber=value.getCellNumber();
            this.email=value.getEmail();
            this.ID = value.getID();
            return this;
        }
        public PersonDetails build(){
            return new PersonDetails(this);
        }
    }

}
