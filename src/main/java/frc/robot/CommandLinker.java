package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;



public class CommandLinker 
{
  /*****
   * Joystick Objects
   *****/
  public final Joystick Joystick = new Joystick(Robot.ROBOTMAP.getJoystickPortOne());

  public CommandLinker() 
  {
    //configureCommands();
  }

  public void configureRegisteredSubsystems() {
    CommandScheduler.getInstance().registerSubsystem(Robot.INTAKE);
    CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
  }

  public void configureCommands()
  {
    CommandScheduler.getInstance().setDefaultCommand(Robot.DRIVETRAIN, new DiffDrive());

    //Button ALIGN_BUTTON = new JoystickButton(this.Joystick, Robot.ROBOTMAP.getAlignButton());
    //ALIGN_BUTTON.whenPressed(new Align());
      
    // Button SHIFT_BUTTON = new JoystickButton(this.Joystick, Robot.ROBOTMAP.getShiftButton());
    // SHIFT_BUTTON.whenPressed(new ShiftGear());
   
  }
}