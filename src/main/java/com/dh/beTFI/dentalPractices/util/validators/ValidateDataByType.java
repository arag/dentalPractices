package com.dh.beTFI.dentalPractices.util.validators;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ValidateDataByType {
    public static boolean invalidString(String text) {
        return text == null || text.trim().equals("") || !text.matches("[a-zA-Z]*");
    }

    public static boolean invalidInteger(Integer number) {
        return number == null || number <= 0;
    }

}
