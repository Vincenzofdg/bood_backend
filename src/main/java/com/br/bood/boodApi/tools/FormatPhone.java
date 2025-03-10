package com.br.bood.boodApi.tools;

public class FormatPhone {
    public static String format(String phone) {
        return phone
                .replace("+", "")
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
