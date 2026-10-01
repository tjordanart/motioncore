package motioncore;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MotionWindow extends JFrame {

    public MotionWindow() {

        setTitle("MotionCore");

        // 1280x720 application window
        setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create animation area
        AnimationPanel animationPanel =
                new AnimationPanel();

        // Create timeline
        TimelinePanel timelinePanel =
                new TimelinePanel(animationPanel.timeline);

        // Create controls
        ControlPanel controlPanel =
                new ControlPanel(
                        animationPanel.timeline,
                        animationPanel
                );

        // Use BorderLayout for the editor
        setLayout(new BorderLayout());

        // Top controls
        add(controlPanel, BorderLayout.NORTH);

        // Main animation area
        add(animationPanel, BorderLayout.CENTER);

        // Timeline
        add(timelinePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}