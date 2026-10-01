package motioncore;

public class Interpolator {

    // Calculates a position between two keyframes
    public static double interpolate(double start, double end, double progress) {
        return start + (end - start) * progress;
    }

}