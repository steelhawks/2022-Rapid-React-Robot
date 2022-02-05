package frc.robot.commands.Drivetrain;


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;


public class DiffDrive extends CommandBase 
{
  public DiffDrive() {
      addRequirements(Robot.DRIVETRAIN);
  }

  
  @Override
  public void initialize() {

  }

  @Override
  public void execute() 
  {
    //Sets the 3D Port Joystick as the arcadeDrive joystick inputs
    Robot.DRIVETRAIN.arcadeDrive(Robot.COMMAND_LINKER.Joystick);
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
}