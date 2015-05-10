package com.tema.algo.sort;

/**
 * Find first bit equal set to one in the string represented number where no 0 right to any 1.
 */
public class SortedBinaryVector {

    public static int find(String str) {
        if (str.isEmpty()) {
            return -1;
        } else if (str.charAt(0) == '1') {
            return 0;
        } else if (str.charAt(str.length() - 1) == '0') {
            return -1;
        }
        int low = 0;
        int high = str.length() - 1;
        while (low < high) {
            final int mid = low + (high - low) / 2;
            if (str.charAt(mid) == '0') {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        solve("");
        solve("0");
        solve("1");
        solve("00");
        solve("11");
        solve("01");
        solve("000");
        solve("001");
        solve("011");
        solve("111");
        solve("0000");
        solve("0001");
        solve("0011");
        solve("0111");
        solve("1111");
        solve("00000");
        solve("00001");
        solve("00011");
        solve("00111");
        solve("01111");
        solve("11111");
    }

    private static void solve(String s) {
        System.out.println(s + ": " + find(s));
    }
}
