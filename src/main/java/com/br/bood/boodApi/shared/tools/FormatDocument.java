package com.br.bood.boodApi.shared.tools;

public class FormatDocument {
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
}
