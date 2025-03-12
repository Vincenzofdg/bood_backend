package com.br.bood.boodApi.shared.tools;

public class FormatPhone {
    public static String format(String phone) {
        return phone
                .replace("+", "")
                .replace(".", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
