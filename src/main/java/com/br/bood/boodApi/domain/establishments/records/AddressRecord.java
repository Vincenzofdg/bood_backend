package com.br.bood.boodApi.domain.establishments.records;

import com.br.bood.boodApi.shared.tools.StringFormater;

public record AddressRecord(String street, String neighborhood, Integer number, String city,
                            String zipCode) {
    @Override
    public String zipCode () {return StringFormater.zipCode(zipCode); }
}