package com.financial_services_task.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.math.BigInteger;

import lombok.Builder;
import lombok.Data;

@Data
@Table(schema = "country")
@Builder
public class CountryTaxDetails {

  @Column(name = "country_id")
  private BigInteger countryId;

  @Column(name = "country_name")
  private String countryName;

  @Column(name = "short_distance_limit")
  private Double shortDistanceLimit;

  @Column(name = "short_distance_tax")
  private Double shortDistanceTax;

  @Column(name = "long_distance_tax")
  private Double longDistanceTax;
}
