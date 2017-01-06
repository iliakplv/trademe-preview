package com.iliakplv.trademepreview.common;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class StringUtils {


    /**
     * Extracts last digit group from full number format
     *
     * @param number    number in one of the following formats: "0001", "0001-0123", or "0001-0123-",
     *                  where "-" is a separator and number of digit groups is arbitrary
     * @param separator separator character or string ("-", " ", etc.)
     * @return Last digit group of a full number,
     * original number if it doesn't contain separators,
     * empty string if original number is empty or separator is empty
     */
    @NonNull
    public static String getLastDigitGroup(@Nullable String number, @Nullable String separator) {
        if (isEmpty(number) || isEmpty(separator)) {
            return getNotNull(number);
        }

        if (number.contains(separator)) {
            final String[] groups = number.split(separator);
            if (groups.length > 0) {
                return groups[groups.length - 1];
            } else {
                return "";
            }
        }

        return number;
    }

    /**
     * Checks if given char sequence is {@code null} or empty
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /**
     * Returns given string if it's not {@code null} or empty string otherwise
     */
    public static String getNotNull(String string) {
        return string != null ? string : "";
    }

}
