package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class StorageStopUp extends CommandBase {

  public StorageStopUp() {
    addRequirements(Robot.INTAKETEST);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.INTAKETEST.stopDown();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.INTAKETEST.stopClimb();
    }
  }
}