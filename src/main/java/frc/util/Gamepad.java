/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/* NOTE IMPORTANT: SET THE SWITCH ON THE BACK TO D and NOT X */
/* NOTE IMPORTANT: SET THE SWITCH ON THE BACK TO D and NOT X */
/* NOTE IMPORTANT: SET THE SWITCH ON THE BACK TO D and NOT X */
/* NOTE IMPORTANT: SET THE SWITCH ON THE BACK TO D and NOT X */
/* NOTE IMPORTANT: SET THE SWITCH ON THE BACK TO D and NOT X */

package frc.util;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick {

  public Gamepad(int port) {
    super(port);
  }

  // GAMEPAD AXES
  public static final int gamepadLeftStickX = 0;
  public static final int gamepadLeftStickY = 1;
  public static final int gamepadRightStickX = 2;
  public static final int gamepadRightStickY = 3;
  
  // GAMEPAD BUTTONS
  public static final int gamepadButtonX = 1;
  public static final int gamepadButtonA = 2;
  public static final int gamepadButtonB = 3;
  public static final int gamepadButtonY = 4;
  public static final int gamepadButtonShoulderL = 5;
  public static final int gamepadButtonShoulderR = 6;  
  public static final int gamepadTriggerLeft = 7;
  public static final int gamepadTriggerRight = 8;
  public static final int gamepadButtonBack = 9;

  // Climb
  public static final int gamepadButtonStart = 8;
  public static final int gamepadButtonLeftStick = 9;

  // Rollwinch
  public static final int gamepadButtonRightStick = 10;
  public static final int gamepadButtonMode = -1;
  public static final int gamepadButtonLogitech = -1;

  public double getRawAxis(int axis) {
    return super.getRawAxis(axis);
  }

  public double getLeftX() {
    return super.getRawAxis(gamepadLeftStickX);
  }

  public double getLeftY() {
    return super.getRawAxis(gamepadLeftStickY);
  }

  public double getRightX() {
    return super.getRawAxis(gamepadRightStickX);
  }

  public double getRightY() {
    return super.getRawAxis(gamepadRightStickY);
  }

  // Not used in axis mode

  // public double getLeftTrigger() {
  //   return super.getRawAxis(gamepadTriggerLeft);
  // }

  // public double getRightTrigger() {
  //   return super.getRawAxis(gamepadTriggerRight);
  // }
}