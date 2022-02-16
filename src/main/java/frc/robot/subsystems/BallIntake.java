// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallIntake extends SubsystemBase {
  VictorSPX intakeMotor;
  /** Creates a new BallIntake. */
  public BallIntake() {
    intakeMotor = new VictorSPX(Constants.BALLINTAKE);
  }

  public void setPowerOn(boolean on) {
    if(on) intakeMotor.set(VictorSPXControlMode.PercentOutput, 1);
    else intakeMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
