package frc.robot;

import frc.util.Gamepad;

public class ButtonMap {
  public final int joystickOnePort = 0;
  public final int gamepadOnePort = 1;

  public final int drivetrainShiftButton = 1; //joystick
  public final int climberWinchForwardButton = Gamepad.kGamepadRightStick;
  public final int climberWinchReverseButton = Gamepad.kGamepadLeftStick; 
  public final int climberPivotButton = Gamepad.kGamepadButtonStart;
  public final int climberPivotReverseButton = Gamepad.kGamepadButtonBack; 

  public final int intakeSpinButton = Gamepad.kGamepadButtonB;
  public final int intakeReverseSpinButton = Gamepad.kGamepadButtonA; 
  public final int intakeToggleSolenoidButton = Gamepad.kGamepadButtonY; 

  public final int storageInButton = Gamepad.kGamepadTriggerLeft;
  public final int storageOutButton = Gamepad.kGamepadTriggerRight;

  public final int storageMoveBallsUpButton = Gamepad.kGamepadButtonShoulderR;
  public final int storageMoveBallsReverseButton = Gamepad.kGamepadButtonShoulderL;
  public final int storageMoveBallsInButton = Gamepad.kGamepadButtonX;
  // public final int storageMoveBallsOutButton;

//   public final int drivetrainReverseDirectionButton = 3;
//   public final int drivetrainCoolFalconsButton = -3;

//   public final int visionAlignButton = 4;
//   public final int visionRequestBayButton = 7;
//   public final int visionRequestPortButton = 11;
//   public final int visionRequestBallButton = 9;
//   public final int visionRequestConnectButton = 12;

//   public final int startRecordingButton = 5;
//   public final int endRecordingButton = 6;

//   public final int turretToggleTestingButton = 8;

}
