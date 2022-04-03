package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TerminalForward extends CommandBase 
{

    
  public TerminalForward() {
      addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() 
  {
    Robot.FOLLOWER.follow(3);
  }

  @Override
  public boolean isFinished() 
  {
    return Robot.FOLLOWER.isFinished;
  }

  //runs the command until the follow method finishes and returns true

  @Override
  public void end(boolean interrupted) {}
}