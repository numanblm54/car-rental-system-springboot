package com.numan.Ornek3.Models;



public interface RentalResponse {
    void setCar(CarResponse car);
    void setCustomer(CustomerResponse customer);
    void setDailyRentalPrice(DailyRentalPriceResponse price);

}
