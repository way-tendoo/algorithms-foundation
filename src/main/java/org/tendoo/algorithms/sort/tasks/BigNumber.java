package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class BigNumber {

    public static boolean bigNumComparator(String lhs, String rhs) {
        return Long.parseLong(lhs + rhs) > Long.parseLong(rhs + lhs);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<String> elems = Utils.readLines(reader, elemsCount);

        for (int i = 1; i < elems.size(); i++) {
            String elem = elems.get(i);
            int j = i - 1;
            while (j >= 0 && bigNumComparator(elem, elems.get(j))) {
                elems.set(j + 1, elems.get(j));
                j--;
            }
            elems.set(j + 1, elem);
        }

        StringBuilder bigNum = new StringBuilder();
        for (String elem : elems)
            bigNum.append(elem);
        System.out.println(bigNum);
    }
}
