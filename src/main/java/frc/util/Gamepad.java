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

  
  // GAMEPAD BUTTONS
  public static final int kGamepadTriggerLeft = 7;

  //axes
  public static final int kGamepadRightStickX = 2;
  public static final int kGamepadRightStickY = 3;
  public static final int kGamepadLeftStickX = 0;



 

  //Climber Winch
  public static final int kGamepadButtonShoulderL = 5; 
  //Arm extension
  public static final int kGamepadLeftStickY = 1;
  //Intake Extend/Retract
  public static final int kGamepadButtonX = 1;
  //Intake SpinRoller
  public static final int kGamepadButtonA = 2; 
  public static final int kGamepadButtonB = 3; 


  //Storage Run
  public static final int kGamepadTriggerRight = 8; 
  public static final int kGamepadButtonShoulderR = 6; 
  public static final int kGamepadButtonY = 4; 
  

  




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