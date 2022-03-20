package frc.robot.commands.Storage; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StopAllStorage extends CommandBase {

  public StopAllStorage() {
    addRequirements(Robot.STORAGE);
    addRequirements(Robot.INTAKE);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.INTAKE.stopRoll();
    Robot.STORAGE.storageMotorStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
      Robot.INTAKE.stopRoll();
    }
  }
}
