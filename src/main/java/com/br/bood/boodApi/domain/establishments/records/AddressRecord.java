package com.br.bood.boodApi.domain.establishments.records;

public record AddressRecord(String street, String neighborhood, Integer number, String city,
                            String zipCode) {}