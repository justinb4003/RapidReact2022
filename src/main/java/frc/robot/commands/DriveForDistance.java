// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.xml.xpath.XPathEvaluationResult;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.RobotData;
import frc.robot.subsystems.SwerveDriveTrain;
import frc.robot.utilities.Math2d;
import frc.robot.utilities.Point2d;

public class DriveForDistance extends CommandBase {
  /** Creates a new DriveForDistance. */
  double speed, distance;
  Pose2d initialPose;
  double currentSpeed = 0;
  Point2d direction;
  public DriveForDistance(double distance, double[] dir, double speed) {
    this.distance = distance;
    direction = new Point2d(dir[0], dir[1]);
    direction = Math2d.smult(1/direction.length(), direction);
    this.speed = speed;
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
    double deltaX = pose.getX() - initialPose.getX();
    double deltaY = pose.getY() - initialPose.getY();
    double currentDistance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);

    if (distance - currentDistance < 0.5*Math.pow(currentSpeed*RobotData.maxSpeed, 2)/RobotData.maxAcceleration) {
      currentSpeed -= 0.02*RobotData.maxAcceleration;
    } else {
      currentSpeed += 0.02*RobotData.maxAcceleration;
      if (currentSpeed > speed * RobotData.maxSpeed) currentSpeed = speed * RobotData.maxSpeed;
    }
    Point2d velocity = Math2d.smult(currentSpeed, direction);
    double targetAngle = Math2d.goalAngle(new double[] {pose.getX(), pose.getY()});
    double deltaAngle = targetAngle - pose.getRotation().getDegrees();
    while (deltaAngle < -180) deltaAngle += 360;
    while (deltaAngle > 180) deltaAngle -= 360;
    double rotSpeed = deltaAngle/180;
    RobotContainer.swerveDrive.drive(velocity.x, velocity.y,
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
    return currentDistance > distance - 0.25;
  }
}