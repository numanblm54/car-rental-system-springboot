package com.numan.Ornek3.integrationTests.CustomerIntegrationTests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.enums.DriversLicenseTypes;
import com.numan.Ornek3.testUtils.TestDataFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryMockTests {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private CustomerRepository customerRepository;
    
    
    @Test
    void testGetCustomerById() throws Exception {
    	
    	Customer customer = TestDataFactory.newCustomer(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);
    	
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        
    	
        mockMvc.perform(get("/customer/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Ahmet"))
        .andExpect(jsonPath("$.surName").value("Yılmaz"))
        .andExpect(jsonPath("$.age").value(25));
    }
    
    
    @Test
    void testGetAllCustomers() throws Exception {

    	Customer customer1 = TestDataFactory.newCustomer(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

        Customer customer2 = TestDataFactory.newCustomer(2, "Mehmet", "Kaya", 30,"12345678902", DriversLicenseTypes.B);

        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        mockMvc.perform(get("/all-customers-list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Ahmet"))
                .andExpect(jsonPath("$[0].surName").value("Yılmaz"))
                .andExpect(jsonPath("$[0].age").value(25))
                .andExpect(jsonPath("$[1].name").value("Mehmet"))
                .andExpect(jsonPath("$[1].surName").value("Kaya"))
                .andExpect(jsonPath("$[1].age").value(30));
    }
    
    
    @Test
    void testGetCustomerByIdNotFound() throws Exception {

        when(customerRepository.findById(99))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/customer/99"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message")
                .value("The customer wasn't found."));
    }
    
    
    @Test
    void testDeleteCustomer() throws Exception{
    	
    	Customer customer = TestDataFactory.newCustomer(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
    	
        mockMvc.perform(delete("/delete-customer/1"))
        .andExpect(status().isOk());
        verify(customerRepository).delete(customer);
    }

}
