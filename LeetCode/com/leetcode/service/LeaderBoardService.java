package service;

import model.User;

public interface LeaderBoardService {

    void addUser(User user);

    void removeUser(User user);

    void printLeaderBoard();

    void printTopUsers(int n);
}
