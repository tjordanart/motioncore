package motioncore;

import java.awt.Color;

public class Layer {

    // The animation for this layer
    Animation animation;

    // The color of the layer
    Color color;

    // The type of object
    String type;

    public Layer(Animation animation, Color color, String type) {
        this.animation = animation;
        this.color = color;
        this.type = type;
    }
}