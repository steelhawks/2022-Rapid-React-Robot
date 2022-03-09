/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.pathcorder;

import java.util.ArrayList;

public class AutonPath {
  public ArrayList<Double> joystickYValues;
  public ArrayList<Double> joystickRotationValues;
  public ArrayList<Double> encoderRightValues;
  public ArrayList<Double> encoderLeftValues;
  public ArrayList<Double> joystickButtonInputs;

  public AutonPath() {
    joystickYValues = new ArrayList<Double>(0);
    joystickRotationValues = new ArrayList<Double>(0);
    encoderRightValues = new ArrayList<Double>(0);
    encoderLeftValues = new ArrayList<Double>(0);
    joystickButtonInputs = new ArrayList<Double>(0);

  }
}