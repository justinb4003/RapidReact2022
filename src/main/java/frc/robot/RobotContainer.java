// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.sql.rowset.serial.SerialArray;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  public static Gyro gyro = new Gyro();
  public static SwerveDriveTrain swerveDrive = new SwerveDriveTrain();
  public static Limelight limelight = new Limelight();
  
  XboxController driver = new XboxController(0);
  //XboxController operator = new XboxController(1);

  Command m_autoCommand = new ThreeBallAuto(); //DriveToPose(100, 20, 90, 0.4);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    swerveDrive.setDefaultCommand(new SwerveDriveCommand(driver));
    configureButtonBindings();
    m_autoCommand = new DriveSwerveProfile(new double[][] {{0,0},{75,75},{150,0}}, 
                                        new double[] {90, 180}, 0.35);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton driverX = new JoystickButton(driver, XboxController.Button.kX.value);
    JoystickButton driverB = new JoystickButton(driver, XboxController.Button.kB.value);
    JoystickButton driverY = new JoystickButton(driver, XboxController.Button.kY.value);
    driverX.whenPressed(new SetFieldRelative(false));
    driverB.whenPressed(new SetFieldRelative(true));
    driverY.whenPressed(new AlignToTarget());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    swerveDrive.resetOdometry();
    swerveDrive.setOdometry(300, 70, 90);
    double[][] waypoints = new double[][] {{300, 29},{222,73},{115,69},{56,56}};
    double[] headings = new double[] {0, 20, 20};
    //m_autoCommand = new DriveSwerveProfile(waypoints, headings, 0.35);
    m_autoCommand = new FourBallAuto();
    return m_autoCommand;
  }
}
