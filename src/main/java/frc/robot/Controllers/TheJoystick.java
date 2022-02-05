package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;


//Not made yet
// import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
// import frc.robot.commands.drivetrain.DrivetrainShiftGear;


public class TheJoystick {

  private final Controller controller;

  public TheJoystick(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {

    // DRIVETRAIN
    // this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton)
    //   .whenPressed(new DrivetrainShiftGear());

    // this.controller.mapButton(Robot.BUTTON_MAP.drivetrainReverseDirectionButton)
    //     .whenPressed(new ReverseDrivetrain());


  }
}