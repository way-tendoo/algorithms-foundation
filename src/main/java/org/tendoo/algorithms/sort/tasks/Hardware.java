package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public final class Hardware {

    static class OrdinaryComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            return lhs.compareTo(rhs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int freeDiskSpace = Integer.parseInt(tokens.nextToken());
        int usersCount = Integer.parseInt(tokens.nextToken());

        List<Integer> necessaryUsersSpace = Utils.readLines(reader, Integer::parseInt, usersCount);
        
        necessaryUsersSpace.sort(new OrdinaryComparator());
        
        int totalUsersCount = 0;
        for (int currentNecessarySpace : necessaryUsersSpace) {
            if (currentNecessarySpace <= freeDiskSpace) {
                totalUsersCount += 1;
                freeDiskSpace -= currentNecessarySpace;
            } else break;
        }
        System.out.println(totalUsersCount);
    }
}
