package frc.robot.commands.Storage; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StorageDown extends CommandBase {

  public StorageDown() {
    addRequirements(Robot.STORAGE);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.storageRunSlow(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
      System.out.println("motor stopped");

    }
  }
}
