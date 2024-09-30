package com.financial_services_task.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(schema = "transaction_data")
@Entity
public class TaxCalculationResponse {

  @Column(name = "invoice_number")
  @Id
  private BigInteger invoiceNumber;

  @Column(name = "ticket_price")
  private Double ticketPrice;

  @Column(name = "tax_percentage")
  private Double taxPercentage;

  @Column(name = "travel_date")
  private Date travelDate;

  @Column(name = "total_price_of_ticket")
  private Double totalPriceOfTicket;
}
