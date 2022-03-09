/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.pathcorder;

public class JoystickRecorder {
  public final double joystickY;
  public final double joystickTwist;
  public final boolean shifted;
  public final int count;

  public JoystickRecorder(double joystickY, double joystickTwist, boolean shifted, int count) {
    this.joystickY = joystickY;
    this.joystickTwist = joystickTwist;
    this.shifted = shifted;
    this.count = count;
  }

  public String toString() {
    return "Y: " + this.joystickY + "\nTwist: " + this.joystickTwist + "\nShifted: " + this.shifted;
  }

}