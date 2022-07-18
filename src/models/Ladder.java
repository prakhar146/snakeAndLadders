package models;

public class Ladder extends GameEntity {

    public Ladder(Position startPos, Position endPos, char symbol) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.symbol = symbol;
    }
}