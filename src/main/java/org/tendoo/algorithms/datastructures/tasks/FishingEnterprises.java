package org.tendoo.algorithms.datastructures.tasks;

import org.tendoo.algorithms.api.Pair;
import org.tendoo.algorithms.datastructures.DoubleLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class FishingEnterprises {

    private static final int BANKRUPTCY = 1;
    private static final int SEPARATION = 2;

    private static long sqr(long num) {
        return num * num;
    }

    private static long sumSquaredDistances(DoubleLinkedList<Long> distances) {
        long totalDistance = 0;
        for (DoubleLinkedList.Node<Long> node = distances.getHead(); node != null; node = node.getNext()) {
            totalDistance += sqr(node.getValue());
        }
        return totalDistance;
    }

    private static Pair<Long, Long> priorDistance(long distance) {
        long mid = distance / 2;
        if (distance % 2 == 0)
            return new Pair<>(mid, mid);
        else
            return new Pair<>(mid, mid + 1);
    }

    private static Pair<Integer, Integer> parseAction(String action) {
        StringTokenizer actionTokens = new StringTokenizer(action);
        int actionId = Integer.parseInt(actionTokens.nextToken());
        int enterpriseId = Integer.parseInt(actionTokens.nextToken());
        return new Pair<>(actionId, enterpriseId - 1);
    }

    private static DoubleLinkedList.Node<Long> settingCursor(DoubleLinkedList<Long> distances,
                                                             DoubleLinkedList.Node<Long> companyCursor,
                                                             int companyIdCursor,
                                                             int companyId) {
        if (companyIdCursor <= companyId)
            return distances.forward(companyCursor, companyIdCursor, companyId).get();
        return distances.back(companyCursor, companyIdCursor, companyId).get();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine(); // not use

        DoubleLinkedList<Long> distances = new DoubleLinkedList<>();

        StringTokenizer distancesTokens = new StringTokenizer(reader.readLine());
        while (distancesTokens.hasMoreTokens())
            distances.pushBack(Long.parseLong(distancesTokens.nextToken()));

        int actionsCount = Integer.parseInt(reader.readLine());

        DoubleLinkedList.Node<Long> companyCursor = distances.getHead();

        int companyIdCursor = 0;
        long sumSquaredDistances = sumSquaredDistances(distances);

        System.out.println(sumSquaredDistances);

        for (int i = 0; i < actionsCount; i++) {
            Pair<Integer, Integer> parsedAction = parseAction(reader.readLine());
            companyCursor = settingCursor(distances, companyCursor, companyIdCursor, parsedAction.getSecond());

            if (parsedAction.getFirst() == BANKRUPTCY) {
                if (companyCursor == distances.getHead()) {
                    Long erasedValue = distances.popFront().get();
                    DoubleLinkedList.Node<Long> newHead = distances.getHead();
                    sumSquaredDistances -= sqr(erasedValue) + sqr(newHead.getValue());
                    newHead.setValue(newHead.getValue() + erasedValue);
                    companyIdCursor = 0;
                    companyCursor = distances.getHead();
                    sumSquaredDistances += sqr(newHead.getValue());
                } else if (companyCursor == distances.getTail()) {
                    Long erasedValue = distances.popBack().get();
                    DoubleLinkedList.Node<Long> newTail = distances.getTail();
                    sumSquaredDistances -= sqr(erasedValue) + sqr(newTail.getValue());
                    newTail.setValue(newTail.getValue() + erasedValue);
                    companyIdCursor = distances.getSize() - 1;
                    companyCursor = distances.getTail();
                    sumSquaredDistances += sqr(newTail.getValue());
                } else {
                    Long erasedValue = distances.erase(companyCursor);
                    Pair<Long, Long> priorDistance = priorDistance(erasedValue);
                    DoubleLinkedList.Node<Long> prev = companyCursor.getPrev();
                    DoubleLinkedList.Node<Long> next = companyCursor.getNext();
                    sumSquaredDistances -= sqr(erasedValue) + sqr(prev.getValue()) + sqr(next.getValue());
                    prev.setValue(prev.getValue() + priorDistance.getFirst());
                    next.setValue(next.getValue() + priorDistance.getSecond());
                    companyIdCursor = parsedAction.getSecond() - 1;
                    companyCursor = prev;
                    sumSquaredDistances += sqr(prev.getValue()) + sqr(next.getValue());
                }
            } else if (parsedAction.getFirst() == SEPARATION) {
                if (companyCursor == distances.getHead()) {
                    long erasedValue = distances.popFront().get();
                    Pair<Long, Long> priorDistance = priorDistance(erasedValue);
                    sumSquaredDistances -= sqr(erasedValue);
                    distances.pushFront(priorDistance.getSecond());
                    distances.pushFront(priorDistance.getFirst());
                    companyIdCursor = 0;
                    companyCursor = distances.getHead();
                    sumSquaredDistances += sqr(priorDistance.getFirst()) + sqr(priorDistance.getSecond());
                } else if (companyCursor == distances.getTail()) {
                    long erasedValue = distances.popBack().get();
                    Pair<Long, Long> priorDistance = priorDistance(erasedValue);
                    sumSquaredDistances -= sqr(erasedValue);
                    distances.pushBack(priorDistance.getFirst());
                    distances.pushBack(priorDistance.getSecond());
                    companyIdCursor = distances.getSize() - 1;
                    companyCursor = distances.getTail();
                    sumSquaredDistances += sqr(priorDistance.getFirst()) + sqr(priorDistance.getSecond());
                } else {
                    DoubleLinkedList.Node<Long> prev = companyCursor.getPrev();
                    DoubleLinkedList.Node<Long> next = companyCursor.getNext();
                    Long erasedValue = distances.erase(companyCursor);
                    sumSquaredDistances -= sqr(erasedValue);
                    Pair<Long, Long> priorDistance = priorDistance(erasedValue);
                    distances.insertAfter(prev, priorDistance.getFirst());
                    distances.insertBefore(next, priorDistance.getSecond());
                    companyIdCursor = parsedAction.getSecond() - 1;
                    companyCursor = prev;
                    sumSquaredDistances += sqr(priorDistance.getFirst()) + sqr(priorDistance.getSecond());
                }
            } else {
                throw new IllegalArgumentException(String.format("Unsupported action: %s", parsedAction.getFirst()));
            }
            System.out.println(sumSquaredDistances);
        }
    }
}
