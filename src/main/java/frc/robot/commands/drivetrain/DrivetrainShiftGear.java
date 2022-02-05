package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class DrivetrainShiftGear extends CommandBase {

  public DrivetrainShiftGear() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    System.out.print("Preparing to shift gears...");
    Robot.DRIVETRAIN.shiftGear();
    Robot.DRIVETRAIN.ping();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}