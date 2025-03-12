package com.br.bood.boodApi.shared.tools;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerator {
    public static String generate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String datePart = dateFormat.format(new Date());
        String hashPart = generateRandomHash(6);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        String timePart = timeFormat.format(new Date());

        return datePart + "-" + hashPart + "-" + timePart;
    }

    private static String generateRandomHash(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder hash = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            hash.append(characters.charAt(index));
        }

        return hash.toString();
    }
}
