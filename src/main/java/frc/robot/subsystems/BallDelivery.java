// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallDelivery extends SubsystemBase {
  VictorSPX leader; //, follower;
  /** Creates a new BallDelivery. */
  public BallDelivery() {
    leader = new VictorSPX(Constants.BALLDELIVERYLEADER);
    leader.setInverted(true);
    /*
    follower = new VictorSPX(Constants.BALLDELIVERYFOLLOWER);
    follower.setInverted(true);
    follower.set(VictorSPXControlMode.Follower, Constants.BALLDELIVERYLEADER);
    */
  }

  public void setPowerOn(boolean on) {
    if(on) leader.set(VictorSPXControlMode.PercentOutput, 1);
    else leader.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
