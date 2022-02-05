package frc.robot;

import frc.util.Gamepad;

public class ButtonMap {

  //Driver Joystick Port
  public final int joystickPort = 0;

  //Xbox Controller Port
  public final int gamepadPort = 1;

  //Storage Function Buttons (Found in Gamepad.java)
  public final int storageMoveUpButton = Gamepad.gamepadButtonY;
  public final int StorageMoveReverseButton = Gamepad.gamepadButtonB;

  public final int drivetrainShiftButton = 1;
  public final int drivetrainReverseDirectionButton = 3;
  public final int drivetrainCoolFalconsButton = -3;

}