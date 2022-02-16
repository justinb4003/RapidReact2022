// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SetOdometry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LeftFourBall extends SequentialCommandGroup {
  double[][] waypoints = new double[][] {
    {204, 234},
    {200, 86},
    {113, 83},
    {25, 55}
  };
  double[] headings = new double[] {45, 0, 30};
  public LeftFourBall() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetOdometry(240, 200, -45),
      new DriveToAlignedPose(204, 234, 0.4),
      new Wait(1000),
      new DriveSwerveProfile(waypoints, headings, 0.5),
      new Wait(1000),
      new DriveToAlignedPose(175, 200, 0.55)
    );
  
  }
}
