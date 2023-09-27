package org.tendoo.algorithms.datastructures.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public final class Barbershop {

    private static final int HAIRCUT_TIME = 20;

    private static class Time {
        private final int hours;
        private final int minutes;

        Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public boolean moreOrEqualThen(Time other) {
            if (this.hours > other.hours)
                return true;
            else return this.hours == other.hours && this.minutes >= other.minutes;
        }

        public Time plusMinutes(int minutes) {
            int newMinutes = this.minutes + minutes;
            if (newMinutes >= 60)
                return new Time(this.hours + 1, newMinutes - 60);
            return new Time(this.hours, newMinutes);
        }

        @Override
        public String toString() {
            return String.format("%s %s", hours, minutes);
        }
    }

    private static Time parseTime(StringTokenizer command) {
        int hours = Integer.parseInt(command.nextToken());
        int minutes = Integer.parseInt(command.nextToken());
        return new Time(hours, minutes);
    }

    private static Time emptyQueueClientProcess(Time inputTime) {
        Time exitTime = inputTime.plusMinutes(HAIRCUT_TIME);
        System.out.println(exitTime);
        return exitTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int clientsCount = Integer.parseInt(reader.readLine());

        Deque<Time> clientsQueue = new LinkedList<>();

        for (int i = 0; i < clientsCount; i++) {
            StringTokenizer command = new StringTokenizer(reader.readLine());
            Time inputTime = parseTime(command);
            int impatience = Integer.parseInt(command.nextToken());

            if (clientsQueue.isEmpty()) {
                Time exitTime = emptyQueueClientProcess(inputTime);
                clientsQueue.add(exitTime);
            } else {
                Time inProgressFinishTime = clientsQueue.peek();
                while (true) {
                    if (inputTime.moreOrEqualThen(inProgressFinishTime)) {
                        clientsQueue.pollFirst();
                        if (clientsQueue.isEmpty())
                            break;
                        inProgressFinishTime = clientsQueue.peek();
                    } else break;
                }
                if (clientsQueue.isEmpty()) {
                    Time exitTime = emptyQueueClientProcess(inputTime);
                    clientsQueue.add(exitTime);
                } else {
                    Time lastInQueue = clientsQueue.peekLast();
                    if (clientsQueue.size() > impatience) {
                        System.out.println(inputTime);
                    } else {
                        Time nextClientExitTime = lastInQueue.plusMinutes(HAIRCUT_TIME);
                        System.out.println(nextClientExitTime);
                        clientsQueue.add(nextClientExitTime);
                    }
                }
            }
        }
    }
}
