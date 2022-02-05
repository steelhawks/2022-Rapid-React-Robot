package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.commands.Drivetrain.ShiftGears;


//Not made yet
// import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
// import frc.robot.commands.drivetrain.DrivetrainShiftGear;


public class DriverJoystickController {

  private final Controller controller;

  public DriverJoystickController(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {

    //TODO: add all buttons on the joystick

    // DRIVETRAIN
    this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton)
       .whenPressed(new ShiftGears());

  }
}