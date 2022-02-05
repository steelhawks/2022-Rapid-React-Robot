package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.commands.Storage.RunStorage;
import frc.robot.commands.Storage.ReverseStorage;
import frc.robot.ButtonMap;
import frc.robot.commands.Climber.RollWinch;
import frc.robot.commands.Intake.*; 






public class XBox {

  private final Controller controller;

  public XBox(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {


    //Storage 
    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsUpButton)
        .whenHeld(new RunStorage(Robot.STORAGE));

    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsReverseButton)
        .whenHeld(new ReverseStorage(Robot.STORAGE));

    //Intake
    this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinButton)
        .whenHeld(new IntakeSpin());

    this.controller.mapButton(Robot.BUTTON_MAP.intakeReverseSpinButton)
        .whenHeld(new IntakeSpinReverse());
    this.controller.mapButton(Robot.BUTTON_MAP.intakeSolenoidButton)
        .whenPressed(new ToggleSolenoid());

    //Climber 
    this.controller.mapButton(Robot.BUTTON_MAP.climberRollWinchButton)
        .whenHeld(new RollWinch());

  }
}
