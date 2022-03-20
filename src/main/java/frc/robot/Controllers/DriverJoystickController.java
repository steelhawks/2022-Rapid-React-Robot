package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Robot;
import frc.robot.commands.Drivetrain.ShiftGears;
import frc.robot.commands.Pathcorder.EndRecording;
import frc.robot.commands.Pathcorder.StartRecording;
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
    Button shift = this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton);
    shift.whenPressed(new ShiftGears());

    Button startRecording = this.controller.mapButton(Robot.BUTTON_MAP.startRecordingButton);
    startRecording.whenPressed(new StartRecording());
    
    Button endRecording = this.controller.mapButton(Robot.BUTTON_MAP.endRecordingButton);
    endRecording.whenPressed(new EndRecording());


    // // VISION
    // this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightDown)
    //     .whenPressed(new LimelightDown());
        
    // this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightUp)
    //     .whenPressed(new LimelightUp());

    this.controller.mapButton(Robot.BUTTON_MAP.visionGoToBall)
        .whenPressed(new GoToBall());

    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignToHub)
        .whenPressed(new AlignToHub());
  }
}