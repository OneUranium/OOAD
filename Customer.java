public abstract class Customer { 
    // Protected fields (accessible to subclasses)
    protected int customerId;
    protected String customerAddress;
    protected String emailAddress;

    // Constructor
    public Customer(int customerId, String customerAddress, String emailAddress) {
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.emailAddress = emailAddress;
    }

    // Abstract method (to be implemented by subclasses)
    public abstract String getCustomerType();

    // Getter methods
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // Setter methods
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
