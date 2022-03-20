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

    public static Shot[] shots = new Shot[12];
    public static double[] speeds = new double[13];
    /* {
        //9500, 10000, 10000, 11000, 11500, 12500, 13500, 14000, 15000, 16000, 17000, 5000, 10500
        6000, 8000, 9000, 10000, 11500, 12500, 13500, 14000, 15000, 16000, 17000, 5000, 10500
    };*/
    static {
        shots[BUMPER] = new Shot(5000, false);
        shots[FEET6] = new Shot(5500, false);
        shots[FEET8] = new Shot(6000, true);
        shots[FEET9] = new Shot(6500, true);
        shots[FEET10] = new Shot(7000, true);
        shots[FEET12] = new Shot(8000, true);
        shots[FEET14] = new Shot(9000, true);
        shots[FEET16] = new Shot(10000, true);
        shots[FEET18] = new Shot(11000, true);
        shots[FEET20] = new Shot(12000, true);
        shots[FEET22] = new Shot(13000, true);
        shots[FEET24] = new Shot(14000, true);
        shots[LOWGOAL] = new Shot(4000, true);
        
        speeds[BUMPER] = 5000;
        speeds[FEET6] = 5000;
        speeds[FEET8] = 6000;
        speeds[FEET10] = 7000;
        speeds[FEET12] = 8000;
        speeds[FEET14] = 9000;
        speeds[FEET16] = 10000;
        speeds[FEET18] = 11000;  //estimate
        speeds[FEET20] = 12000;  //estimate
        speeds[FEET22] = 13000;  //estimate
        speeds[FEET24] = 14000;  //estimate
        speeds[LOWGOAL] = 4000;  //estimate
        speeds[FEET9] = 6500;  //estimate
    }
    public static boolean[] diverter = new boolean[] {
        false, false, true, true, true, true, true, true, true, true, true, true, true
    };
    
    public static void setShot(int distance) {
        /*
        RobotContainer.shooter.setSpeed(speeds[distance]);
        RobotContainer.pneumatics.setValve(Pneumatics.DIVERTER, diverter[distance]);
        */
        RobotContainer.shooter.setSpeed(shots[distance].speed);
        RobotContainer.pneumatics.setValve(Pneumatics.DIVERTER, shots[distance].position);
    }

    public static void setShot(int distance, boolean on) {
        setShot(distance);
        RobotContainer.ballStateMachine.setShooterOn(on);
    }
    



}
