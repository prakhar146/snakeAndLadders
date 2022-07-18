package models;

import java.util.List;

public class Board {
    private final int[][] board;
    private final List<Snake> snakes;
    private final List<Ladder> ladder;
    private final List<Player> players;
    private final Position startPos;
    private final Position endPos;

    public Board(int[][] board, List<Snake> snakes, List<Ladder> ladder, List<Player> players, Position startPos, Position endPos) {
        this.board = board;
        this.snakes = snakes;
        this.ladder = ladder;
        this.players = players;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public int[][] getBoard() {
        return board;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadder() {
        return ladder;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }
}
