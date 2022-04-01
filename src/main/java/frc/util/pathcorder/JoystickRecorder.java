/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.pathcorder;

public class JoystickRecorder {
  public final double velocityLeft;
  public final double velocityRight;
  public final boolean shifted;
  public final int count;

  public JoystickRecorder(double velocityLeft, double velocityRight, boolean shifted, int count) {
    this.velocityLeft = velocityLeft;
    this.velocityRight = velocityRight;
    this.shifted = shifted;
    this.count = count;
  }

  public String toString() {
    return "Lvelocity: " + this.velocityLeft + "\nRvelocity: " + this.velocityRight + "\nShifted: " + this.shifted;
  }

}