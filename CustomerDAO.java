import java.io.*;
import java.util.*;

public class CustomerDAO {
    private static final String CUSTOMERS_FILE = "customers.txt";
    
    // CREATE - Add new customer
    public void saveCustomer(Customer customer) {
        List<Customer> customers = getAllCustomers();
        customers.add(customer);
        saveAllCustomers(customers);
    }
    
    // READ - Get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = parseCustomer(line);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing customer data found.");
        }
        return customers;
    }
    
    // READ - Find customer by ID
    public Customer getCustomerById(int customerId) {
        return getAllCustomers().stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }
    
    // READ - Find customer by email
    public Customer getCustomerByEmail(String email) {
        return getAllCustomers().stream()
                .filter(c -> c.getEmailAddress().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
    
    // UPDATE - Update customer
    public void updateCustomer(Customer updatedCustomer) {
        List<Customer> customers = getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == updatedCustomer.getCustomerId()) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
        saveAllCustomers(customers);
    }
    
    // DELETE - Remove customer
    public void deleteCustomer(int customerId) {
        List<Customer> customers = getAllCustomers();
        customers.removeIf(c -> c.getCustomerId() == customerId);
        saveAllCustomers(customers);
    }
    
    // Helper method to save all customers
    private void saveAllCustomers(List<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                writer.println(convertCustomerToString(customer));
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
    
    // Helper method to parse customer from string
    private Customer parseCustomer(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 6) {
            int customerId = Integer.parseInt(parts[0]);
            String type = parts[1];
            String address = parts[2];
            String email = parts[3];
            
            if ("INDIVIDUAL".equals(type)) {
                return new IndividualCustomer(customerId, address, email, parts[4], parts[5], parts[6]);
            } else if ("COMPANY".equals(type)) {
                return new CompanyCustomer(customerId, parts[4], parts[5], address, email);
            }
        }
        return null;
    }
    
    // Helper method to convert customer to string
    private String convertCustomerToString(Customer customer) {
        if (customer instanceof IndividualCustomer) {
            IndividualCustomer ind = (IndividualCustomer) customer;
            return String.join("|", 
                String.valueOf(ind.getCustomerId()),
                "INDIVIDUAL",
                ind.getCustomerAddress(),
                ind.getEmailAddress(),
                ind.getCustomerName(),
                ind.getCustomerSurname(),
                ind.getEmploymentInfo()
            );
        } else if (customer instanceof CompanyCustomer) {
            CompanyCustomer comp = (CompanyCustomer) customer;
            return String.join("|", 
                String.valueOf(comp.getCustomerId()),
                "COMPANY",
                comp.getCustomerAddress(),
                comp.getEmailAddress(),
                comp.getCompanyName(),
                comp.getRegistrationNumber(),
                ""
            );
        }
        return "";
    }
}