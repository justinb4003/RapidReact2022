// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.utilities.Math2d;
import frc.robot.utilities.ShotData;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RightFiveBallAuto extends SequentialCommandGroup {
  double[][] waypoints = new double[][] {
    {334.3129825478784, 71.24606320230626},
    //{299.0769098371766, 19.507841240362247},
    {295, 27}, //30},
    {258.6843386810062, 62.764387470840035},
    //{225.7135183021015, 95}
    {224, 91}
  };
  double[] headings = new double[] {80, 45, Math2d.goalAngle(waypoints[waypoints.length - 1])};
  public RightFiveBallAuto() {
    int shot1 = ShotData.FEET10;
    int shot2 = ShotData.FEET14;
    int shot3 = ShotData.FEET12;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetOdometry(waypoints[0][0], waypoints[0][1], 90),
      new SetIntake(true),
      new SetShooterSpeed(ShotData.speeds[shot1]),
      new DriveSwerveProfile2(waypoints, headings, 0.3),
      new SetShot(shot1, true),
      new Wait(1000),
      new SetShot(shot2, true),
      new DriveForDistance(20, new double[] {-1, 0}, 0.25), //-0.25, 0),
      new Wait(1800),
      new SetShooter(0),
      //new DriveToPose(32, 50, 30, 0.6),
      new DriveToPose(50, 35, 30, 0.6),
      new Wait(1500),
      new ParallelCommandGroup(
        new SequentialCommandGroup(new Wait(1500), new SetShooterSpeed(ShotData.speeds[shot3])),
        new DriveToAlignedPose(220, 60, 0.6)
      ),
      new SetShot(shot3, true),
      new Wait(1500),
      new SetIntake(false),
      new SetShooter(0)
    
    );
  }
}
