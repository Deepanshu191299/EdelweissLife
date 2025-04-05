package in.edelweiss.proposal.form.util;

public class CISUtil {
	
	
	
	public static String formatIndianNumber(String numberStr) {
        // Check if the number string is null or empty and return "0" if it is
        if (numberStr == null || numberStr.isEmpty()) {
            return "0";
        }
 
        try {
            // Parse the String to a Double
            Double number = Double.parseDouble(numberStr);
            // Round the number to the nearest integer
            long roundedNumber = Math.round(number);
            // Convert the rounded long number to a string
            String numStr = Long.toString(roundedNumber);
            
         // If the number has 3 digits or fewer, return it directly
            if (numStr.length() <= 3) {
                return numStr; // e.g., "9", "99", "999"
            }
            
            // Get the last 3 digits of the whole part (for the thousand group)
            String lastThree = numStr.substring(numStr.length() - 3);
            
            // Get the rest of the digits before the last 3 digits
            String rest = numStr.substring(0, numStr.length() - 3);
            
            // Apply Indian grouping with commas (pairs of 2 digits)
            if (!rest.isEmpty()) {
                rest = rest.replaceAll("\\B(?=(\\d{2})+(?!\\d))", ",");
            }
 
            // Combine the two parts together
            return rest + "," + lastThree;
        } catch (NumberFormatException e) {
            // In case of invalid number format, return "0"
            return "0";
        }
    }

}
