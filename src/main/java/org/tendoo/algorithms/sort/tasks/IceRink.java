package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public final class IceRink {

    static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            return rhs.compareTo(lhs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int skatesCount = Integer.parseInt(reader.readLine());

        List<Integer> skatesSize = Utils.readIntTokens(reader.readLine(), skatesCount);

        int studentsCount = Integer.parseInt(reader.readLine());

        List<Integer> studentsSize = Utils.readIntTokens(reader.readLine(), studentsCount);

        skatesSize.sort(new DescComparator());

        studentsSize.sort(new DescComparator());

        int left = 0;
        int right = 0;

        int maxStudentsCount = 0;
        while (left < skatesCount && right < studentsCount) {
            if (studentsSize.get(right) <= skatesSize.get(left)) {
                left++;
                maxStudentsCount++;
            }
            right++;
        }
        System.out.println(maxStudentsCount);
    }
}
