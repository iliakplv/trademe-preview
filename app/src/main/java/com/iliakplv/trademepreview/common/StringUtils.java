package com.iliakplv.trademepreview.common;


import android.support.annotation.Nullable;

public final class StringUtils {


    // TODO refactor this !!!
    /**
     * Extracts last digit group from full number format
     *
     * @param number    number in one of the following formats: "0001", "0001-0123", or "0001-0123-",
     *                  where "-" is a separator and number of digit groups is arbitrary
     * @param separator separator character or string ("-", " ", etc.)
     * @return Last digit group of a full number,
     * original number if it doesn't contain separators,
     * original number if it's empty or separator is empty
     */
    public static String getLastDigitGroup(@Nullable String number, @Nullable String separator) {
        if (isEmpty(number) || isEmpty(separator)) {
            return number;
        }
        if (!number.contains(separator)) {
            return number;
        }
        final String[] groups = number.split(separator);
        final int numberOfGroups = groups.length;
        if (numberOfGroups >= 2) {
            return isEmpty(groups[numberOfGroups - 1]) ?
                    groups[numberOfGroups - 2] :
                    groups[numberOfGroups - 1];
        }
        return groups[0];
    }

    /**
     * Checks if given char sequence is null or empty
     *
     * @param charSequence char sequence
     * @return true if the char sequence is null or empty
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String getNotNull(String string) {
        return string != null ? string : "";
    }

}
