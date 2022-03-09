package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SampleAutopath0 extends CommandBase 
{

  boolean isFinished = false;
    
  public SampleAutopath0() {
      addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
    this.isFinished = Robot.FOLLOWER.isFinished;
  }

  @Override
  public void execute() 
  {
    Robot.FOLLOWER.follow(0);
  }

  @Override
  public boolean isFinished() 
  {
    return isFinished;
  }

  //runs the command until the follow method finishes and returns true

  @Override
  public void end(boolean interrupted) {}
}