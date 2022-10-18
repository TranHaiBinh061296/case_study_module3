package com.codegym.furniture.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String IMAGE_PATTERN = "(https?:\\/\\/.*\\.(?:png|jpg|jpeg))";
    private static final String PHONE_PATTERN = "^0[1-9][0-9]{8}$";
    public static final String PRICE_REGEX = "^[1-9][0-9]{0,10}\\.[0]";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9.]*[A-Za-z0-9]+@[A-Za-z0-9]+(.[A-Za-z0-9]+)$";
    public static final String QUANTITY_REGEX = "^[1-9][0-9]{0,2}";


    public static boolean isImageValid(String image) {
        return Pattern.compile(IMAGE_PATTERN).matcher(image).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isPhoneValid(String phone) {
        return Pattern.compile(PHONE_PATTERN).matcher(phone).matches();
    }

    public static boolean isPriceValid(String price) {
        return Pattern.compile(PRICE_REGEX).matcher(price).matches();
    }

    public static boolean isQuantityValid(String quantity) {
        return Pattern.compile(QUANTITY_REGEX).matcher(quantity).matches();
    }
}
