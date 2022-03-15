package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class StorageIn extends CommandBase {

  public StorageIn() {
    addRequirements(Robot.STORAGE);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.storageIn(true);
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