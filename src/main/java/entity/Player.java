package entity;

public class Player {
    private Player() {
        throw new AssertionError("Player class may not be instantiated");
    }

    public static Entity create(EntityList parent) {
        return parent.create("Player");
    }
}
