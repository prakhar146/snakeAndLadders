package models;

abstract class GameEntity {
    protected Position startPos;
    protected Position endPos;
    protected char symbol;

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public char getSymbol() {
        return symbol;
    }
}

public class Snake extends GameEntity {

    public Snake(Position startPos, Position endPos, char symbol) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.symbol = symbol;
    }
}