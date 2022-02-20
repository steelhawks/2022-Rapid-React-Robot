package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.DriverJoystickController;
import frc.robot.Controllers.XBoxController;
import frc.robot.commands.Drivetrain.DiffDrive;
import frc.robot.commands.Drivetrain.ShiftGears;
import frc.util.Gamepad;



public class CommandLinker 
{
  /*****
   * Joystick Objects
   *****/

  public final Joystick driveJoystick = new Joystick(0);
  public final Gamepad operatorGamepad = new Gamepad(1);
  private final DriverJoystickController driverController = new DriverJoystickController(this.driveJoystick);
  private final XBoxController xboxController = new XBoxController(this.operatorGamepad);

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

    this.driverController.mapButtons();
    this.xboxController.mapButtons();
  }
}