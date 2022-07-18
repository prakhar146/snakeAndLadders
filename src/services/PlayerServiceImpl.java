package services;

import models.Player;
import models.Position;
import util.Dice;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public Position move(int[][] board, Player p) {
        int diceNum = Dice.roll();
        System.out.println("Dice Num " + diceNum);
        Position currentPos = p.getPosition();
        Position newPosition = getNewPosition(board, currentPos, diceNum);
        System.out.println("New Pos " + newPosition);
        return newPosition;
    }

    private Position getNewPosition(int[][] board, Position currentPosition, int moves) {
        int numCols = board[0].length;
        int currentRow = currentPosition.getX();
        int currentColumn = currentPosition.getY();
        int newColumn = (currentColumn + moves);
        while (newColumn >= numCols) {
            int numOfColumnsLeft = (numCols - 1) - currentPosition.getY();
            moves = Math.max(0, moves - numOfColumnsLeft);
            currentColumn = 0;
            currentRow++;
            newColumn = (currentColumn + moves);
        }
        return new Position(currentRow, newColumn);
    }
}
