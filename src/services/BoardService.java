package services;

import models.Player;

public interface BoardService {
    Player getWinner();
    boolean addPlayer(Player player);
}
