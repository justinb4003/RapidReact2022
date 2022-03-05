// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN ids
    public static final int BALLDELIVERYLEADER = 30;
    public static final int BALLDELIVERYFOLLOWER = 31;
    public static final int BALLINTAKE = 40;
    public static final int BALLENTRYLEADER = 50;
    public static final int BALLENTRYFOLLOWER = 51;
    public static final int LEFTSHOOTER = 60;
    public static final int RIGHTSHOOTER = 61;

    // Solenoid channels
    public static final int INTAKE = 0;
    public static final int DIVERTER = 1;
    
    // DIO
    public static final int BOTTOM_EYE = 0;
    public static final int TOP_EYE = 9;
}
