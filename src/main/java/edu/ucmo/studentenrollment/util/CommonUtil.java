package edu.ucmo.studentenrollment.util;

import java.util.UUID;

public class CommonUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String paddedNumber(Long num) {
        return "1" + String.format("%011d", num);
    }
}
