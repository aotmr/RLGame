package state;

public class StateMgr {
    private State curr = null;
    private State next = null;

    public StateMgr(State start) {
        this.next = start;
    }

    public void setNextState(State next) {
        this.next = next;
    }

    public void update(float dt) {
        if (next != null) {
            next.cleanup();
            curr = next;
            curr.setup();
        }
        curr.update(dt);
    }

    public void draw() {
        if (curr != null)
            curr.draw();
    }

    public void cleanup() {
        if (curr != null)
            curr.cleanup();
    }
}
