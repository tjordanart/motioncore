package motioncore;

import java.util.ArrayList;

public class Timeline {

    // All animation layers
    ArrayList<Layer> layers = new ArrayList<>();

    // Current position of the timeline
    double currentTime = 0;

    // Whether the timeline is playing
    boolean playing = true;

    // Timeline length in seconds
    double duration = 8;

    // Add a layer
    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    // Move the timeline forward
    public void update(double deltaTime) {

        if (!playing) {
            return;
        }

        currentTime += deltaTime;

        // Loop back to the beginning
        if (currentTime >= duration) {
            currentTime = 0;
        }

        // Set every animation to the timeline position
        for (Layer layer : layers) {
            layer.animation.currentTime = currentTime;
        }
    }
}