// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

/** Add your docs here. */
public class Math2d {
    public static Point2d sum2d(Point2d u, Point2d v) {
        return new Point2d(u.x + v.x, u.y + v.y);
    }
    public static Point2d smult(double s, Point2d u) {
        return new Point2d(s*u.x, s*u.y);
    }
    public static Point2d diff2d(Point2d u, Point2d v) {
        return new Point2d(u.x-v.x, u.y-v.y);
    }
    
}
