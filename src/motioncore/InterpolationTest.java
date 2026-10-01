/*package motioncore;

public class InterpolationTest {

    public static void main(String[] args) {

        // Create the starting keyframe
        Keyframe start = new Keyframe(0, 100, 300);

        // Create the ending keyframe
        Keyframe end = new Keyframe(2, 700, 300);

        // Set the current time
        double currentTime = 1.0;

        // Print out
        System.out.println("Start: " + start.x + ", " + start.y);
        System.out.println("End: " + end.x + ", " + end.y);

        // Calculate how far through the animation we are
        double progress = (currentTime - start.time) / (end.time - start.time);

        System.out.println("Progress: " + progress);

        // Calculate the current position
        double currentX = Interpolator.interpolate(start.x, end.x, progress);
        double currentY = Interpolator.interpolate(start.y, end.y, progress);

        System.out.println("Current X: " + currentX);
        System.out.println("Current Y: " + currentY);
    }

}*/