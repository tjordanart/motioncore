package motioncore;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimelinePanel extends JPanel {

    Timeline timeline;

    // Currently selected keyframe
    Keyframe selectedKeyframe;

    // Animation containing the selected keyframe
    Animation selectedAnimation;

    public TimelinePanel(Timeline timeline) {

        this.timeline = timeline;

        setPreferredSize(
                new java.awt.Dimension(1280, 180)
        );

        // Keep timeline updated
        Timer timer = new Timer(16, e -> repaint());
        timer.start();

        // Mouse interaction
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                // First check if a keyframe was clicked
                Keyframe clickedKeyframe =
                        findKeyframe(e.getX(), e.getY());

                if (clickedKeyframe != null) {

                    selectedKeyframe =
                            clickedKeyframe;

                    repaint();

                    return;
                }

                // Otherwise move the playhead
                movePlayhead(e.getX());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                selectedKeyframe = null;
                selectedAnimation = null;

                repaint();
            }
        });

        // Drag keyframes
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (selectedKeyframe == null) {
                    return;
                }

                // Convert mouse position into time
                double time =
                        (e.getX() - 100) / 140.0;

                // Keep inside timeline
                if (time < 0) {
                    time = 0;
                }

                if (time > timeline.duration) {
                    time = timeline.duration;
                }

                // Move keyframe
                selectedKeyframe.time = time;

                // Keep keyframes sorted
                if (selectedAnimation != null) {

                    selectedAnimation.keyframes.sort(
                            (a, b) ->
                                    Double.compare(
                                            a.time,
                                            b.time
                                    )
                    );
                }

                repaint();
            }
        });
    }

    // Find a keyframe under the mouse
    private Keyframe findKeyframe(
            int mouseX,
            int mouseY) {

        for (int i = 0;
             i < timeline.layers.size();
             i++) {

            Layer layer =
                    timeline.layers.get(i);

            int rowY =
                    100 + (i * 40);

            for (Keyframe keyframe :
                    layer.animation.keyframes) {

                int keyframeX =
                        100 +
                        (int) (
                                keyframe.time * 140
                        );

                // 10px click area around diamond
                if (Math.abs(mouseX - keyframeX) <= 10 &&
                    Math.abs(mouseY - rowY) <= 10) {

                    selectedAnimation =
                            layer.animation;

                    return keyframe;
                }
            }
        }

        return null;
    }

    // Move the playhead
    private void movePlayhead(int mouseX) {

        double time =
                (mouseX - 100) / 140.0;

        if (time < 0) {
            time = 0;
        }

        if (time > timeline.duration) {
            time = timeline.duration;
        }

        timeline.currentTime = time;

        // Synchronize animations
        for (Layer layer :
                timeline.layers) {

            layer.animation.currentTime =
                    timeline.currentTime;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Background
        g.setColor(
                new Color(25, 25, 25)
        );

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Header
        g.setColor(Color.WHITE);

        g.drawString(
                "TIMELINE",
                20,
                25
        );

        // Time markers
        g.setColor(
                new Color(120, 120, 120)
        );

        for (int second = 0;
             second <= timeline.duration;
             second++) {

            int x =
                    100 + (second * 140);

            g.drawLine(
                    x,
                    40,
                    x,
                    55
            );

            g.drawString(
                    second + "s",
                    x - 8,
                    70
            );
        }

        // Layer names
        g.setColor(Color.WHITE);

        for (int i = 0;
             i < timeline.layers.size();
             i++) {

            Layer layer =
                    timeline.layers.get(i);

            int y =
                    105 + (i * 40);

            g.drawString(
                    layer.type,
                    20,
                    y
            );
        }

        // Layer lines
        g.setColor(
                new Color(70, 70, 70)
        );

        for (int i = 0;
             i < timeline.layers.size();
             i++) {

            int y =
                    100 + (i * 40);

            g.drawLine(
                    100,
                    y,
                    getWidth(),
                    y
            );
        }

        // Draw keyframes
        for (int i = 0;
             i < timeline.layers.size();
             i++) {

            Layer layer =
                    timeline.layers.get(i);

            int y =
                    100 + (i * 40);

            g.setColor(layer.color);

            for (Keyframe keyframe :
                    layer.animation.keyframes) {

                int x =
                        100 +
                        (int) (
                                keyframe.time * 140
                        );

                // Diamond
                int[] xPoints = {
                        x,
                        x + 7,
                        x,
                        x - 7
                };

                int[] yPoints = {
                        y - 7,
                        y,
                        y + 7,
                        y
                };

                g.fillPolygon(
                        xPoints,
                        yPoints,
                        4
                );

                // Highlight selected keyframe
                if (keyframe == selectedKeyframe) {

                    g.setColor(Color.WHITE);

                    g.drawPolygon(
                            xPoints,
                            yPoints,
                            4
                    );

                    g.setColor(layer.color);
                }
            }
        }

        // Playhead
        int playheadX =
                100 +
                (int) (
                        timeline.currentTime * 140
                );

        g.setColor(Color.WHITE);

        g.drawLine(
                playheadX,
                35,
                playheadX,
                160
        );
    }
}