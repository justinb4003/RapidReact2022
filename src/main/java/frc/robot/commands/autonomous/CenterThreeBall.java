// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterThreeBall extends SequentialCommandGroup {
  /** Creates a new CenterThreeBall. */
  double[][] waypoints = new double[][] {
    {234,154},
    {200, 90},
    {115, 90},
    {35, 65}
  };
  
  double[] headings = new double[] {45, 0, 30};
  public CenterThreeBall() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetOdometry(234, 154, 0),
      new DriveSwerveProfile(waypoints, headings, 0.5),
      new Wait(1000),
      new DriveToAlignedPose(200, 165, 0.5)
    );
  }
}
