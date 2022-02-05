package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class ReverseDrivetrain extends CommandBase {

  public ReverseDrivetrain() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    System.out.print("Preparing to reverse motion...");
    Robot.DRIVETRAIN.reverseDirection();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}