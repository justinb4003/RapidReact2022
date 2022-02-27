// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class BallStateMachine extends SubsystemBase {
  AnalogInput topPhotoEye, bottomPhotoEye;
  public static final int EMPTY = 0;
  public static final int ONEBALLLOW = 1;
  public static final int LOWTOHIGH = 2;
  public static final int ONEBALLHIGH = 3;
  public static final int ONEBALLLOWONEBALLMOVE = 4;
  public static final int TWOBALLS = 5;
  int state = EMPTY;
  boolean intakeOn = false;
  boolean shooterOn = false;
  boolean topEyeActive = false;
  boolean bottomEyeActive = false;

  /** Creates a new BallStateMachine. */
  public BallStateMachine() {
    /*
    topPhotoEye = new AnalogInput(0);
    bottomPhotoEye = new AnalogInput(1);
    */
  }

  public void setIntakeOn(boolean on) {
    intakeOn = on;
  }

  public void setShooterOn(boolean on) {
    shooterOn = on;
  }

  public void toggleTopEye() {
    topEyeActive = !topEyeActive;
  }

  public void toggleBottomEye() {
    bottomEyeActive = !bottomEyeActive;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    boolean debug = true;
    boolean bottomOn;
    boolean topOn;
    if (debug) {
      bottomOn = bottomEyeActive;
      topOn = topEyeActive;
    } else {
      bottomOn = bottomPhotoEye.getVoltage() > 0.8;
      topOn = topPhotoEye.getVoltage() > 0.8;
    } 
    SmartDashboard.putBoolean("Top Eye", topOn);
    SmartDashboard.putBoolean("Bottom Eye", bottomOn);
    boolean intakeSubsystemOn = false;
    boolean entrySubsystemOn = false;
    boolean deliverySubsystemOn = false;
  
    switch (state) {
      case EMPTY: {
        if(bottomOn) state = ONEBALLLOW;
        if(intakeOn) {
          intakeSubsystemOn = true;
          entrySubsystemOn = true;
          deliverySubsystemOn = true;
        }
        break;
      }
      case ONEBALLLOW: {
        if(!bottomOn) state = LOWTOHIGH;
        if(intakeOn) {
          intakeSubsystemOn = true;
          entrySubsystemOn = true;
          deliverySubsystemOn = true;
        }
        break;
      }
      case LOWTOHIGH: {
        if(bottomOn) state = ONEBALLLOWONEBALLMOVE;
        if(topOn) state = ONEBALLHIGH;
        if(intakeOn) {
          intakeSubsystemOn = true;
          entrySubsystemOn = true;
          deliverySubsystemOn = true;
        }
        break;
      }
      case ONEBALLLOWONEBALLMOVE: {
        if(topOn) state = TWOBALLS;
        if(intakeOn) {
          deliverySubsystemOn = true;
        }
        break;
      }
      case ONEBALLHIGH: {
        if(!topOn) state = EMPTY;
        if(bottomOn) state = TWOBALLS;
        if(intakeOn) {
          intakeSubsystemOn = true;
          entrySubsystemOn = true;
        }
        break;
      }
      case TWOBALLS: {
        if(!topOn) state = ONEBALLLOW;
        break;
      }
    }

    if (shooterOn && RobotContainer.shooter.isUpToSpeed()) {
      deliverySubsystemOn = true;
      entrySubsystemOn = true;
    }
    
    RobotContainer.ballIntake.setPowerOn(intakeSubsystemOn);
    RobotContainer.ballDelivery.setPowerOn(deliverySubsystemOn);
    RobotContainer.ballEntry.setPowerOn(entrySubsystemOn);
  
  }
}
