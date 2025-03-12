package com.br.bood.boodApi.shared.tools;

public class StringFormater {
    public static String alvara(String doc) {
        return doc
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }

    public static String cnpj(String doc) {
        return doc
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }

    public static String phone(String phone) {
        return phone
                .replace("+", "")
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }

    public static String zipCode(String code) {
        return code
                .replace("+", "")
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
