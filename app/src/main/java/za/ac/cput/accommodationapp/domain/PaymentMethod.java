package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class PaymentMethod implements Serializable
{
    private String methodType;

    private PaymentMethod(){}
    public PaymentMethod(Builder builder)
    {
        methodType = builder.methodType;

    }

    public String getMethodType() {
        return methodType;
    }

    public static class Builder
    {
        private String methodType;

        public Builder methodType(String value)
        {
            this.methodType = value;
            return this;
        }

        public Builder copy(PaymentMethod value)
        {
            this.methodType = value.getMethodType();
            return this;
        }
        public PaymentMethod build(){return new PaymentMethod(this);}
    }
}
