package org.tendoo.algorithms.sort;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class CountSort {

    public static void countSort(List<Integer> elems) {
        int elemsCount = elems.size();
        if (elemsCount == 0)
            return;
        Integer maxValue = elems.stream().max(Integer::compareTo).get();
        int[] buffer = new int[maxValue + 1];
        for (int elem : elems)
            buffer[elem] += 1;
        int j = 0;
        for (int i = 0; i < elemsCount; i++) {
            while (buffer[j] == 0)
                j++;
            elems.set(i, j);
            buffer[j] -= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readIntTokens(reader.readLine(), elemsCount);

        countSort(elems);

        Utils.print(elems);
    }
}
