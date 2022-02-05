package frc.robot;

import frc.util.Gamepad;

public class ButtonMap {

  //Driver Joystick Port
  public final int joystickPort = 0;

  //Xbox Controller Port
  public final int gamepadPort = 1;

  //Storage Function Buttons
  public final int storageMoveUpButton = Gamepad.kGamepadButtonY;
  public final int StorageMoveReverseButton = Gamepad.kGamepadButtonB;

  public final int drivetrainShiftButton = 1;
  public final int drivetrainReverseDirectionButton = 3;
  public final int drivetrainCoolFalconsButton = -3;

}