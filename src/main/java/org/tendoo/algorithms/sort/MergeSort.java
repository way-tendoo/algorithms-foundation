package org.tendoo.algorithms.sort;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public final class MergeSort {

    public static int[] mergeSort(int[] array) {
        if (array.length == 1)
            return array;

        int[] left = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] right = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int[] result = new int[array.length];

        int l = 0, r = 0, k = 0;
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                result[k] = left[l];
                l++;
            } else {
                result[k] = right[r];
                r++;
            }
            k++;
        }
        while (l < left.length) {
            result[k] = left[l];
            l++;
            k++;
        }
        while (r < right.length) {
            result[k] = right[r];
            r++;
            k++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readIntTokens(reader.readLine(), elemsCount);

        int[] sorted = mergeSort(elems.stream().mapToInt(s -> s).toArray());

        for (int i : sorted)
            System.out.printf("%s ", i);
    }
}
