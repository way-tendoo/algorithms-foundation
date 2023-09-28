package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public final class TestSystem {

    static class Team {
        private final Integer id;
        private final Integer tasksCount;
        private final Integer penaltyTime;

        Team(Integer id, Integer tasksCount, Integer penaltyTime) {
            this.id = id;
            this.tasksCount = tasksCount;
            this.penaltyTime = penaltyTime;
        }

        public Integer getId() {
            return id;
        }

        public Integer getTasksCount() {
            return tasksCount;
        }

        public Integer getPenaltyTime() {
            return penaltyTime;
        }

        @Override
        public String toString() {
            return id.toString();
        }
    }

    static class TeamComparator implements Comparator<Team> {
        @Override
        public int compare(Team lhs, Team rhs) {
            int taskCountComparison = rhs.getTasksCount().compareTo(lhs.getTasksCount());
            if (taskCountComparison != 0)
                return taskCountComparison;
            int penaltyTimeComparison = lhs.getPenaltyTime().compareTo(rhs.getPenaltyTime());
            if (penaltyTimeComparison != 0)
                return penaltyTimeComparison;
            return lhs.getId().compareTo(rhs.getId());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int teamsCount = Integer.parseInt(reader.readLine());

        List<Team> teams = new ArrayList<>();

        for (int id = 1; id <= teamsCount; id++) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            int tasksCount = Integer.parseInt(tokens.nextToken());
            int penaltyTime = Integer.parseInt(tokens.nextToken());
            teams.add(new Team(id, tasksCount, penaltyTime));
        }

        teams.sort(new TeamComparator());

        Utils.print(teams);
    }
}
