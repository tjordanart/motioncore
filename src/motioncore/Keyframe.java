package motioncore;

public class Keyframe {

    // Time position of the keyframe in seconds
    double time;

    // X and Y position of the animated object
    double x;
    double y;

    // Creates a keyframe at a specific time and position
    public Keyframe(double time, double x, double y) {
        this.time = time;
        this.x = x;
        this.y = y;
    }

}