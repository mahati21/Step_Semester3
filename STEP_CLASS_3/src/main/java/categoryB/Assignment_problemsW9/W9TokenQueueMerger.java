package categoryB.Assignment_problemsW9;

import java.util.Arrays;

public class W9TokenQueueMerger {
    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                result[k++] = counterA[i++];
            } else {
                result[k++] = counterB[j++];
            }
        }
        while (i < m) {
            result[k++] = counterA[i++];
        }

        while (j < n) {
            result[k++] = counterB[j++];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] a = {3, 8, 15, 20};
        int[] b = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(a, b)));
    }
}
