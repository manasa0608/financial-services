package com.financial_services_task.interview.services;

import com.financial_services_task.interview.entity.CountryTaxDetails;
import com.financial_services_task.interview.entity.TaxCalculationRequest;
import com.financial_services_task.interview.entity.TaxCalculationResponse;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculationService {
  Map<BigInteger, TaxCalculationResponse> taxTransactionDetails = new HashMap<>();

  public TaxCalculationResponse calculateTaxForTravel(TaxCalculationRequest taxCalculationRequest) {

    // country details stored in map - need to be moved to a database table
    Map<String, CountryTaxDetails> countryTaxDetailsMap = new HashMap<>();

    countryTaxDetailsMap.put(
        "germany",
        CountryTaxDetails.builder()
            .countryId(BigInteger.valueOf(1))
            .countryName("germany")
            .longDistanceTax(19.0)
            .shortDistanceTax(7.0)
            .shortDistanceLimit(100.0)
            .build());

    countryTaxDetailsMap.put(
        "austria",
        CountryTaxDetails.builder()
            .countryId(BigInteger.valueOf(2))
            .countryName("austria")
            .longDistanceTax(10.0)
            .shortDistanceTax(9.0)
            .shortDistanceLimit(100.0)
            .build());

    TaxCalculationResponse taxCalculationResponse = new TaxCalculationResponse();

    try {
      CountryTaxDetails countryDetails =
          countryTaxDetailsMap.get(taxCalculationRequest.getCountry().toLowerCase());

      Calendar instance = Calendar.getInstance();
      instance.set(Calendar.MONTH, 07);
      instance.set(Calendar.DATE, 01);
      instance.set(Calendar.YEAR, 2029);
      Date taxedDate = instance.getTime();

      // get each countries tax according to the short/long distance
      if (taxCalculationRequest.getDateOfTravel().before(taxedDate)) {
        taxCalculationResponse.setTaxPercentage(0d);
      } else if (taxCalculationRequest.getTravelDistance()
          <= countryDetails.getShortDistanceLimit()) {
        taxCalculationResponse.setTaxPercentage(countryDetails.getShortDistanceTax());
      } else {
        taxCalculationResponse.setTaxPercentage(countryDetails.getLongDistanceTax());
      }

      Double totalAmountWithTax =
          computeTax(
              taxCalculationResponse.getTaxPercentage(), taxCalculationRequest.getTicketPrice());

      // insert the transactional data into the table - temporarily local storage in map
      taxCalculationResponse.setTravelDate(taxCalculationRequest.getDateOfTravel());
      taxCalculationResponse.setInvoiceNumber(taxCalculationRequest.getInvoiceNumber());
      taxCalculationResponse.setTotalPriceOfTicket(totalAmountWithTax);
      taxCalculationResponse.setTicketPrice(taxCalculationRequest.getTicketPrice());

      taxTransactionDetails.put(taxCalculationResponse.getInvoiceNumber(), taxCalculationResponse);
      System.out.println(taxTransactionDetails);
      return taxCalculationResponse;
    } catch (Exception exception) {
      throw new RuntimeException(exception.getCause());
    }
  }

  private Double computeTax(Double taxPercentage, Double amount) {
    return amount + (taxPercentage * amount / 100);
  }
}
