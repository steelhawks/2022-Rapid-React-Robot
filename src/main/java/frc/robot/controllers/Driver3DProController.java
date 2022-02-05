package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.*;

//Not made yet
// import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
// import frc.robot.commands.drivetrain.DrivetrainShiftGear;


public class Driver3DProController {

  private final Controller controller;

  public Driver3DProController(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {

    // DRIVETRAIN

    // this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton)
    //   .whenPressed(new DrivetrainShiftGear());

    // this.controller.mapButton(Robot.BUTTON_MAP.drivetrainReverseDirectionButton)
    //     .whenPressed(new DrivetrainReverseDirection());


  }
}