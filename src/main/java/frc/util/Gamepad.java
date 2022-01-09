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
  public static final int kGamepadLeftStickX = 0;
  public static final int kGamepadLeftStickY = 1;
  // shooter stop
  public static final int kGamepadTriggerLeft = 2;
  public static final int kGamepadTriggerRight = 3;
  public static final int kGamepadRightStickX = 4;
  public static final int kGamepadRightStickY = 5;

  // GAMEPAD BUTTONS
  // Intake Toggle Solenoids
  public static final int kGamepadButtonA = 1;
  // Intake Vomit
  public static final int kGamepadButtonB = 2;
  // Pistons
  public static final int kGamepadButtonX = 3;
  // Storage
  public static final int kGamepadButtonY = 4;
  // Shooter
  public static final int kGamepadButtonShoulderL = 5;
  // Intake
  public static final int kGamepadButtonShoulderR = 6;

  public static final int kGamepadButtonBack = 7;

  // Climb
  public static final int kGamepadButtonStart = 8;
  public static final int kGamepadButtonLeftStick = 9;

  // Rollwinch
  public static final int kGamepadButtonRightStick = 10;
  public static final int kGamepadButtonMode = -1;
  public static final int kGamepadButtonLogitech = -1;

  public double getRawAxis(int axis) {
    return super.getRawAxis(axis);
  }

  public double getLeftX() {
    return super.getRawAxis(kGamepadLeftStickX);
  }

  public double getLeftY() {
    return super.getRawAxis(kGamepadLeftStickY);
  }

  public double getRightX() {
    return super.getRawAxis(kGamepadRightStickX);
  }

  public double getRightY() {
    return super.getRawAxis(kGamepadRightStickY);
  }

  public double getLeftTrigger() {
    return super.getRawAxis(kGamepadTriggerLeft);
  }

  public double getRightTrigger() {
    return super.getRawAxis(kGamepadTriggerRight);
  }
}