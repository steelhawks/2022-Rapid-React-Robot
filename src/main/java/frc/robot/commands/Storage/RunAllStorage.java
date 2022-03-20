package frc.robot.commands.Storage; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class RunAllStorage extends CommandBase {

  public RunAllStorage() {
    addRequirements(Robot.STORAGE);
    addRequirements(Robot.INTAKE);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.INTAKE.spinRoller(false);
    Robot.STORAGE.storageIn(true);
    Robot.STORAGE.storageRun(false);
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
