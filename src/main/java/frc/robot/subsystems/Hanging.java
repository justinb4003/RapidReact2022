// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Hanging extends SubsystemBase {
  /** Creates a new Hanging. */
  TalonFX hanger; 
  double encoderOffset;
  double topEncoderLimit = 240000;
  double bottomEncoderLimit = 0;
  public Hanging() {
    hanger = new TalonFX(Constants.HANGING);
    hanger.setNeutralMode(NeutralMode.Brake);
    
    encoderOffset = hanger.getSelectedSensorPosition();

  }

  public double getPosition() {
    return hanger.getSelectedSensorPosition() - encoderOffset;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double power = -RobotContainer.operator.getRightY();
    if(Math.abs(power) < 0.1) power = 0;
    power = power * 0.6;
    if(power > 0 && getPosition() > topEncoderLimit) power = 0;
    if(RobotContainer.operator.getPOV() != 270) {
      if(power < 0 && getPosition() < bottomEncoderLimit) power = 0;
    } else {
      if (power < -0.1) power = -0.2;
    }
    

    hanger.set(TalonFXControlMode.PercentOutput, power);
    //System.out.println(getPosition());
  }
}
