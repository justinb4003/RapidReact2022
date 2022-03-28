// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  TalonFX bottom, top;
  double speed = 0;
  double kP = 0.05;
  public Shooter() {
    top = new TalonFX(Constants.LEFTSHOOTER);
    top.setInverted(false);
    bottom = new TalonFX(Constants.RIGHTSHOOTER);
    bottom.setInverted(false);
    //follower.set(TalonFXControlMode.Follower, Constants.LEFTSHOOTER);
    top.config_kF(0, 1023/22000.0);
    top.config_kP(0, kP);
    top.setNeutralMode(NeutralMode.Coast);
    bottom.config_kF(0, 1023/18000.0);
    bottom.config_kP(0, kP);
    bottom.setNeutralMode(NeutralMode.Coast);
  }

  public void setSpeed(double speed) {
    this.speed = speed;
    bottom.set(TalonFXControlMode.Velocity, speed); // bottom
    top.set(TalonFXControlMode.Velocity, speed*1.5); // top
  }
  
  public boolean isUpToSpeed() {
    if (Math.abs(speed) < 100) return false;
    //return Math.abs(bottom.getSelectedSensorVelocity() - speed)/speed < 0.20;
    return true;
  }

  public void incrementSpeed(double increment) {
    speed += increment;
    if (speed < 0) speed = 0;
    if (Math.abs(speed) < 100) {
      speed = 0;
    }
    if (speed > 22000) speed = 22000;
    RobotContainer.ballStateMachine.setShooterOn(speed != 0.0);
    SmartDashboard.putNumber("Shooter speed:", speed);
    top.set(TalonFXControlMode.Velocity, speed);
  }

  public void setPercentOutput(double percent) {
    top.set(TalonFXControlMode.PercentOutput, percent);
    bottom.set(TalonFXControlMode.PercentOutput, percent);
  }

  public double getBottomSpeed() {
    return bottom.getSelectedSensorVelocity();
  }

  public double getTopSpeed() {
    return top.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(speed + " " + leader.getSelectedSensorVelocity());
  }
}
