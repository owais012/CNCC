import java.util.Scanner;

public class CRC {

    public static StringBuilder trimLeadingZero(String str) {
        String trimmed = str.replaceFirst("^0+", "");
        return new StringBuilder(trimmed);
    }

    public static String divide(String divisor, StringBuilder dividend) {
        // this while loop can be replaced by a function for trimming leading zero's
        dividend = trimLeadingZero(dividend.toString());

        if (dividend.length() < divisor.length())
            return dividend.toString();

        for (int i = 0, j = 0; i < divisor.length() && j < dividend.length(); i += 1, j += 1) {
            if (divisor.charAt(i) == dividend.charAt(j)) {
                dividend.setCharAt(j, '0');
            } else
                dividend.setCharAt(j, '1');
        }

        return divide(divisor, new StringBuilder(dividend));
    }

    public static void main(String[] args) {
        String divisor = "11001";
        String data = "1010101010";
        String lastKBit = divide(divisor, new StringBuilder(data + "0".repeat(divisor.length() - 1)));
        while (lastKBit.length() < divisor.length() - 1) {
            lastKBit = "0" + lastKBit;
        }

        String transmittedData = data + lastKBit;
        System.out.println(transmittedData);
        String recievedData = transmittedData; // can put anything to verify whether it's correct or not

        if (divide(divisor, new StringBuilder(recievedData)) != "") {
            System.out.println("There is error in the recieved data");
        } else {
            System.out.println("Data recieved is correct");
        }
    }
}
