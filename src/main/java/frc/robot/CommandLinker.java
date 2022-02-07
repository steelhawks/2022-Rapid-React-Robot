package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Controllers.DriverJoystickController;
import frc.robot.Controllers.XBoxController;
import frc.robot.commands.Drivetrain.DiffDrive;
import frc.util.Gamepad;



public class CommandLinker 
{
  /*****
   * Joystick Objects
   *****/

  public final Joystick driveJoystick = new Joystick(Robot.BUTTON_MAP.joystickOnePort);
  public final Gamepad operatorGamepad = new Gamepad(Robot.BUTTON_MAP.gamepadOnePort);
  private final DriverJoystickController driverController = new DriverJoystickController(driveJoystick);
  private final XBoxController xboxController = new XBoxController(operatorGamepad);

  public CommandLinker() {}

  
  public void configureRegisteredSubsystems() {
    CommandScheduler.getInstance().registerSubsystem(Robot.INTAKE);
    CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
    CommandScheduler.getInstance().registerSubsystem(Robot.STORAGE);
    CommandScheduler.getInstance().registerSubsystem(Robot.CLIMBER);
  }

  public void configurePeriodicBindings() {
    CommandScheduler.getInstance().setDefaultCommand(Robot.DRIVETRAIN, new DiffDrive());
  }

  public void configureCommands()
  {

    driverController.mapButtons();
    xboxController.mapButtons();
  }
}