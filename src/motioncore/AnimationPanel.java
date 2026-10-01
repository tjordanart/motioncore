package motioncore;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimationPanel extends JPanel {

    // The master timeline
    Timeline timeline;

    // Animation timer
    Timer timer;

    // Currently selected layer
    Layer selectedLayer;

    // Mouse drag information
    int dragOffsetX;
    int dragOffsetY;

    public AnimationPanel() {

        // Create the timeline
        timeline = new Timeline();

        // Create circle animation
        Keyframe start = new Keyframe(0, 100, 300);
        Keyframe end = new Keyframe(2, 700, 300);

        Animation circleAnimation =
                new Animation(start, end);

        Layer circleLayer =
                new Layer(
                        circleAnimation,
                        new Color(0, 200, 255),
                        "circle"
                );

        timeline.addLayer(circleLayer);

        // Create square animation
        Keyframe secondStart =
                new Keyframe(0, 400, 100);

        Keyframe secondEnd =
                new Keyframe(4, 400, 500);

        Animation squareAnimation =
                new Animation(secondStart, secondEnd);

        Layer squareLayer =
                new Layer(
                        squareAnimation,
                        new Color(255, 120, 50),
                        "square"
                );

        timeline.addLayer(squareLayer);

        // Animation timer
        timer = new Timer(16, e -> {

            timeline.update(0.016);

            repaint();
        });

        timer.start();

        // Mouse interaction
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                // Check layers from top to bottom
                for (int i = timeline.layers.size() - 1;
                     i >= 0;
                     i--) {

                    Layer layer =
                            timeline.layers.get(i);

                    double x =
                            layer.animation.getX();

                    double y =
                            layer.animation.getY();

                    // Check if mouse clicked the object
                    if (e.getX() >= x &&
                        e.getX() <= x + 50 &&
                        e.getY() >= y &&
                        e.getY() <= y + 50) {

                        selectedLayer = layer;

                        // Remember where inside the object
                        // the mouse was clicked
                        dragOffsetX =
                                (int) (e.getX() - x);

                        dragOffsetY =
                                (int) (e.getY() - y);

                        repaint();

                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                // Stop dragging
                if (selectedLayer != null) {
                    repaint();
                }
            }
        });

        // Mouse dragging
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (selectedLayer == null) {
                    return;
                }

                Animation animation =
                        selectedLayer.animation;

                // Calculate new position
                double newX =
                        e.getX() - dragOffsetX;

                double newY =
                        e.getY() - dragOffsetY;

                // Calculate how far the object moved
                double deltaX =
                        newX - animation.getX();

                double deltaY =
                        newY - animation.getY();

                // Move every keyframe
                for (Keyframe keyframe :
                        animation.keyframes) {

                    keyframe.x += deltaX;
                    keyframe.y += deltaY;
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Background
        g.setColor(new Color(30, 30, 30));

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Grid
        g.setColor(new Color(55, 55, 55));

        for (int x = 0;
             x < getWidth();
             x += 50) {

            g.drawLine(
                    x,
                    0,
                    x,
                    getHeight()
            );
        }

        for (int y = 0;
             y < getHeight();
             y += 50) {

            g.drawLine(
                    0,
                    y,
                    getWidth(),
                    y
            );
        }

        // Draw all layers
        for (Layer layer : timeline.layers) {

            double x =
                    layer.animation.getX();

            double y =
                    layer.animation.getY();

            g.setColor(layer.color);

            // Draw circle
            if (layer.type.equals("circle")) {

                g.fillOval(
                        (int) x,
                        (int) y,
                        50,
                        50
                );
            }

            // Draw square
            if (layer.type.equals("square")) {

                g.fillRect(
                        (int) x,
                        (int) y,
                        50,
                        50
                );
            }

            // Draw selection outline
            if (layer == selectedLayer) {

                g.setColor(Color.WHITE);

                g.drawRect(
                        (int) x - 3,
                        (int) y - 3,
                        56,
                        56
                );
            }
        }
    }
}