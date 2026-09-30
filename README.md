# MotionCore

A Java-based 2D animation engine and interactive animation editor built from scratch.

Create animations using keyframes, interpolation, timelines, and interactive object controls. The project is designed to explore how animation software can be built from the ground up using software engineering concepts.

## Overview

MotionCore is an experimental animation system that combines my background in motion graphics and animation with my studies in computer engineering.

The project starts with a basic keyframe interpolation system and is being expanded into an interactive animation editor with timeline controls, object manipulation, and eventually physics-based animation.

## Features

- Keyframe-based animation
- Linear interpolation
- Multiple keyframes per animation
- Interactive timeline
- Play and pause controls
- Timeline scrubbing
- Selectable objects
- Draggable objects
- Add keyframe functionality
- Draggable timeline keyframes
- Real-time animation playback

## Animation System

MotionCore uses keyframes to define an object's position at specific points in time.

For example:

| Time | X | Y |
|---|---:|---:|
| 0s | 100 | 300 |
| 2s | 700 | 300 |

The animation engine calculates the object's position between these keyframes using linear interpolation.

Additional keyframes can be added to create more complex animation paths.

## Timeline

The timeline controls the current position of the animation.

Users can:

- Play and pause the animation
- Scrub through the timeline
- Add keyframes at the current time
- Drag keyframes to change their timing
- View keyframes for individual animation layers

## Interactive Canvas

The animation canvas allows objects to be selected and manipulated directly.

Current objects include:

- Circle
- Square

Objects can be selected and dragged within the animation canvas.

## Planned Features

- Individual keyframe position editing
- Keyframe selection and deletion
- Rotation
- Scale
- Opacity
- Improved layer controls
- Improved timeline editing
- Animation paths
- Physics simulation
- Velocity
- Acceleration
- Gravity
- Friction
- Bounce
- Collision detection
- Project save and load
- JavaScript/browser version

## Technology

- Java
- Java Swing
- Object-oriented programming
- Keyframe interpolation
- Event-driven programming

## Requirements

- Java 17+
- Eclipse or another Java IDE

No external libraries are currently required.

## How to Run

Clone the repository:

```bash
git clone https://github.com/Tjordanart/motioncore.git
```

Navigate to the project directory:

```bash
cd motioncore
```

Open the project in Eclipse or another Java IDE.

Run:

```text
Main.java
```

The MotionCore animation editor will open in a new window.

## What I Practiced

This project provides practice with:

- Java programming
- Object-oriented programming
- Classes and objects
- ArrayLists
- Event handling
- Mouse interaction
- GUI programming
- Animation systems
- Keyframe interpolation
- Timeline systems
- Real-time rendering
- Software architecture

## Project Status

Early development

MotionCore is being developed as a long-term software engineering and creative technology project.

## Author

**Tyler Jordan**

[GitHub](https://github.com/Tjordanart)  
[Portfolio](https://www.tjordanart.com)
