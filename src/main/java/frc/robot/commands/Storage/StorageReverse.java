package frc.robot.commands.Storage; 
import frc.robot.subsystems.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StorageReverse extends CommandBase {

  private final Storage m_intakeTest;

  public StorageReverse(Storage subsystem) {
    m_intakeTest = subsystem;
    addRequirements(m_intakeTest);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.storageRun(false);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
    }
  }
}