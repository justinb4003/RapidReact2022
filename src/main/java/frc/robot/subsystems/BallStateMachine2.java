// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.lang.model.element.ElementVisitor;

import frc.robot.RobotContainer;

/** Add your docs here. */
public class BallStateMachine2 extends BallStateMachine {
    public static final int NONE = 0;
    public static final int ONELOW = 1;
    public static final int ONEHIGH = 2;
    public static final int TWO = 3;
    public static final int TRAVELING1 = 4;
    public static final int TRAVELING2 = 5;
    public BallStateMachine2() {
        state = NONE;
    }

    public void periodic() {
        if (RobotContainer.operator.getLeftY() > 0.5) {
            RobotContainer.ballIntake.backOut();
            RobotContainer.ballEntry.backOut();
            //RobotContainer.ballDelivery.backOut();
            return;
        }
        boolean deliverySystemOn = false;
        boolean entrySystemOn = false;

        boolean bottomOn = bottomPhotoEye.getVoltage() > 0.8;
        boolean topOn = topPhotoEye.getVoltage() > 0.8;
        switch(state) {
            case NONE: {
                entrySystemOn = true;
                deliverySystemOn = false;
                if (bottomOn) state = ONELOW;
                if (topOn) state = ONEHIGH;
                break;
            }
            case ONELOW: {
                entrySystemOn = true;
                deliverySystemOn = true;
                if (!bottomOn) state = TRAVELING1;
                break;
            }
            case ONEHIGH: {
                entrySystemOn = true;
                deliverySystemOn = false;
                if (bottomOn) state = TWO;
                if (!topOn) state = NONE;
                break;
            }
            case TWO: {
                entrySystemOn = false;
                deliverySystemOn = false;
                if (!topOn) state = ONELOW;
                break;
            }
            case TRAVELING1: {
                entrySystemOn = true;
                deliverySystemOn = true;
                if (topOn) state = ONEHIGH;
                if (bottomOn) state = TRAVELING2;
                break;
            }
            case TRAVELING2: {
                entrySystemOn = false;
                deliverySystemOn = true;
                if (topOn) state = TWO;
                break;
            }

        }
        System.out.println("BSM state: " + state);
        RobotContainer.ballIntake.setPowerOn(intakeOn);
        if (shooterOn && RobotContainer.shooter.isUpToSpeed()) {
            RobotContainer.ballDelivery.setPowerOn(true);
            RobotContainer.ballEntry.setPowerOn(true);
            return;
        }
        if (intakeOn){
            RobotContainer.ballEntry.setPowerOn(entrySystemOn);
            RobotContainer.ballDelivery.setPowerOn(deliverySystemOn);
        } else {
            RobotContainer.ballEntry.setPowerOn(false);
            RobotContainer.ballDelivery.setPowerOn(false);
        }

    }

}
