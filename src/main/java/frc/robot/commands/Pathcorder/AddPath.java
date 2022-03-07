package frc.robot.commands.Pathcorder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class AddPath extends CommandBase {
  public AddPath() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
    
  }
  @Override
  public void execute() {}

  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) 
  {

  }
}