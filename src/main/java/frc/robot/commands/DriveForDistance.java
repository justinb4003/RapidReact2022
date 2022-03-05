// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.utilities.Math2d;

public class DriveForDistance extends CommandBase {
  /** Creates a new DriveForDistance. */
  double distance, xspeed, yspeed;
  Pose2d initialPose;
  public DriveForDistance(double distance, double xspeed, double yspeed) {
    this.distance = distance;
    this.xspeed = xspeed;
    this.yspeed = yspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialPose = RobotContainer.swerveDrive.getOdometry().getPoseMeters();
    RobotContainer.swerveDrive.setFieldRelative(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Pose2d pose = RobotContainer.swerveDrive.getOdometry().getPoseMeters();
    double targetAngle = Math2d.goalAngle(new double[] {pose.getX(), pose.getY()});
    double deltaAngle = targetAngle - pose.getRotation().getDegrees();
    while (deltaAngle < -180) deltaAngle += 360;
    while (deltaAngle > 180) deltaAngle -= 360;
    double rotSpeed = deltaAngle/180;
    RobotContainer.swerveDrive.drive(xspeed * RobotContainer.swerveDrive.kMaxSpeed, 
      yspeed * RobotContainer.swerveDrive.kMaxSpeed, 
      rotSpeed * RobotContainer.swerveDrive.kMaxAngularSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveDrive.stopDriveMotor();
    RobotContainer.swerveDrive.setFieldRelative(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Pose2d current = RobotContainer.swerveDrive.getOdometry().getPoseMeters();
    double deltaX = current.getX() - initialPose.getX();
    double deltaY = current.getY() - initialPose.getY();
    double currentDistance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    //System.out.println("current distance = " + currentDistance);
    return currentDistance > distance;
  }
}
