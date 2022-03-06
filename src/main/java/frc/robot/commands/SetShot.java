// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utilities.ShotData;

public class SetShot extends CommandBase {
  /** Creates a new SetShot. */
  int distance;
  boolean on = false;
  public SetShot(int distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.distance = distance;
  }

  public SetShot(int distance, boolean on) {
    this(distance);
    this.on = on;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(on){
      ShotData.setShot(distance,true);
    }
    else{
      ShotData.setShot(distance);
    }
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //ShotData.setShot(distance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
