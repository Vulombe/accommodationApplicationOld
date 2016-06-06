package za.ac.cput.accommodationapp.domain;

import java.io.Serializable;

/**
 * Created by student on 2016/04/01.
 */
public class Payment implements Serializable
{
    private Long paymentNumber;
    private double amountPaid;
    private PaymentMethod paymentMethod;
    private double studentBalance;

    private Payment(){}
    public Payment (Builder builder)
    {
        paymentNumber = builder.paymentNumber;
        amountPaid = builder.amountPaid;
        paymentMethod = builder.paymentMethod;
        studentBalance = builder.studentBalance;
    }
    public Long getPaymentNumber() {
        return paymentNumber;
    }
    public double getAmountPaid() {
        return amountPaid;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getStudentBalance() {
        return studentBalance;
    }

    public static class Builder{
        private Long paymentNumber;
        private double amountPaid;
        private PaymentMethod paymentMethod;
        private double studentBalance;

        public Builder paymentNumber(Long value)
        {
            this.paymentNumber = value;
            return this;
        }
        public Builder amountPaid(double value)
        {
            this.amountPaid = value;
            return this;
        }

        public Builder paymentMethod(PaymentMethod value)
        {
            this.paymentMethod = value;
            return this;
        }
        public Builder studentBalance(double value)
        {
            this.studentBalance = value;
            return this;
        }

        public Builder copy(Payment value){
            this.amountPaid = value.getAmountPaid();
            this.paymentNumber = value.getPaymentNumber();
            this.paymentMethod = value.getPaymentMethod();
            this.studentBalance = value.getStudentBalance();
            return this;
        }
        public Payment build(){return new Payment(this);}
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        if (!paymentNumber.equals(payment.paymentNumber)) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return paymentNumber.hashCode();
    }

}
