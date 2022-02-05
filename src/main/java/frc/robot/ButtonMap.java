package frc.robot;

import frc.util.Gamepad;

public class ButtonMap {
  public final int joystickOnePort = 0;
  public final int gamepadOnePort = 1;


  public final int climberRollWinchButton = Gamepad.kGamepadButtonMode;

  public final int intakeSpinButton = Gamepad.kGamepadButtonShoulderR;
  public final int intakeReverseSpinButton = Gamepad.kGamepadButtonX;
  public final int intakeSolenoidButton = Gamepad.kGamepadButtonA; 

  public final int shooterSpinForwardButton = Gamepad.kGamepadButtonShoulderL;

  public final int storageMoveBallsForwardButton = Gamepad.kGamepadButtonY;
  public final int StorageMoveBallsReverseButton = Gamepad.kGamepadButtonB;

  public final int drivetrainShiftButton = 1;
  public final int drivetrainReverseDirectionButton = 3;
  public final int drivetrainCoolFalconsButton = -3;

  public final int visionAlignButton = 4;
  public final int visionRequestBayButton = 7;
  public final int visionRequestPortButton = 11;
  public final int visionRequestBallButton = 9;
  public final int visionRequestConnectButton = 12;

  public final int startRecordingButton = 5;
  public final int endRecordingButton = 6;

  public final int turretToggleTestingButton = 8;

}
