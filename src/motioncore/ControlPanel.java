package motioncore;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ControlPanel extends JPanel {

    Timeline timeline;

    JButton playButton;
    JButton addKeyframeButton;

    AnimationPanel animationPanel;

    public ControlPanel(
            Timeline timeline,
            AnimationPanel animationPanel) {

        this.timeline = timeline;
        this.animationPanel = animationPanel;

        setLayout(
                new FlowLayout(FlowLayout.LEFT)
        );

        // Play / Pause button
        playButton =
                new JButton("Pause");

        playButton.addActionListener(e -> {

            timeline.playing =
                    !timeline.playing;

            if (timeline.playing) {

                playButton.setText("Pause");

            } else {

                playButton.setText("Play");
            }
        });

        add(playButton);

        // Add Keyframe button
        addKeyframeButton =
                new JButton("Add Keyframe");

        addKeyframeButton.addActionListener(e -> {

            // Make sure an object is selected
            if (animationPanel.selectedLayer == null) {
                return;
            }

            Layer layer =
                    animationPanel.selectedLayer;

            Animation animation =
                    layer.animation;

            // Get the object's current position
            double x =
                    animation.getX();

            double y =
                    animation.getY();

            // Create a keyframe at the current time
            Keyframe keyframe =
                    new Keyframe(
                            timeline.currentTime,
                            x,
                            y
                    );

            // Add it to the animation
            animation.addKeyframe(keyframe);

            animationPanel.repaint();
        });

        add(addKeyframeButton);
    }
}