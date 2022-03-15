// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import frc.robot.commands.*;
import frc.robot.utilities.Math2d;
import frc.robot.utilities.ShotData;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterFourBall extends SequentialCommandGroup {
  /** Creates a new CenterThreeBall. */
  double[][] waypoints = new double[][] {
    {234, 154},
    {250, 127},
    {240, 101},
    {205, 85}
  };
  
  double[] headings = new double[] {45, Math2d.goalAngle(waypoints[waypoints.length-1]), Math2d.goalAngle(waypoints[waypoints.length-1])};
  
  double[][] second_waypoints = new double[][] {
    //{210.55701985663293, 81.42407408006575},
    waypoints[waypoints.length-1],
    {134.9283759897607, 82.27224165321236},
    //{77.34747668202843, 71.24606320230626},
    //{51.564984454685614, 55.13087931252043}
    {77.34747668202843, 70}, //67},
    {30, 50}
  };
  double[] second_headings = new double[] {30, 45, 45};
  
  public CenterFourBall() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetOdometry(234, 154, 0),
      new SetIntake(true),
      new SetShot(ShotData.FEET12),
      new DriveSwerveProfile2(waypoints, headings, 0.3),
      new SetShooterOn(true),
      new Wait(2000),
      new SetShooterOn(false),
      //new DriveToPose(20, 49, 30, 0.5),
      /*
      new DriveToPose(35, 40, 30, 0.4),
      new Wait(1500),
      new SetShot(ShotData.FEET10),
      new DriveToAlignedPose(200, 165, 0.5),
      new SetShooterOn(true),
      new SetIntake(false),
      new Wait(2000),
      new SetShooterOn(false),
      new SetShooter(0)
      */
      new DriveSwerveProfile3(second_waypoints, second_headings, 0.4),
      new Wait(1500),
      new SetShot(ShotData.FEET10),
      new DriveToAlignedPose(200, 165, 0.5),
      new SetShooterOn(true),
      new SetIntake(false),
      new Wait(2000),
      new SetShooterOn(false),
      new SetShooter(0) 
    );
  }
}
