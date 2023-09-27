package org.tendoo.algorithms.sort;

import org.tendoo.algorithms.datastructures.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class SelectionSort {

    private static void swap(List<Integer> elems, int i, int j) {
        int elem = elems.get(i);
        elems.set(i, elems.get(j));
        elems.set(j, elem);
    }

    public static void selectionSortDesc(List<Integer> elems) {
        for (int i = 0; i < elems.size(); i++) {
            int maxIndex = i;
            for (int j = i; j < elems.size(); j++) {
                if (elems.get(j) >= elems.get(maxIndex)) {
                    maxIndex = j;
                }
            }
            swap(elems, i, maxIndex);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readTokens(reader.readLine(), Integer::parseInt, elemsCount);

        selectionSortDesc(elems);

        Utils.print(elems);
    }

}
