package org.tendoo.algorithms.sort;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class QuickSort {

    interface Partitioner {
        List<Integer>[] partition(List<Integer> array, int pivot);
    }

    static class RandomPivotPartitioner implements Partitioner {

        @Override
        public List<Integer>[] partition(List<Integer> array, int pivot) {
            List<Integer> left = new ArrayList<>();
            List<Integer> center = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            for (Integer x : array) {
                if (x < pivot)
                    left.add(x);
                else if (x == pivot)
                    center.add(x);
                else
                    right.add(x);
            }
            return new List[]{left, center, right};
        }
    }

    public static List<Integer> quickSort(List<Integer> array, Partitioner partitioner) {
        if (array.size() < 2) {
            return array;
        } else {
            Random random = new Random();
            int pivot = array.get(random.nextInt(array.size()));
            List<Integer>[] parts = partitioner.partition(array, pivot);
            return concatenate(quickSort(parts[0], partitioner), parts[1], quickSort(parts[2], partitioner));
        }
    }

    public static List<Integer> concatenate(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> result = new ArrayList<>(a.size() + b.size() + c.size());
        result.addAll(a);
        result.addAll(b);
        result.addAll(c);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int elemsCount = Integer.parseInt(reader.readLine());

        List<Integer> elems = Utils.readIntTokens(reader.readLine(), elemsCount);

        List<Integer> sorted = quickSort(elems, new RandomPivotPartitioner());

        Utils.print(sorted);
    }
}
