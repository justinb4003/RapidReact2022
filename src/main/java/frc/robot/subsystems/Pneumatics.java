// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
  /** Creates a new Pneumatics. */
  int valve_num = 3;
  public static final int INTAKE = 0;
  public static final int DIVERTER = 1;
  public static final int HANGINGLOCK = 2;
  Solenoid[] photoEyes = new Solenoid[4];
  int[] photoEyeChannels = new int[] {5,6,7,15};
  boolean[] states = new boolean[valve_num];
  Solenoid[] valves = new Solenoid[valve_num];
  public Pneumatics() {
    valves[INTAKE] = new Solenoid(2, PneumaticsModuleType.REVPH, Constants.INTAKE);
    valves[DIVERTER] = new Solenoid(2, PneumaticsModuleType.REVPH, Constants.DIVERTER);
    valves[HANGINGLOCK] = new Solenoid(2, PneumaticsModuleType.REVPH, Constants.HANGING_LOCK);

    states[INTAKE] = false;
    states[DIVERTER] = true;
    states[HANGINGLOCK] = true;

    for (int v = 0; v < valve_num; v++) setValve(v, states[v]);
    
    for (int i = 0; i < 4; i++) {
      photoEyes[i] = new Solenoid(2, PneumaticsModuleType.REVPH, photoEyeChannels[i]);
      photoEyes[i].set(true);
    }
  }

  public void toggleDiverter() {
    states[DIVERTER] = !states[DIVERTER];
    valves[DIVERTER].set(states[DIVERTER]);
  }

  public void setValve(int valve, boolean state) {
    if (valve == DIVERTER) return;
    states[valve] = state;
    valves[valve].set(state);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //for (int i = 0; i < valve_num; i++) valves[i].set(states[i]);
  }
}
