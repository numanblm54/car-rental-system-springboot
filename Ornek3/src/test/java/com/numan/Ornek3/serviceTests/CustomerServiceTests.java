package com.numan.Ornek3.serviceTests;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.enums.DriversLicenseTypes;
import com.numan.Ornek3.exception.MyException;
import com.numan.Ornek3.mapper.CustomerMapper;
import com.numan.Ornek3.testUtils.TestDataFactory;   

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
	
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    
    @Mock
    private CustomerMapper customerMapper;
    
    
    @Test
    void testAddCustomer() {
    	
    	CustomerDTO customerDTO= TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

        Customer customer = TestDataFactory.newCustomer(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

       	CustomerDTO savedCustomerDTO = TestDataFactory.savedCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);
	
       	when(customerRepository.findByNationalCardNo("12345678901")).thenReturn(null);
        
        when(customerMapper.mapToCustomer(customerDTO)).thenReturn(customer);
       
       	when(customerMapper.mapToCustomerDTO(customer)).thenReturn(savedCustomerDTO);
       	
       	CustomerDTO result = customerService.addCustomer(customerDTO);
                
        assertNotNull(result);
        assertEquals("Ahmet", result.getName());
        assertEquals("Yılmaz", result.getSurName());
        assertEquals(25, result.getAge());
   }
    
    
    @Test
    void testAddCustomerWithNationalCardNoError() {
    	
    	CustomerDTO customerDTO = TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

        Customer customer = TestDataFactory.newCustomer(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

       	when(customerRepository.findByNationalCardNo("12345678901")).thenReturn(customer);
       	
        MyException exception = assertThrows(MyException.class, () -> customerService.addCustomer(customerDTO));
              
        assertEquals("There is a customer who uses this national card no.", exception.getMessage());
   }
}