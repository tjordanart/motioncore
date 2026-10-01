package motioncore;

import java.util.ArrayList;

public class Animation {

    // All keyframes belonging to this animation
    ArrayList<Keyframe> keyframes =
            new ArrayList<>();

    // Current timeline position
    double currentTime = 0;

    public Animation(Keyframe start, Keyframe end) {

        keyframes.add(start);
        keyframes.add(end);
    }

    // Add a new keyframe
    public void addKeyframe(Keyframe keyframe) {

        keyframes.add(keyframe);

        // Keep keyframes sorted by time
        keyframes.sort(
                (a, b) -> Double.compare(a.time, b.time)
        );
    }

    // Get the X position at the current time
    public double getX() {

        if (keyframes.isEmpty()) {
            return 0;
        }

        // Before first keyframe
        if (currentTime <= keyframes.get(0).time) {
            return keyframes.get(0).x;
        }

        // After last keyframe
        if (currentTime >=
                keyframes.get(keyframes.size() - 1).time) {

            return keyframes
                    .get(keyframes.size() - 1)
                    .x;
        }

        // Find the two surrounding keyframes
        for (int i = 0;
             i < keyframes.size() - 1;
             i++) {

            Keyframe start = keyframes.get(i);
            Keyframe end = keyframes.get(i + 1);

            if (currentTime >= start.time &&
                currentTime <= end.time) {

                double progress =
                        (currentTime - start.time) /
                        (end.time - start.time);

                return Interpolator.interpolate(
                        start.x,
                        end.x,
                        progress
                );
            }
        }

        return 0;
    }

    // Get the Y position at the current time
    public double getY() {

        if (keyframes.isEmpty()) {
            return 0;
        }

        // Before first keyframe
        if (currentTime <= keyframes.get(0).time) {
            return keyframes.get(0).y;
        }

        // After last keyframe
        if (currentTime >=
                keyframes.get(keyframes.size() - 1).time) {

            return keyframes
                    .get(keyframes.size() - 1)
                    .y;
        }

        // Find the two surrounding keyframes
        for (int i = 0;
             i < keyframes.size() - 1;
             i++) {

            Keyframe start = keyframes.get(i);
            Keyframe end = keyframes.get(i + 1);

            if (currentTime >= start.time &&
                currentTime <= end.time) {

                double progress =
                        (currentTime - start.time) /
                        (end.time - start.time);

                return Interpolator.interpolate(
                        start.y,
                        end.y,
                        progress
                );
            }
        }

        return 0;
    }
}