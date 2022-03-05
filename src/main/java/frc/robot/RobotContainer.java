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
import frc.robot.utilities.ShotData;
import frc.robot.utilities.XboxTrigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  public static Limelight limelight;// = new Limelight();
  public static PixyCam2 pixyCam2; // = new PixyCam2();
  public static Pixycam pixycam;
  public static Shooter shooter = new Shooter();
  public static BallIntake ballIntake = new BallIntake();
  public static BallEntry ballEntry = new BallEntry();
  public static BallDelivery ballDelivery = new BallDelivery();
  public static BallStateMachine ballStateMachine = new BallStateMachine();
  public static Pneumatics pneumatics = new Pneumatics();
  
  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

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
    JoystickButton driverA = new JoystickButton(driver, XboxController.Button.kA.value);
    JoystickButton driverLBumper = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    JoystickButton driverRBumper = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    JoystickButton driverStartButton = new JoystickButton(driver, XboxController.Button.kStart.value);
    
    driverX.whenPressed(new SetFieldRelative(false));
    driverB.whenPressed(new SetFieldRelative(true));
    driverY.whenPressed(new AlignToTarget());
    //driverStartButton.whenPressed(new ToggleDiverter());
    /*
    driverY.whenPressed(new ToggleDriveAligned());
    driverA.whenPressed(new DriveToCargo(false));
    */

    //driverY.whenPressed(new IncrementShooterSpeed(500));
    //driverA.whenPressed(new IncrementShooterSpeed(-500));

    //driverA.whenPressed(new SetShooter(11000));
    //driverY.whenPressed(new SetShooter(0));
    // driverX.whenPressed(new TogglePhotoEye(true));
    // driverB.whenPressed(new TogglePhotoEye(false));

    JoystickButton operatorX = new JoystickButton(operator, XboxController.Button.kX.value);
    JoystickButton operatorY = new JoystickButton(operator, XboxController.Button.kY.value);
    JoystickButton operatorA = new JoystickButton(operator, XboxController.Button.kA.value);
    JoystickButton operatorB = new JoystickButton(operator, XboxController.Button.kB.value);
    JoystickButton operatorLBumper = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    JoystickButton operatorRBumper = new JoystickButton(operator, XboxController.Button.kRightBumper.value);
    XboxTrigger operatorLeftTrigger = new XboxTrigger(operator, false);
    XboxTrigger operatorRightTrigger = new XboxTrigger(operator, true);

    operatorLBumper.whenPressed(new SetIntake(true));
    operatorRBumper.whenPressed(new SetIntake(false));
    operatorX.whenPressed(new SetShot(ShotData.FEET12));
    operatorA.whenPressed(new SetShot(ShotData.FEET10));
    operatorY.whenPressed(new SetShot(ShotData.FEET16));
    operatorB.whenPressed(new SetShot(ShotData.BUMPER));
    operatorLeftTrigger.whenActive(new SetShooterOn(true));
    operatorRightTrigger.whenActive(new SetShooterOn(false));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    swerveDrive.resetOdometry();
    double[][] waypoints = new double[][] {{300, 29},{222,73},{115,69},{56,56}};
    double[] headings = new double[] {0, 20, 20};
    //m_autoCommand = new DriveSwerveProfile(waypoints, headings, 0.35);
    m_autoCommand = new RightFiveBallAuto();
    return m_autoCommand;
  }
}
