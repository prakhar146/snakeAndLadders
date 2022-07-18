package services;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeAndLadders {
    BoardService boardService;

    public SnakeAndLadders() {
        Board board = generateBoard();
        boardService = new BoardServiceImpl(board);
    }

    public void playGame() {
        Player winner = boardService.getWinner();
        printWinner(winner);
    }

    void printWinner(Player player) {
        System.out.println("****************");
        System.out.println("---- " + player.getName() + " ----");
        System.out.println("*****************");
    }

    Board generateBoard() {
        int N = 10;
        int[][] gameBoard = new int[N][N];
        Random rd = new Random();

        int numOfSnakes = 5;
        int numOfLadders = 5;

        List<Snake> snakeList = new ArrayList<>();
        List<Ladder> ladderList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();

        snakeList.add(new Snake(new Position(8, 1), new Position(5, 2), 'S'));
        snakeList.add(new Snake(new Position(8, 5), new Position(4, 4), 'S'));
        snakeList.add(new Snake(new Position(7, 8), new Position(1, 2), 'S'));
        snakeList.add(new Snake(new Position(5, 3), new Position(0, 5), 'S'));
        snakeList.add(new Snake(new Position(2, 1), new Position(0, 7), 'S'));


        ladderList.add(new Ladder(new Position(0, 6), new Position(5, 5), 'L'));
        ladderList.add(new Ladder(new Position(1, 3), new Position(6, 2), 'L'));
        ladderList.add(new Ladder(new Position(1, 7), new Position(2, 9), 'L'));
        ladderList.add(new Ladder(new Position(3, 4), new Position(7, 3), 'L'));
        ladderList.add(new Ladder(new Position(5, 0), new Position(9, 0), 'L'));

        playerList.add(new Player("Player 1"));
        playerList.add(new Player("Player 2"));
//        playerList.add(new Player("Player 3"));

        return new Board(gameBoard, snakeList, ladderList, playerList, new Position(0, 0), new Position(N - 1, N - 1));
    }


}
