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
  TalonFX leader, follower;
  double speed = 0;
  public Shooter() {
    leader = new TalonFX(Constants.LEFTSHOOTER);
    leader.setInverted(true);
    follower = new TalonFX(Constants.RIGHTSHOOTER);
    follower.setInverted(false);
    follower.set(TalonFXControlMode.Follower, Constants.LEFTSHOOTER);
    leader.config_kF(0, 1023/20000.0);
    leader.config_kP(0, 0.01);
    leader.setNeutralMode(NeutralMode.Coast);
  }

  public void setSpeed(double speed) {
    this.speed = speed;
    leader.set(TalonFXControlMode.Velocity, speed);
  }

  public boolean isUpToSpeed() {
    if (Math.abs(speed) < 100) return false;
    return Math.abs(leader.getSelectedSensorVelocity() - speed)/speed < 0.15;
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
    leader.set(TalonFXControlMode.Velocity, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(speed + " " + leader.getSelectedSensorVelocity());
  }
}
