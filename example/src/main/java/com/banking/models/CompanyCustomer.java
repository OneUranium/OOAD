package controllers.models;

public class CompanyCustomer extends Customer {
    private String companyName;
    private String registrationNumber;

    public CompanyCustomer(int customerId, String companyName, String registrationNumber,
                           String customerAddress, String emailAddress) {
        super(customerId, customerAddress, emailAddress);
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String getCustomerType() {
        return "Company";
    }

    public String getCompanyName() { return companyName; }
    public String getRegistrationNumber() { return registrationNumber; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
}
