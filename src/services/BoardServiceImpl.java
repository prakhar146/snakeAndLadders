package services;

import models.*;

import java.util.List;

public class BoardServiceImpl implements BoardService {
    Board board;
    PlayerService playerService;
    int turn;

    public BoardServiceImpl(Board board) {
        this.board = board;
        playerService = new PlayerServiceImpl();
        turn = 0;
    }

    @Override
    public Player getWinner() {
        List<Player> players = board.getPlayers();
        Player currentPlayer = players.get(turn);
        Position playerPosition = currentPlayer.getPosition();
        int[][] board = this.board.getBoard();

        int noOfTurns = 0;

        while (!playerPosition.equals(this.board.getEndPos())) {
            currentPlayer = players.get(turn);
            System.out.println("TURN " + noOfTurns + " --> " + currentPlayer.getName());
            Position newPosition = playerService.move(board, currentPlayer);
//            System.out.print(currentPlayer.getName() + " moves to " + newPosition);
            if(!isOutOfBounds(newPosition)) {
                newPosition = adjustPosition(newPosition);
//                System.out.println(" -- After adjustment -- " + newPosition);
                currentPlayer.setPosition(newPosition);;
            }
//            else {
//                System.out.println(" -- Out of bounds -- Going back to " + playerPosition);
//            }
            playerPosition = currentPlayer.getPosition();
            printBoard();
            System.out.println("------------------------------------------");
            turn = ((turn + 1) % players.size());
            noOfTurns++;
        }
        return currentPlayer;
    }

    @Override
    public boolean addPlayer(Player player) {
        board.addPlayer(player);
        return false;
    }

    Position adjustPosition(Position position) {
        List<Snake> snakes = board.getSnakes();
        List<Ladder> ladders = board.getLadder();

        Position finalPosition = position;

        Snake validSnake = snakes.stream().filter(snake -> snake.getStartPos().equals(position)).findFirst().orElse(null);
        Ladder validLadder = ladders.stream().filter(snake -> snake.getStartPos().equals(position)).findFirst().orElse(null);

        while ((validLadder != null) || (validSnake != null)) {
            while (validLadder != null) {
                Position newPosition = validLadder.getEndPos();
                System.out.println("Ladder prev " + position + " new " + newPosition);
                validLadder = ladders.stream().filter(snake -> snake.getStartPos().equals(newPosition)).findFirst().orElse(null);
                validSnake = snakes.stream().filter(snake -> snake.getStartPos().equals(newPosition)).findFirst().orElse(null);
                finalPosition = newPosition;
            }

            while (validSnake != null) {
                Position newPosition  = validSnake.getEndPos();
                System.out.println("SNAKE prev " + position + " new " + newPosition);
                validSnake = snakes.stream().filter(snake -> snake.getStartPos().equals(newPosition)).findFirst().orElse(null);
                validLadder = ladders.stream().filter(snake -> snake.getStartPos().equals(newPosition)).findFirst().orElse(null);
                finalPosition = newPosition;
            }
        }

        return finalPosition;
    }

    boolean isOutOfBounds(Position position) {
        int x = position.getX();
        int y = position.getY();
        int[][] gameBoard = board.getBoard();

        int maxRows = gameBoard.length;
        int maxCols = gameBoard[0].length;

        if(x >= maxRows || y >=maxCols) {
            return true;
        }
        return false;
    }

    void printBoard() {
        char[][] gameBoard = new char[board.getEndPos().getX() + 1][board.getEndPos().getY() + 1];

        for (int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = '_';
            }
        }

        List<Snake> snakeList = board.getSnakes();
        List<Ladder> ladderList = board.getLadder();
        List<Player> playerList = board.getPlayers();

        for (Snake s: snakeList) {
            Position p = s.getStartPos();
            gameBoard[p.getX()][p.getY()] = s.getSymbol();
        }
        for (Ladder l: ladderList) {
            Position p = l.getStartPos();
            gameBoard[p.getX()][p.getY()] = l.getSymbol();
        }
        for (Player player: playerList) {
            Position p = player.getPosition();
            gameBoard[p.getX()][p.getY()] = player.getName().charAt(player.getName().length() - 1);
        }

        for (char[] chars : gameBoard) {
            System.out.print("[");
            for (int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(chars[j] + ", ");
            }
            System.out.println("]");
        }
    }

}
