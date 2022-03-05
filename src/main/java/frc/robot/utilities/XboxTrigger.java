// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class XboxTrigger extends Trigger {
    XboxController controller;
    boolean right;

    public XboxTrigger(XboxController controller, boolean right) {
        this.controller = controller;
        this.right = right;
    }

    public boolean get() {
        if(right) return controller.getRightTriggerAxis() > 0.2; 
        return controller.getLeftTriggerAxis() > 0.2;
    }  
}
