package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class StorageTest extends CommandBase {

  private int motor;
  public StorageTest(int motorNum) {
    addRequirements(Robot.STORAGE);
    motor = motorNum;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.storageTest(motor);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
      System.out.println("motor stopped");
    }
  }
}