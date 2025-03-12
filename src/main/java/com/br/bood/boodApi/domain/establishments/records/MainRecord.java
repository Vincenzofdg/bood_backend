package com.br.bood.boodApi.domain.establishments.records;

import com.br.bood.boodApi.shared.tools.StringFormater;

public record MainRecord(String name, String email, String tags, String description, String phone,
                         String document, Float rate, Boolean status, String previewUrl) {

    @Override
    public String phone() {
        return StringFormater.phone(phone);
    }

    @Override
    public String document() {
        if (document.length() > 5) {
            return StringFormater.cnpj(document);
        }
        return StringFormater.alvara(document);
    }
}