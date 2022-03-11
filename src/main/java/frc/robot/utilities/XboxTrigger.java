// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class XboxTrigger extends Trigger {
    XboxController controller;
    int button;

    public static final int LEFT_TRIGGER = 0;
    public static final int RIGHT_TRIGGER = 1;
    public static final int DPAD_UP = 2;
    public static final int DPAD_DOWN = 3;

    public XboxTrigger(XboxController controller, int button) {
        this.controller = controller;
        this.button = button;
    }

    public boolean get() {
        switch(button) {
            case LEFT_TRIGGER: return controller.getLeftTriggerAxis() > 0.2;
            case RIGHT_TRIGGER: return controller.getRightTriggerAxis() > 0.2;
            case DPAD_UP: return controller.getPOV() == 0;
            case DPAD_DOWN: return controller.getPOV() == 180;
        }
        return false;
    }  
}
