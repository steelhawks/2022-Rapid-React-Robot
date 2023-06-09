package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Robot;
import frc.robot.commands.Drivetrain.ReverseDriveDirection;
import frc.robot.commands.Drivetrain.ShiftGears;
import frc.robot.commands.Pathcorder.EndRecording;
import frc.robot.commands.Pathcorder.StartRecording;
import frc.robot.commands.Storage.RunAllOfStorage;
import frc.robot.commands.Storage.StopAllOfStorage;
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

    // DRIVETRAIN
    Button shift = this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton);
    shift.whenPressed(new ShiftGears());

    Button reverse = this.controller.mapButton(Robot.BUTTON_MAP.drivetrainReverseDirectionButton);
    reverse.whenPressed(new ReverseDriveDirection());



    Button startRecording = this.controller.mapButton(Robot.BUTTON_MAP.startRecordingButton);
    startRecording.whenPressed(new StartRecording());
    
    Button endRecording = this.controller.mapButton(Robot.BUTTON_MAP.endRecordingButton);
    endRecording.whenPressed(new EndRecording());

    // VISION
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightDown) // 12
        .whenPressed(new LimelightDown());
        
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightUp) // 11
        .whenPressed(new LimelightUp());
        
    this.controller.mapButton(Robot.BUTTON_MAP.storageRunAllStorage)
        .whenPressed(new RunAllOfStorage()); //=9 
        
    this.controller.mapButton(Robot.BUTTON_MAP.storageStopAllStorage)
        .whenPressed(new StopAllOfStorage()); //=10 

    // this.controller.mapButton(Robot.BUTTON_MAP.visionGoToBall)
    //     .whenPressed(
    //         new SequentialCommandGroup(
    //   new ParallelRaceGroup(
    //       new AutoShoot(), new WaitCommand(2)),
    //   new IntakeToggleSolenoid(),
    //   // new SampleAutopath0(), // same thing as (shoot) and (go towards general area of ball)

    //   //if proceeding be sure the sampleautpath0 does not drive over the ball. Need some sufficient room in front to see ball.
    //   new ParallelRaceGroup(
    //       new GoToBall(), new RunAllOfStorage(), new WaitCommand(2) // then Go to ball for hopefully 2 seconds, spinning intake for all of it.
    //     ); 

    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignToHub)
        .whenPressed(new AlignToHub()); // 8 for rotate to hub, optionally a straight To Hub command.
    
  }
}