// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.utilities.Math2d;
import frc.robot.utilities.ShotData;

public class RightFiveBall2 extends SequentialCommandGroup {
  double[][] first_waypoints = new double[][] {
    {298, 25},
    {288.76391294623943, 57.37171717825354},
    {252.66842382795951, 86.20941466523873},
    //{210.55701985663293, 81.42407408006575}
    {206, 79}
  };
  double[] first_headings = new double[] {45, 20, Math2d.goalAngle(first_waypoints[first_waypoints.length-1])};
  /*
  double[][] second_waypoints = new double[][] {
    //{210.55701985663293, 81.42407408006575},
    first_waypoints[first_waypoints.length-1],
    {134.9283759897607, 82.27224165321236},
    //{77.34747668202843, 71.24606320230626},
    //{51.564984454685614, 55.13087931252043}
    {77.34747668202843, 70}, //67},
    {30, 50}
  };
  */
  double[][] second_waypoints = new double[][] {
    {206.54110500358627, 79.7277389337725},
    {148.5384549185883, 88.20941466523873},
    {86.23872279569983, 77.18323621433262},   
    //{38.267902416795145, 51.73820901993393}
    {25, 51.7}
  };
  double[] second_headings = new double[] {30, 45, 45};

  /** Creates a new RightFiveBall2. */
  public RightFiveBall2() {
    int shot1 = ShotData.FEET12;
    int shot2 = ShotData.FEET12;
    int shot3 = ShotData.FEET12;

    addCommands(new SetOdometry(296, 72, 90),
      new SetIntake(true),
      new SetShooterSpeed(ShotData.speeds[shot1]),
      new DriveToAlignedPose(first_waypoints[0][0], first_waypoints[0][1], 0.3),
      new SetShot(shot1, true),
      new Wait(800),
      new DriveSwerveProfile3(first_waypoints, first_headings, 0.4),
      new SetShot(shot2, true),
      new Wait(1800),
      new SetShooter(0),
      new DriveSwerveProfile3(second_waypoints, second_headings, 0.5),
      new Wait(1200),
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
