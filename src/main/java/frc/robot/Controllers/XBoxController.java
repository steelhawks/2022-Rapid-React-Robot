package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.commands.Storage.StorageRun;
import frc.robot.commands.Storage.StorageReverse;
import frc.robot.commands.Climber.RollWinch;
import frc.robot.commands.Intake.*; 



public class XBoxController {

  private final Controller controller;

  public XBoxController(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {

    //TODO: add all buttons on the xbox controller


    //Storage 
    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsUpButton)
        .whenHeld(new StorageRun(Robot.STORAGE));

    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsReverseButton)
        .whenHeld(new StorageReverse(Robot.STORAGE));

    //Intake
    this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinButton)
        .whenHeld(new IntakeSpin());
    this.controller.mapButton(Robot.BUTTON_MAP.intakeReverseSpinButton)
        .whenHeld(new IntakeSpinReverse());
    this.controller.mapButton(Robot.BUTTON_MAP.intakeToggleSolenoidButton)
        .whenPressed(new ToggleSolenoid());

    //Climber 
    this.controller.mapButton(Robot.BUTTON_MAP.climberRollWinchButton)
        .whenHeld(new RollWinch());

  }
}
