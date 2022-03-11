// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SwerveDriveTrain;

public class AlignToTarget extends CommandBase {
  boolean finished = false;
  double kP = 0.017;
  double tolerance = 1;
  long stopTime;
  /** Creates a new AlignToTarget. */
  public AlignToTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stopTime = System.currentTimeMillis() + 1500;
  }
  double powerThreshold = .10;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.limelight.isTargetValid()){
      double x = -(RobotContainer.limelight.getTargetX()+1.5);
      finished = Math.abs(x) < tolerance;
      double rotationPower = kP * x;
      if(Math.abs(rotationPower)<powerThreshold){
        if(rotationPower<0) rotationPower = -powerThreshold;
        else rotationPower = powerThreshold;
      }
      rotationPower *= SwerveDriveTrain.kMaxAngularSpeed;
      RobotContainer.swerveDrive.drive(0, 0, rotationPower);
    }
    else {
      RobotContainer.swerveDrive.stopDriveMotor();
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveDrive.stopDriveMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished || System.currentTimeMillis() > stopTime;
  }
}
