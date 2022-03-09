package frc.robot.commands.Autonomous;

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
      Robot.FOLLOWER.follow(2601); //SAMPLE TEMPLATE FOR A AUTON DRIVETRAIN COMMAND
      // input the index number of the path from String array in ROBOTMAP.paths
      // this runs that csv file and makes it into a AutonPath Object and runs it
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