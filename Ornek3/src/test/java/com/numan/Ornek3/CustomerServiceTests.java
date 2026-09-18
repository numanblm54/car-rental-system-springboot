package com.numan.Ornek3;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.CustomerRequest;
import com.numan.Ornek3.Models.CustomerResponse;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.Services.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
	

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    
    @Test
    void testAddCustomerNationalCardNotExists() {
    	
    	CustomerRequest request=new CustomerRequest();
        request.setName("Ahmet");
        request.setSurName("Yılmaz");
        request.setAge(25);
        request.setNationalCardNo("12345678901");
        request.setDriversLicenseType(DriversLicenseTypes.A);
        
        when(customerRepository.findByNationalCardNo("12345678901")).thenReturn(null);
        
        Customer savedCustomer = new Customer();

        savedCustomer.setId(1);
        savedCustomer.setName("Ahmet");
        savedCustomer.setSurName("Yılmaz");
        savedCustomer.setAge(25);
        savedCustomer.setNationalCardNo("12345678901");
        
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        
        CustomerResponse response =
                customerService.addCustomer(request);
        System.out.println("TEST ÇALIŞTI");
        
        assertNotNull(response);
        assertEquals("Ahme", response.getName());
        assertEquals("Yılmaz", response.getSurName());
        assertEquals(25, response.getAge());
    }

}
