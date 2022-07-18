package services;

import models.Player;
import models.Position;

public interface PlayerService {
    Position move(int[][] board, Player p);
}
