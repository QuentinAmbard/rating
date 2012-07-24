package org.avricot.rating.utils;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StringUtils extends org.springframework.util.StringUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private static final String TEXT_0 = "0";
    private static final String PUNCTUATION = "[,_;:/-]";
    private static final String MORE_THAN_ONE_UNDERSCORE = "_{2,}";
    private static final String UNDERSCORE = "_";
    private static final String NON_ALPHA_NUMERIC = "[^a-zA-Z_0-9]";
    private static final String ONE_SPACE = " ";
    private static final String AT_LEAST_ONE_SPACE = "[\\s]+";
    private static final String SLASH_AND_BACK_SLASH = "[\\/\\\\]";
    /**
     * Read this <a href=
     * 'http://userguide.icu-project.org/transforms/general'>documentation</a>.
     */
    private static final String NULL_STRING = "NULL";

    /**
     * No public constructor for utility class.
     */
    private StringUtils() {
    }

    /**
     * Removes all "/" and "\" and replaces them by a space, and removes all
     * useless spaces.
     */
    public static String removeAllSlashesAndUselessSpaces(final String path) {
        return StringUtils.removeUselessSpace(path.replaceAll(SLASH_AND_BACK_SLASH, ONE_SPACE));
    }

    /**
     * Clean the string, usefull for tag. Removes trailing and leading
     * whitespace, lowercase and uppercase first letter.
     */
    public static String cleanString(final String str) {
        String cleanedStr = trimWhitespace(str);
        cleanedStr = cleanedStr.toLowerCase();
        cleanedStr = removeUselessSpace(cleanedStr);
        return capitalize(cleanedStr);
    }

    /**
     * Cleans the string.
     * <ul>
     * <li>remove the HTML markup</li>
     * <li>remove useless spaces ( see {@link #removeUselessSpace(String)} ).</li>
     * </ul>
     */
    public static String noHtmlAndUselessSpaces(final String text) {
        return StringUtils.removeUselessSpace(text.replaceAll("</?[^>]+(?://s*)?>", ONE_SPACE));
    }

    /**
     * Returns the acronym for the given text.
     * <p>
     * Examples :
     * <ul>
     * <li>Marie-Charlotte Dupuis : mcd</li>
     * </ul>
     */
    public static String acronym(final String string) {
        final StringTokenizer tokenizer = new StringTokenizer(string, " \t\n\r\f-'");
        final StringBuilder acronym = new StringBuilder();

        while (tokenizer.hasMoreTokens()) {
            final String word = tokenizer.nextToken();

            if (word.length() > 1) {
                acronym.append(word.substring(0, 1));
            }
        }
        return acronym.toString();
    }

    /**
     * Replaces simple punctuation with spaces.
     * 
     * @warn . is a special case
     */
    public static String removePunctuation(final String string) {
        if (string == null) {
            return null;
        }
        return StringUtils.removeDot(string.replaceAll(PUNCTUATION, ONE_SPACE));
    }

    /**
     * Removes all the dot ('.') following theses rules.
     * <ul>
     * <li>A dot followed or preceded by a space are replaced by a simple space.
     * </li>
     * <li>A dot beginning or ending the string is replaced by empty.</li>
     * <li>A dot in two words (one of them having two or more letters) is
     * replace by empty.</li>
     * <li>All the others dots are replaced by empty.</li>
     * </ul>
     */
    public static String removeDot(final String string) {
        if (string == null) {
            return null;
        }
        return string.replaceAll("(?:\\.\\s)|(?:\\s\\.)", ONE_SPACE) //
                .replaceAll("(?:^\\.)|(?:\\.$)", "") //
                .replaceAll("(?:(\\w{2})\\.(\\w))|(?:(\\w)\\.(\\w{2}))", "$1$3 $2$4") //
                .replaceAll("\\.", "");
    }

    /**
     * Removes all useless space in the given string.
     */
    public static String removeUselessSpace(final String string) {
        if (string == null) {
            return null;
        }
        return org.springframework.util.StringUtils.trimWhitespace(string).replaceAll(AT_LEAST_ONE_SPACE, ONE_SPACE);
    }

    /**
     * Returns a copy of the provided list with all the empty element not added.
     */
    public static List<String> removeWithoutText(final List<String> strings) {
        final List<String> copy = new ArrayList<String>(strings.size());
        for (final String string : strings) {
            if (org.springframework.util.StringUtils.hasText(string)) {
                copy.add(string);
            }
        }
        return copy;
    }

    /**
     * Returns true if the list contains (without carrying of case sensitivity)
     * the given string.
     */
    public static boolean containsIgnoreCase(final String string, final List<String> strings) {
        if (string == null) {
            return strings.contains(null);
        }
        for (final String element : strings) {
            if (string.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Splits the given <tt>string</tt> using all spaces character (\\s+)
     * without accepting empty string and return the result in a never-null
     * list.<br/>
     * This methods is null-safe: returning empty list if given <tt>string</tt>
     * is null.
     */
    public static List<String> splitWithSpaces(final String string) {
        if (string == null) {
            return new ArrayList<String>();
        }
        final List<String> result = new ArrayList<String>(Arrays.asList(string.split(AT_LEAST_ONE_SPACE)));
        for (final Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
            if (!org.springframework.util.StringUtils.hasText(iterator.next())) {
                iterator.remove();
            }
        }
        return result;
    }

    /**
     * @return true if the whole provided string is in upper case
     */
    public static boolean isInUpperCase(final String string) {
        return string.toUpperCase().equals(string);
    }

    /**
     * Return <tt>true</tt> if the given string has text and is not "NULL"
     * (ignore case).
     */
    public static boolean hasTextNotNullString(final String string) {
        return StringUtils.hasText(string) && !NULL_STRING.equalsIgnoreCase(string);
    }

    /**
     * Remove all the given characters from the begining of a string. Eg: "0003"
     * => 3
     */
    public static String removeFromBegining(final String characterToRemove, final String text) {
        String textModified = text;
        while (textModified.length() > 0 && TEXT_0.equals(textModified.substring(0, 1))) {
            textModified = textModified.substring(1, textModified.length());
        }
        return textModified;
    }

    /**
     * Return a copy of the text limited at the given size, or the text if <
     * size.
     */
    public static String limitText(final String text, final int size) {
        if (text.length() > size) {
            return text.substring(0, size) + "...";
        }
        return text;
    }

    /**
     * Return true if the string start with a number.
     */
    public static boolean startWithNumber(final String str) {
        if (str.length() < 1) {
            return false;
        }
        return isNumber(str.substring(0, 1));
    }

    /**
     * Return true if the string is a number.
     */
    public static boolean isNumber(final String str) {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();
        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != localeMinusSign || str.charAt(0) == localeMinusSign && str.length() == 1) {
            return false;
        }

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparatorComa = ',';
        char localeDecimalSeparatorPoint = '.';
        for (char c : str.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == localeDecimalSeparatorComa || c == localeDecimalSeparatorPoint && !isDecimalSeparatorFound) {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Return the toString() method of the object, or "" if null.
     */
    public static String toStringNotNull(final Object o) {
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    /**
     * Return the double as a string, with a precision of 1 digit if != 0.
     */
    public static String getAsString(final Double d) {
        if (d - d.intValue() > 0) {
            return String.valueOf(Math.round(d * 10) / 10.0);
        }
        return String.valueOf(d.intValue());
    }

    /**
     * Return a list of enum from a list of string. If the string doesn't match
     * any enum, nothing is stored in the array for this value.
     */
    public static <E extends Enum<E>> List<E> getEnumFromStrings(final List<String> strings, final Class<E> enumType) {
        List<E> result = new ArrayList<E>();
        for (String str : strings) {
            try {
                result.add(Enum.valueOf(enumType, str));
            } catch (IllegalArgumentException e) {
                LOGGER.debug("can't build enum from string" + str + " and for enum" + enumType.getName(), e);
                // Do nothing
            }
        }
        return result;
    }

    /**
     * Remove the duplicated string from the list. Keep the order. Ignore the
     * case. Limit the result to the given value.
     */
    public static List<String> removeDuplicatedIgnoreCase(final List<String> list, final int maxResult) {
        List<String> result = new ArrayList<String>(maxResult);
        Set<String> distinct = new HashSet<String>();
        for (String str : list) {
            if (distinct.add(str.toLowerCase())) {
                result.add(str);
                if (result.size() >= maxResult) {
                    break;
                }
            }
        }
        return result;

    }

}
