package service.impl;

import java.util.TreeSet;

import model.User;
import service.LeaderBoardService;

public class LeaderBoardServiceImpl implements LeaderBoardService{

    private final TreeSet<User> leaderboard;

    public LeaderBoardServiceImpl() {
        leaderboard = new TreeSet<>((u1, u2) -> {
        int cmp = Integer.compare(u2.getPoints(), u1.getPoints()); // Sort by points desc
        if (cmp == 0) {
            return Integer.compare(u1.getId(), u2.getId()); // Tie-breaker by id
        }
        return cmp;
    });
    }

    @Override
    public void addUser(User user) {
        leaderboard.add(user);
    }

    @Override
    public void removeUser(User user) {
        leaderboard.remove(user);
    }

    @Override
    public void printLeaderBoard() {
        printTopUsers(leaderboard.size());
    }

    @Override
    public void printTopUsers(int n) {
        int count = 0;

        for(User user : leaderboard) {
            if(count++ == n) break;
            System.out.println(count + ". " + user.getName() + " - " + user.getPoints());
        }

        if(count == 0) {
            System.out.println("No user have submitted the problems yet");
        }
    }

}
