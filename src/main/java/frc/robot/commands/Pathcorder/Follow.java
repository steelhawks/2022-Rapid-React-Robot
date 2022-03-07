package frc.robot.commands.Pathcorder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class Follow extends CommandBase {
  public Follow() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
      Robot.FOLLOWER.follow();    
  }

  @Override
  public boolean isFinished() 
  {
    System.out.println(Robot.FOLLOWER.isFinished);
    return Robot.FOLLOWER.isFinished;
  }

  @Override
  public void end(boolean interrupted) {}
}