package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SampleAutopath0 extends CommandBase 
{
    
  public SampleAutopath0() {
      addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
    Robot.FOLLOWER.follow(0);
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
}