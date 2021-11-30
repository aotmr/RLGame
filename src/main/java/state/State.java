package state;

public interface State {
    default void setup() {}
    default void update(float dt) {}
    default void draw() {}
    default void cleanup() {}
}
