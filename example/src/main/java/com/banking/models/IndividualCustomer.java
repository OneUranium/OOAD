package controllers.models;

public class IndividualCustomer extends Customer {
    private String customerName;
    private String customerSurname;
    private String employmentInfo;

    // Constructor for employed customers
    public IndividualCustomer(int customerId, String customerAddress, String emailAddress,
                              String customerName, String customerSurname, String employmentInfo) {
        super(customerId, customerAddress, emailAddress);
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.employmentInfo = employmentInfo;
    }

    // Constructor for unemployed customers
    public IndividualCustomer(int customerId, String customerAddress, String emailAddress,
                              String customerName, String customerSurname) {
        super(customerId, customerAddress, emailAddress);
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.employmentInfo = "Unemployed";
    }


    @Override  
    public String getCustomerType() {
        return "Individual";
    }

    // Getter methods
    public String getCustomerName() { return customerName; }
    public String getCustomerSurname() { return customerSurname; }
    public String getEmploymentInfo() { return employmentInfo; }

    // Setter methods
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerSurname(String customerSurname) { this.customerSurname = customerSurname; }
    public void setEmploymentInfo(String employmentInfo) { this.employmentInfo = employmentInfo; }

    
}