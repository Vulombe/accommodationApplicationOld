package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class Contacts implements Serializable
{
    private String email;
    private String cellNumber;
    private Long ID;


    private Contacts()
    {}
    public Contacts(Builder builder) {
        email=builder.email;
        cellNumber = builder.cellNumber;
        ID = builder.ID;
    }
    public String getEmail() {
        return email;
    }
    public String getCellNumber() {
        return cellNumber;
    }
    public Long getID() {
        return ID;
    }
    public static class Builder{
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
        public Builder ID(Long value)
        {
            this.ID = value;
            return this;
        }
        public Builder copy(Contacts value){
            this.cellNumber=value.getCellNumber();
            this.email=value.getEmail();
            this.ID = value.getID();
            return this;
        }
        public Contacts build(){
            return new Contacts(this);
        }
    }
}
