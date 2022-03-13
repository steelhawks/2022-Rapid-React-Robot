package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Robot;
import frc.robot.commands.Drivetrain.ShiftGears;
import frc.robot.commands.Storage.StorageTest;


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
    Button shift = this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton);
    shift.whenPressed(new ShiftGears());

    //TEST
    Button top = this.controller.mapButton(11);
    top.whileHeld(new StorageTest(Robot.STORAGE, 0));
    Button bottom = this.controller.mapButton(12);
    bottom.whileHeld(new StorageTest(Robot.STORAGE, 1));

  }
}