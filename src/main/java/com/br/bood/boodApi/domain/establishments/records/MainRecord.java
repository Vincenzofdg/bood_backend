package com.br.bood.boodApi.domain.establishments.records;

import com.br.bood.boodApi.shared.tools.FormatDocument;
import com.br.bood.boodApi.shared.tools.FormatPhone;

public record MainRecord(String name, String email, String tags, String description, String phone,
                         String document, Float rate, Boolean status, String preview_url) {

    @Override
    public String phone() {
        return FormatPhone.format(phone);
    }

    @Override
    public String document() {
        if (document.length() > 5) {
            return FormatDocument.cnpj(document);
        }
        return FormatDocument.alvara(document);
    }
}