// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
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
    topPhotoEye = new AnalogInput(5);
    bottomPhotoEye = new AnalogInput(4);
  }

  public void setIntakeOn(boolean on) {
    intakeOn = on;
    RobotContainer.pneumatics.setValve(Pneumatics.INTAKE, on);
  }

  public void setShooterOn(boolean on) {
    shooterOn = on;
    if(!on) RobotContainer.shooter.setSpeed(0);
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
    boolean debug = false;
    boolean bottomOn;
    boolean topOn;
    if (debug) {
      bottomOn = bottomEyeActive;
      topOn = topEyeActive;
    } else {
      bottomOn = bottomPhotoEye.getVoltage() > 0.8;
      topOn = topPhotoEye.getVoltage() > 0.8;
      //System.out.println(topPhotoEye.getVoltage());
    } 
    SmartDashboard.putBoolean("Top Eye", topOn);
    SmartDashboard.putBoolean("Bottom Eye", bottomOn);
    boolean intakeSubsystemOn = false;
    boolean entrySubsystemOn = false;
    boolean deliverySubsystemOn = false;
  
    switch (state) {
      case EMPTY: {
        if(bottomOn) state = ONEBALLLOW;
        if(topOn) state = ONEBALLHIGH;
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

    intakeSubsystemOn = true;
    entrySubsystemOn = true;
    deliverySubsystemOn = true;
    if (topOn) {
      deliverySubsystemOn = false;
      if (bottomOn) {
        entrySubsystemOn = false;
        intakeSubsystemOn = false;
      }
    }

    if (shooterOn && RobotContainer.shooter.isUpToSpeed()) {
      deliverySubsystemOn = true;
      entrySubsystemOn = true;
    }
    //System.out.println(shooterOn);
    if (intakeOn || (shooterOn && RobotContainer.shooter.isUpToSpeed())) {
      if (intakeOn) RobotContainer.ballIntake.setPowerOn(intakeSubsystemOn);
      else RobotContainer.ballIntake.setPowerOn(false);
      RobotContainer.ballDelivery.setPowerOn(deliverySubsystemOn);
      RobotContainer.ballEntry.setPowerOn(entrySubsystemOn);
      
    } else{
      RobotContainer.ballIntake.setPowerOn(false);
      RobotContainer.ballDelivery.setPowerOn(false);
      RobotContainer.ballEntry.setPowerOn(false);
    }

    SmartDashboard.putNumber("ball state", state);
  
  }
}
