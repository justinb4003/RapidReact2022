// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//THIS IS TUNED TO THE FIELD IN OUR BUILD SPACE

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.utilities.Math2d;
import frc.robot.utilities.ShotData;

public class RightFiveBall3 extends SequentialCommandGroup {
  double[][] first_waypoints = new double[][] {
    {298, 28},
    {288.76391294623943, 57.37171717825354},
    {252.66842382795951, 86.20941466523873},
    //{210.55701985663293, 81.42407408006575}
    {200, 78}
  };
  double[] first_headings = new double[] {45, 20, Math2d.goalAngle(first_waypoints[first_waypoints.length-1])};
  //this works
  /*double[][] second_waypoints = new double[][] {
    {206.54110500358627, 79.7277389337725},
    {148.5384549185883, 86},
    {86.23872279569983, 72},   
    {24,48}
  
  }; */
  
  double[][] second_waypoints = new double[][] {
    {206.54110500358627, 79.7277389337725},
    {148.5384549185883, 82},
    {86.23872279569983, 70},   
    //{24,48}
    {38, 38}
  
  };
  double[] second_headings = new double[] {30, 45, 45};

  /** Creates a new RightFiveBall2. */
  public RightFiveBall3() {
    int shot1 = ShotData.FEET12;
    int shot2 = ShotData.FEET12;
    int shot3 = ShotData.FEET10;

    addCommands(new SetOdometry(296, 72, 90),
      new SetIntake(true),
      new SetShooterSpeed(ShotData.speeds[shot1]),
      new DriveToAlignedPose(first_waypoints[0][0], first_waypoints[0][1], 0.35),
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
        new DriveToAlignedPose(240, 60, 0.6)
      ),
      new SetShot(shot3, true),
      new Wait(1500),
      new SetIntake(false),
      new SetShooter(0)
    );

  }

}
