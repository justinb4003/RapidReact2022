// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SetOdometry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FiveCargo extends SequentialCommandGroup {
  double[][] waypoints = new double[][] {
    {290, 293},
    {181,240},
    {220, 150},
    {214,88},
    {101,85},
    {28,68}
  };
  double[] headings = new double[] {45, 0, 0, 0, 30};
  /** Creates a new FiveCargo. */
  public FiveCargo() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Wait(5000),
      new SetOdometry(240, 200, -45),
      new DriveToAlignedPose(290, 293, 0.4),
      new Wait(1000),
      new DriveSwerveProfile(waypoints, headings, 0.5),
      new Wait(1000),
      new DriveToAlignedPose(200, 165, 0.5)
    );
  }
}
