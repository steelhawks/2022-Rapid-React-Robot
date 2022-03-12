package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Vision.*;


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


    // VISION
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightDown)
        .whenPressed(new LimelightDown());
        
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightUp)
        .whenPressed(new LimelightUp());

    this.controller.mapButton(Robot.BUTTON_MAP.visionGoToBall)
        .whileHeld(new GoToBall());

    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignToHub)
        .whileHeld(new AlignToHub());
  }
}