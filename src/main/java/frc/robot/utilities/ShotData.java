// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Pneumatics;

/** Add your docs here. */
public class ShotData {
    public static final int BUMPER = 0;
    public static final int FEET6 = 1;
    public static final int FEET8 = 2;
    public static final int FEET10 = 3;
    public static final int FEET12 = 4;
    public static final int FEET14 = 5;
    public static final int FEET16 = 6;
    public static final int FEET18 = 7;
    public static final int FEET20 = 8;
    public static final int FEET22 = 9;
    public static final int FEET24 = 10;
    public static final int LOWGOAL = 11;
    public static final int FEET9 = 12;
    public static double[] speeds = new double[] {
        //9500, 10000, 10000, 11000, 11500, 12500, 13500, 14000, 15000, 16000, 17000, 5000, 10500
        6000, 8000, 9000, 10000, 11500, 12500, 13500, 14000, 15000, 16000, 17000, 5000, 10500
    };
    public static boolean[] diverter = new boolean[] {
        false, false, true, true, true, true, true, true, true, true, true, true, true
    };
    
    public static void setShot(int distance) {
        RobotContainer.shooter.setSpeed(speeds[distance]);
        RobotContainer.pneumatics.setValve(Pneumatics.DIVERTER, diverter[distance]);
    }

    public static void setShot(int distance, boolean on) {
        setShot(distance);
        RobotContainer.ballStateMachine.setShooterOn(on);
    }
    



}
