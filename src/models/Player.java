package models;

public class Player {
    private String name;
    private Position position;

    public Player(String name) {
        this.name = name;
        position = new Position(0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
