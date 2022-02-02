package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.Storage.StorageRollAll;
import frc.robot.commands.Storage.StorageRollDown;
import frc.robot.commands.Storage.StorageRollUp;

public class CommandLinker 
{
  /*****
   * Joystick Objects
   *****/
  public final Joystick DRIVE_JOYSTICK = new Joystick(Robot.ROBOTMAP.getJoystickPortOne());

  public CommandLinker() 
  {
    //configureCommands();
  }

  public void configureRegisteredSubsystems() {
    CommandScheduler.getInstance().registerSubsystem(Robot.INTAKETEST);
    CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
  }

  public void configureCommands()
  {
    //CommandScheduler.getInstance().setDefaultCommand(Robot.DRIVETRAIN, new DiffDrive());

    //Button ALIGN_BUTTON = new JoystickButton(this.DRIVE_JOYSTICK, Robot.ROBOTMAP.getAlignButton());
    //ALIGN_BUTTON.whenPressed(new Align());
      
    Button SHIFT_BUTTON = new JoystickButton(this.DRIVE_JOYSTICK, Robot.ROBOTMAP.getShiftButton());
    SHIFT_BUTTON.whenPressed(new ShiftGear());

    Button INTAKE_ROLLUP_BUTTON = new JoystickButton(this.DRIVE_JOYSTICK, 3);
    Button INTAKE_ROLLDOWN_BUTTON = new JoystickButton(this.DRIVE_JOYSTICK, 4);
    Button INTAKE_ALL_BUTTON = new JoystickButton(this.DRIVE_JOYSTICK, 5);
    Button TOGGLE_SOLENOID = new JoystickButton(this.DRIVE_JOYSTICK, 6);

    Button Extend_SOLENOID = new JoystickButton(this.DRIVE_JOYSTICK, 11);
    Button Retract_SOLENOID = new JoystickButton(this.DRIVE_JOYSTICK, 12);

    
    INTAKE_ROLLDOWN_BUTTON.whileHeld(new StorageRollDown(Robot.INTAKETEST));//.whenReleased(new IntakeStopDown());
    INTAKE_ROLLUP_BUTTON.whileHeld(new StorageRollUp(Robot.INTAKETEST));//.whenReleased(new IntakeStopUp());
    INTAKE_ALL_BUTTON.whileHeld(new StorageRollAll(Robot.INTAKETEST));

    TOGGLE_SOLENOID.whenPressed(new ToggleSolenoid());
    Extend_SOLENOID.whenPressed(new ExtendSolenoid());
    Retract_SOLENOID.whenPressed(new RetractSolenoid());
    
  }
}
