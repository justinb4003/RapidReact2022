// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SetOdometry;
import frc.robot.commands.SetShooter;
import frc.robot.commands.SetShooterOn;
import frc.robot.commands.*;
import frc.robot.commands.SetShot;
import frc.robot.utilities.ShotData;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterOneBallAuto extends SequentialCommandGroup {
  /** Creates a new CenterOneBallAuto. */
  public CenterOneBallAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      new SetOdometry(240, 162, 0),
      new SetShot(ShotData.FEET8),
      new SetShooterOn(true),
      new Wait(2000),
      new DriveToAlignedPose(180, 162, .4),
      new SetShooterOn(false),
      new SetShooter(0)
    );
  }
}
