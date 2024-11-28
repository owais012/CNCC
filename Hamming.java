import java.util.*;

public class Hamming {
    public static int calculateParityBit(int d) {
        int p = 0;
        while ((1 << p) < p + d + 1) {
            p += 1;
        }
        return p + d;
    }

    public static void print(int... a) {
        for (var v : a)
            System.out.print(v + " ");
        System.out.println();
    }

    public static String makeHammingData(String data, int n) {
        StringBuilder hammCode = new StringBuilder();

        for (int i = 1, j = data.length() - 1; i <= n; i += 1) {
            if ((i & (i - 1)) > 0) { // if this in form of 2^n-1 t
                hammCode.append(data.charAt(j));
                j--;
            } else {
                hammCode.append("r");
            }
        }
        return hammCode.toString();
    }

    // need to verfy this function 
    public static int calculateParity(String dataBit, int start, int n) {
        int bitCnt = 0, j = start;
        start++;
        // if start == 1 -> 1, 3, 5, 7, 9 , ..n
        // if start == 2 -> 2 3, 6, 7, 10 , 11
        while (j < n) { 
            int k = j;

            for (; k < (j + start) && (j+ start) <= n; k += 1) {
                if (dataBit.charAt(k) == '1'){
                    bitCnt += 1;
                }
            }
            j += (2*start);
        }
        return bitCnt;
    }

    public static String encodeData(String hammCode, int n) {
        StringBuilder bit = new StringBuilder(hammCode);

        for (int i = 0; i < bit.length(); i += 1) {
            if (bit.charAt(i) == 'r') {
                // take r elements then skip r , continue
                char parity =  calculateParity(hammCode, i, n)%2 == 0 ? '0' : '1' ;

                bit.setCharAt(i, parity);
            }
        }

        return bit.reverse().toString();
    }

    public static String decode(String data) {
        StringBuilder bit = new StringBuilder(data);
        int n = data.length();
        bit.reverse();

        System.out.println(bit);
        for (int i = 0; i < bit.length(); i += 1) {
            if (((i+1)& i) == 0) {
                // take r elements then skip r , continue
                int bitCnt = calculateParity(bit.toString(), i, n)-1;

                if((bitCnt %2 == 0 && bit.charAt(i) != '0') || (bit.charAt(i) == '1' && bitCnt %2 != 1)){
                    System.out.println(i + " bit count " + bitCnt + " parity "+ bit.charAt(i));
                    return "there is Error on the data";
                }
            }
        }
     
        return "data is correct";
    }

    public static void main(String[] args) {
        // doing 7 bit hamming code
        // here we use 2^n formula

        Scanner sc = new Scanner(System.in);
        int dataBit = sc.nextInt();
        System.out.println("Enter the data");
        String data = sc.next();

        // Step 1) Find the number of Parity bit using formula
        // 2^p >= p + dataBit + 1

        int n = calculateParityBit(dataBit);
        System.out.println(n);

        // Step 2) make array/ String for hamming code
        String hammData = makeHammingData(data, n);
        System.out.println(hammData);

        // step 3) calculate r1 , r2, r3 .. rp

        // Step 4) print output
        String encodedData = encodeData(hammData, n);
        System.out.println(encodedData);

        // **************detect Errors************
        // step 5) make function which decodes and check whether correct or not
        System.out.println(decode(encodedData));
        // step 6) make another function which calculate the location of error

    }
}
