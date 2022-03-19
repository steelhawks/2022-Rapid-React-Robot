package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.AutoShoot;
import frc.robot.commands.Drivetrain.ReverseDriveDirection;
import frc.robot.commands.Drivetrain.ShiftGears;
import frc.robot.commands.Intake.IntakeToggleSolenoid;
import frc.robot.commands.Pathcorder.EndRecording;
import frc.robot.commands.Pathcorder.StartRecording;
import frc.robot.commands.Storage.RunAllOfStorage;
import frc.robot.commands.Storage.StopAllOfStorage;
import frc.robot.commands.Storage.StorageTest;
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

    // //TESTINGGGGGGGGGGGGGGGGGGGGGGGGGGG
    // Button top = this.controller.mapButton(11);
    // top.whileHeld(new StorageTest(0));
    // Button bottom = this.controller.mapButton(12);
    // bottom.whileHeld(new StorageTest(1));



    // // VISION
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightDown)
        .whenPressed(new LimelightDown());  //12 i think
        
    this.controller.mapButton(Robot.BUTTON_MAP.visionFaceLimelightUp)
        .whenPressed(new LimelightUp()); //11 ??
        
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
    //     ); // 7 for funsies

    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignToHub)
        .whenPressed(new AlignToHub()); // 8 for rotate to hub, optionally a straight To Hub command.
    
  }
}