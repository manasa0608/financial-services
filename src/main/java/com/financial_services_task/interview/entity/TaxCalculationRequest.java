package com.financial_services_task.interview.entity;

import com.financial_services_task.interview.helpers.TicketCategory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class TaxCalculationRequest {

  private BigInteger invoiceNumber;
  private String client;
  private String address;
  private TicketCategory ticketCategory;
  private Date dateOfTravel;
  private String fromCity;
  private String toCity;
  private Double travelDistance;

  private String country;
  private Double ticketPrice;
}
