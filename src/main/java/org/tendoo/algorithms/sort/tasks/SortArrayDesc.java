package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public final class SortArrayDesc {

    static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            return lhs.compareTo(rhs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readIntTokens(reader.readLine(), elemsCount);

        elems.sort(new DescComparator());

        Utils.print(elems);
    }
}
