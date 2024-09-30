package com.financial_services_task.interview.controllers;

import com.financial_services_task.interview.entity.TaxCalculationRequest;
import com.financial_services_task.interview.entity.TaxCalculationResponse;
import com.financial_services_task.interview.services.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxCalculationController {

  @Autowired TaxCalculationService taxCalculationService;

  @PostMapping("/ticket-price")
  public TaxCalculationResponse getTotalPriceOfTicketWithTax(
      @RequestBody TaxCalculationRequest taxCalculationRequest) {
    return taxCalculationService.calculateTaxForTravel(taxCalculationRequest);
  }
}
