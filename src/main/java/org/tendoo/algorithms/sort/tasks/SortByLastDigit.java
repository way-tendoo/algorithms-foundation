package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public final class SortByLastDigit {

    static class LastDigitComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            int lhsLastDigit = lhs % 10;
            int rhsLastDigit = rhs % 10;
            if (lhsLastDigit > rhsLastDigit)
                return 1;
            else if (lhsLastDigit < rhsLastDigit)
                return -1;
            else
                return lhs.compareTo(rhs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readLines(reader, Integer::parseInt, elemsCount);

        elems.sort(new LastDigitComparator());

        Utils.print(elems);
    }
}
