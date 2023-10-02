package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;
import org.tendoo.algorithms.api.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public final class Invariants {

    public static Pair<int[], Integer> mergeSort(int[] array, Integer invariantsCounter) {
        if (array.length == 1)
            return new Pair<>(array, invariantsCounter);

        Pair<int[], Integer> left = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2), invariantsCounter);
        Pair<int[], Integer> right = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length), invariantsCounter);

        int[] buffer = new int[array.length];

        int[] lArray = left.getFirst();
        int[] rArray = right.getFirst();

        int bufferInvariantsCounter = left.getSecond() + right.getSecond();

        int l = 0, r = 0, k = 0;
        while(l < lArray.length && r < rArray.length) {
            if (lArray[l] >= rArray[r]) {
                buffer[k] = lArray[l];
                bufferInvariantsCounter += (rArray.length - r);
                l++;
            } else {
                buffer[k] = rArray[r];
                r++;
            }
            k++;
        }
        while (l < lArray.length) {
            buffer[k] = lArray[l];
            k++;
            l++;
        }
        while (r < rArray.length) {
            buffer[k] = rArray[r];
            k++;
            r++;
        }
        return new Pair<>(buffer, bufferInvariantsCounter);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        int[] elems = Utils.readIntTokens(reader.readLine(), elemsCount).stream().mapToInt(s -> s).toArray();

        int invariantsCount = mergeSort(elems, 0).getSecond();

        System.out.println(invariantsCount);
    }
}
