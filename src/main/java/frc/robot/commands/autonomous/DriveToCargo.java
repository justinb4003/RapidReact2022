// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class DriveToCargo extends CommandBase {
  boolean blue;
  boolean finish = false;
  int nullCount = 0;
  /** Creates a new DriveToCargo. */
  public DriveToCargo(boolean blue) {
    this.blue = blue;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.swerveDrive.setFieldRelative(false);
    finish = false;
    nullCount = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Block block;
    if(blue) block = RobotContainer.pixyCam2.getBlueBlock();
    else block = RobotContainer.pixyCam2.getRedBlock();
    if(block == null){
      nullCount++;
      return;
    } 
    else nullCount = 0;
    if(nullCount >= 10){
      finish = true;
      return;
    }
    if(block.getX() > 260){
      finish = true;
      return;
    }
    double yDifference = block.getY() - 120;
    double rotationPower = 0.02 * yDifference;
    RobotContainer.swerveDrive.drive(-0.45 * RobotContainer.swerveDrive.kMaxSpeed, 0, rotationPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveDrive.setBrakeMode();
    RobotContainer.swerveDrive.stopDriveMotor();
    RobotContainer.swerveDrive.setFieldRelative(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
