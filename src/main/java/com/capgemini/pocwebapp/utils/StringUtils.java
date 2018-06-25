
package com.capgemini.pocwebapp.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.capgemini.pocwebapp.constants.IApplicationConstants;

public final class StringUtils extends org.springframework.util.StringUtils
		implements IApplicationConstants {

	public static final NumberFormat AMOUNT_FORMATTER = new DecimalFormat(
			"###,###,###,##0.######");
	public static final DecimalFormat AMOUNT_FORMATTER_FOUR_DECIMALS = new DecimalFormat(
			"###,###,###,##0.0000");
	static {
		AMOUNT_FORMATTER_FOUR_DECIMALS.setNegativePrefix("(");
		AMOUNT_FORMATTER_FOUR_DECIMALS.setNegativeSuffix(")");
	}
	public static final NumberFormat AMOUNT_FORMATTER_TWO_DECIMALS = new DecimalFormat(
			"###,###,###,##0.##");
	public static final NumberFormat PERFORMANCE_FORMATTER = new DecimalFormat(
			"#.##");
	public static final NumberFormat AMOUNT_FORMATTER_EXTERNAL = new DecimalFormat("#.######");
	public static final String BLANK = "";
	public static final String BLANK_SPACE = " ";
	public static final char CHAR_BRACE_RIGHT = ')';
	public static final char CHAR_COLON = ':';
	public static final char CHAR_COMMA = ',';
	public static final char CHAR_PERCENT = '%';
	public static final char CHAR_QUOTE = '\'';
	public static final String COLON = ":";
	public static final String FALSE = "false";
	public static final String STR_QUOTE = "\'";
	public static final String TRUE = "true";
	public static final String SINGLE_QUOTE = "'";

	private static final String CHARACTER_SET_8859_1 = "8859_1";
	private static final String CHARACTER_SET_UTF8 = "UTF8";

	/**
	 * @param str
	 * @return returns true if str is alphanumeric, false otherwise
	 */
	public static boolean alphaNumeric(String str) {
		boolean b = false;
		if (!IsEmpty(str)) {
			b = Pattern.matches("[a-zA-Z0-9\\s]*", str);
		}
		return b;
	}

	/**
	 * @param objects
	 * @return returns single string contains all objects
	 */
	public static String ConvertObjectToString(Object[] objects) {
		String objectNames = null;

		if (objects != null) {
			int objectCount = objects.length;
			StringBuffer obj = new StringBuffer();
			for (int iCount = 0; iCount < objectCount; iCount++) {
				if (objects[iCount] != null) {
					obj.append(objects[iCount].toString() + ", ");
				}
			}
			objectNames = obj.toString();
			if (objectNames.length() > 0) {
				objectNames = objectNames
						.substring(0, objectNames.length() - 2);
			}
		}
		return objectNames;
	}

	/**
	 * @param str
	 * @param delimeter
	 * @return returns Object array containing substrings of str separated by
	 *         delimiter
	 */
	public static Object[] ConvertStringToArray(String str, String delimeter) {
		Object[] elements = null;
		List<String> elementList = new ArrayList<String>();
		if (str != null && !StringUtils.BLANK.equals(str)) {
			StringTokenizer strToken = new StringTokenizer(str, delimeter);
			while (strToken.hasMoreTokens()) {
				elementList.add((String) strToken.nextElement());
			}
			elements = (String[]) elementList.toArray(new String[0]);
		}
		return elements;

	}

	/**
	 * @param amount
	 * @return returns double representing amount
	 */
	public static double ConvertStringToDouble(String amount) {
		double returnValue = 0.00;
		if (!IsBlank(amount)) {
			if (amount.charAt(0) == '-') {
				returnValue = (-Convert(amount.substring(1)));
			} else {
				returnValue = Convert(amount);
			}
		}
		return returnValue;
	}

	/**
	 * @param str
	 * @return returns decoded string for UTF8 characters
	 */
	public static String DecodeString(String str) {
		String retStr = StringUtils.BLANK;
		if (!IsBlank(str)) {
			try {
				retStr = new String(str.getBytes(CHARACTER_SET_8859_1),
						CHARACTER_SET_UTF8);
			} catch (UnsupportedEncodingException e) {
				retStr = str;
			}
		}
		return retStr;
	}

	/**
	 * @param list
	 * @param value
	 * @return returns true if values exists in list, false otherwise
	 */
	public static boolean Exists(String[] list, String value) {
		boolean flag = false;
		if (list != null && list.length > 0 && value != null
				&& value.length() > 0) {
			for (int i = 0; i < list.length; i++) {
				if (value.equals(list[i])) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * @param list
	 * @param values
	 * @return returns true if values exists in list, false otherwise
	 */
	public static boolean Exists(String[] list, String[] values) {
		boolean flag = false;
		if (list != null && list.length > 0 && values != null
				&& values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				flag = Exists(list, values[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * @param formatString
	 * @param argument
	 * @return returns formatted message
	 */
	public static String FormatMessage(String formatString, String argument) {
		return FormatMessage(formatString, new String[] { argument });
	}

	/**
	 * @param formatString
	 * @param arguments
	 * @return returns formatted message
	 */
	public static String FormatMessage(String formatString, String[] arguments) {
		return MessageFormat.format(formatString, (Object[]) arguments);
	}

	/**
	 * @param entityName
	 * @return returns formatted string of entityName
	 */
	public static String FormatName(String entityName) {
		String formattedName = entityName;

		if (!StringUtils.IsBlank(entityName)) {
			if (entityName.contains("<")) {
				formattedName = formattedName.replaceAll("<", "&lt;");
			}
			if (entityName.contains(">")) {
				formattedName = formattedName.replaceAll(">", "&gt;");
			}
		}
		return formattedName;
	}

	/**
	 * @param amount
	 * @return returns formatted string of amount
	 */
	public static String FormatToString(BigDecimal amount) {
		String strAmount = BLANK;
		if (amount != null) {
			strAmount = FormatToString(amount.doubleValue(), 2);
		}
		return strAmount;
	}

	/**
	 * @param amount
	 * @return returns formatted string of amount
	 */
	public static String FormatToString(double amount) {
		return FormatToString(amount, 2);
	}

	/**
	 * @param amount
	 * @param decimalPositions
	 * @return returns formatted string of amount using decimalPositions
	 */
	public static String FormatToString(double amount, int decimalPositions) {
		DecimalFormat nf = new DecimalFormat("###,###,###,##0.######");
		nf.setMaximumFractionDigits(decimalPositions);
		nf.setMinimumFractionDigits(decimalPositions);
		return nf.format(amount);
	}
	
	/**
	 * @param amount
	 * @return returns formatted string of amount with four decimals
	 */
	public static String FormatToStringForPrint(BigDecimal amount) {
		/*
		 * treat 0.0000 & (0.0000) as zero after formatting. below condition
		 * would check the same.
		 */
		if (amount != null
				&& (amount.doubleValue() >= 0.00005 || amount.doubleValue() <= -0.00005)) {
			return AMOUNT_FORMATTER_FOUR_DECIMALS.format(amount);
		}
		return "-";
	}

	/**
	 * @param amount
	 * @param decimalPositions
	 * @return returns formatted string of amount using decimalPositions
	 */
	public static String FormatToStringWithoutComma(double amount,
			int decimalPositions) {
		DecimalFormat nf = new DecimalFormat("###########0.######");
		nf.setMaximumFractionDigits(decimalPositions);
		nf.setMinimumFractionDigits(decimalPositions);
		return nf.format(amount);
	}

	/**
	 * @param arr
	 * @param key
	 * @return returns name from arr for key
	 */
	public static String GetName(String[][] arr, String key) {
		if (arr == null || key == null)
			return BLANK;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0].equals(key)) {
				return arr[i][1];
			}
		}
		return key;
	}

	/**
	 * @param input
	 * @return returns true if input is blank, false otherwise
	 */
	public static boolean IsBlank(String input) {
		if (input == null || BLANK.equals(input))
			return true;
		else
			return false;
	}

	/**
	 * @param str
	 * @return returns true if str is digit, false otherwise
	 */
	public static boolean isDigit(String str) {
		boolean b = false;
		if (!IsEmpty(str)) {

			b = Pattern.matches("\\d*", str);
		}
		return b;
	}

	/**
	 * @param input
	 * @return returns true if input is empty, false otherwise
	 */
	public static boolean IsEmpty(String input) {
		if (input == null || BLANK.equals(input.trim()))
			return true;
		else
			return false;
	}

	/**
	 * @param toSplit
	 * @param len
	 * @return returns the String array list with split length of len
	 */
	public static List<String> split(String toSplit, int len) {
		List<String> splitList = new ArrayList<String>();
		if (toSplit == null || len < 1) {
			splitList.add(BLANK);
			return splitList;
		}
		int size = 1;
		if (toSplit.length() > len) {
			size = toSplit.length() / len;
			if (toSplit.length() % len > 0) {
				++size;
			}
			int i = 0;
			do {
				splitList.add(toSplit.substring(i * len, (i + 1) * len));
			} while (++i < (size - 1));
			splitList.add(toSplit.substring(i * len));
		} else {
			splitList.add(toSplit);
		}
		return splitList;
	}

	/**
	 * @param toSplit
	 * @param len
	 * @param newLineSplit
	 *            treats new line as split if true
	 * @return returns the String array list with split length of len
	 */
	public static List<String> split(String toSplit, int len,
			boolean newLineSplit) {
		if (newLineSplit) {
			List<String> splitList = new ArrayList<String>();
			if (toSplit == null || len < 1) {
				splitList.add(BLANK);
				return splitList;
			}
			String[] splits = Pattern.compile("\n", Pattern.LITERAL).split(
					toSplit, -1);
			for (String string : splits) {
				splitList.addAll(split(string, len));
			}
			return splitList;
		} else {
			return split(toSplit, len);
		}
	}

	/**
	 * @param list
	 * @param delimiter
	 * @return returns string created from concatenation of values using
	 *         delimiter
	 */
	public static String ToString(String[] list, String delimiter) {
		StringBuffer sbTo = new StringBuffer();
		boolean firstElement = true;
		if (list != null && list.length > 0) {
			sbTo = new StringBuffer(list.length * 40);
			for (int i = 0; i < list.length; i++) {
				if (list[i] != null) {
					if (firstElement) {
						firstElement = false;
					} else {
						sbTo.append(delimiter);
					}
					sbTo.append(list[i]);
				}
			}
		}
		return sbTo.toString();

	}

	/**
	 * Insert the method's description here. Creation date: (11/5/2002 4:56:34
	 * PM)
	 * 
	 * @return java.lang.String
	 * @param strInput
	 *            java.lang.String
	 */
	public static String Trim(String strInput) {
		return (strInput != null) ? strInput.trim() : BLANK;
	}

	/**
	 * Method Trim the String from right.
	 * 
	 * @param strInput
	 * @param size
	 * @return String
	 */
	public static String Trim(String strInput, int size) {
		return TrimRight(strInput, size);
	}

	/**
	 * @param source
	 * @return returns string array containing non-empty elements of source
	 */
	public static String[] Trim(String[] source) {
		String[] target = null;
		List<String> tempDestination = new ArrayList<String>();
		if (source != null) {
			for (int k = 0; k < source.length; k++) {
				if (!IsEmpty(source[k])) {
					tempDestination.add(source[k]);
				}

			}
			if (tempDestination.size() > 0) {
				target = new String[tempDestination.size()];
				tempDestination.toArray(target);
			}
		}
		return target;
	}

	/**
	 * Method Trim the String from left.
	 * 
	 * @param strInput
	 * @param size
	 * @return String
	 */
	public static String TrimLeft(String strInput, int size) {
		String tempStr = Trim(strInput);
		int i = tempStr.length();
		if (i > 0 && i > size) {
			tempStr = tempStr.substring(i - size, i);
		}
		return tempStr;
	}

	/**
	 * Method Trim the String from right. Creation date: (11/5/2002 4:57:16 PM)
	 * 
	 * @return java.lang.String
	 * @param strInput
	 *            java.lang.String
	 * @param size
	 *            int
	 */
	public static String TrimRight(String strInput, int size) {
		String tempStr = Trim(strInput);
		if (tempStr.length() > 0 && tempStr.length() > size) {
			tempStr = tempStr.substring(0, size);
		}
		return tempStr;
	}

	/**
	 * @param inputValue
	 * @return returns null if inputValue is blank after trimming, trimmed
	 *         inputValue otherwise
	 */
	public static String TrimToNull(String inputValue) {
		String tempInputValue = StringUtils.Trim(inputValue);
		if (StringUtils.BLANK.equals(tempInputValue))
			return null;
		else
			return tempInputValue;
	}

	/**
	 * @param inputValues
	 * @return returns null if inputValues elements are blank after trimming,
	 *         array of trimmed to null inputValues elements otherwise
	 */
	public static String[] TrimToNull(String[] inputValues) {
		if (inputValues != null && inputValues.length > 0) {
			boolean tempArrayInit = false;
			for (int i = 0; i < inputValues.length; i++) {

				if (TrimToNull(inputValues[i]) == null) {
					inputValues[i] = null;
				} else {
					tempArrayInit = true;
				}
			}
			if (!tempArrayInit) {
				return null;
			}
		}
		return inputValues;
	}

	/**
	 * @param amount
	 * @return returns double representing amount
	 */
	private static double Convert(String amount) {
		double returnValue = 0.00;
		try {
			if (amount.charAt(0) == '$') {
				returnValue = AMOUNT_FORMATTER.parse(amount.substring(1))
						.doubleValue();
			} else {
				returnValue = AMOUNT_FORMATTER.parse(amount).doubleValue();
			}
		} catch (Exception e) {
			// Empty catch block
		}
		return returnValue;
	}

	/**
	 * Private constructor - Empty Constructor
	 */
	private StringUtils() {

	}

	public static String NullToBlank(String inputValue) {
		String tempInputValue = StringUtils.Trim(inputValue);
		if (tempInputValue == null)
			return StringUtils.BLANK;
		else
			return tempInputValue;
	}

}
