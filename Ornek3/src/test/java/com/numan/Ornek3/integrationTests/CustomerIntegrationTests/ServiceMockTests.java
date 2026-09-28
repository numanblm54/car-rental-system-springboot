package com.numan.Ornek3.integrationTests.CustomerIntegrationTests;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.enums.DriversLicenseTypes;
import com.numan.Ornek3.testUtils.TestDataFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceMockTests {
	
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private CustomerService customerService;
    
    
    @Test
    void testGetCustomerById() throws Exception {

    	CustomerDTO customerDTO = TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);

        when(customerService.getCustomerDTOById(1))
                .thenReturn(customerDTO);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ahmet"))
                .andExpect(jsonPath("$.surName").value("Yılmaz"))
                .andExpect(jsonPath("$.age").value(25));
    }
    
    
    @Test
    void testGetAllCustomers() throws Exception{
    	CustomerDTO customerDTO1 = TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.A);
    	CustomerDTO customerDTO2 = TestDataFactory.newCustomerDTO(2, "Mehmet", "Kaya", 30,"12345678902", DriversLicenseTypes.B);
    	
    	List<CustomerDTO> customerDTOList = new ArrayList<>();
    	customerDTOList.add(0,customerDTO1);
    	customerDTOList.add(1,customerDTO2);
    	
    	when(customerService.getAllCustomers()).thenReturn(customerDTOList);
    	
    	mockMvc.perform(get("/all-customers-list"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].name").value("Ahmet"))
    	.andExpect(jsonPath("$[1].name").value("Mehmet"));
    	
    }

}