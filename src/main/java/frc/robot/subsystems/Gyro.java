// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import com.kauailabs.navx.frc.AHRS;

public class Gyro extends SubsystemBase {
  /** Creates a new Gyro. */
  private final AHRS gyro = new AHRS(Port.kUSB1);
  Rotation2d initialHeading = new Rotation2d();
  public Gyro() {}

  public void setInitialHeading(double heading) {
    initialHeading = Rotation2d.fromDegrees(heading);
  }

  public void reset() {
    gyro.reset();
  }

  public Rotation2d getRotation2d() {
    return gyro.getRotation2d().plus(initialHeading);
  }

  public double getAccelX() {
    return gyro.getRawAccelX();
  }

  public double getAccelY() {
    return gyro.getRawAccelY();
  }

  public double getAccelZ() {
    return gyro.getRawAccelZ();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(gyro.getRotation2d());
  }
}
