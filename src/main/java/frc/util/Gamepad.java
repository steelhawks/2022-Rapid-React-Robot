/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick {

  public Gamepad(int port) {
    super(port);
  }

  // GAMEPAD AXES
  public static final int gamepadLeftStickX = 0;
  public static final int gamepadLeftStickY = 1;
  // shooter stop
  public static final int gamepadTriggerLeft = 2;
  public static final int gamepadTriggerRight = 3;
  public static final int gamepadRightStickX = 4;
  public static final int gamepadRightStickY = 5;

  // GAMEPAD BUTTONS
  // Intake Toggle Solenoids
  public static final int gamepadButtonA = 1;
  // Intake Vomit
  public static final int gamepadButtonB = 2;
  // Pistons
  public static final int gamepadButtonX = 3;
  // Storage
  public static final int gamepadButtonY = 4;
  // Shooter
  public static final int gamepadButtonShoulderL = 5;
  // Intake
  public static final int gamepadButtonShoulderR = 6;

  public static final int gamepadButtonBack = 7;

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

  public double getLeftTrigger() {
    return super.getRawAxis(gamepadTriggerLeft);
  }

  public double getRightTrigger() {
    return super.getRawAxis(gamepadTriggerRight);
  }
}