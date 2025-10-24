public abstract class Customer {
    protected int customerId;
    protected String customerAddress;
    protected String emailAddress;

    public Customer(int customerId, String customerAddress, String emailAddress) {
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.emailAddress = emailAddress;
    }

    public abstract String getCustomerType();

    public int getCustomerId() { return customerId; }
    public String getCustomerAddress() { return customerAddress; }
    public String getEmailAddress() { return emailAddress; }

    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}