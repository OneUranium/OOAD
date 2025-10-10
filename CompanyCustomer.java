public class CompanyCustomer extends Customer {
    private String companyName;
    private String registrationNumber;

    // Constructor
    public CompanyCustomer(int customerId, String customerAddress, String emailAddress,
                           String companyName, String registrationNumber) {
        super(customerId, customerAddress, emailAddress);
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
    }

    // Implement abstract method
    public String getCustomerType() {
        return "Company";
    }

    // Getters
    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // Setters
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
