package frc.robot.commands.Drivetrain;


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;


public class ShiftGears extends CommandBase 
{
  public ShiftGears() {
      addRequirements(Robot.DRIVETRAIN);
  }

  
  @Override
  public void initialize() {

  }

  @Override
  public void execute() 
  {
    //Sets the 3D Port Joystick as the arcadeDrive joystick inputs
    Robot.DRIVETRAIN.shiftGear();
  }

  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
}