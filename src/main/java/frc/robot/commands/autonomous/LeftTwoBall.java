// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.utilities.ShotData;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LeftTwoBall extends SequentialCommandGroup {
  /** Creates a new LeftTwoBall. */
  public LeftTwoBall() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetOdometry(240, 200, -45),
      new SetIntake(true),
      new SetShot(ShotData.FEET10),
      new DriveToAlignedPose(204, 234, 0.4),
      new Wait(1000),
      new SetShooterOn(true),
      new Wait(2000),
      new SetIntake(false),
      new SetShooterOn(false),
      new DriveForDistance(20, new double[] {-1,0}, 0.25) //-0.25, 0)
    );
  }
}
