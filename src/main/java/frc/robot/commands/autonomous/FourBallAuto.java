// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallAuto extends SequentialCommandGroup {
  /** Creates a new FourBallAuto. */
  public FourBallAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    double[][] waypoints = new double[][] {{300, 29},{222,73},{122,69},{39,60}};
    double[] headings = new double[] {0, 20, 20};
    //m_autoCommand = new DriveSwerveProfile(waypoints, headings, 0.35);
    addCommands(

      new DriveToPose(300, 30, 75, 0.3),
      new Wait(1000),
      new DriveSwerveProfile(waypoints, headings, 0.5),
      new Wait(1000),
      new DriveToPose(165, 102, 22, 0.5)


    );
  }
}
