package frc.robot.commands.Storage; 
import frc.robot.subsystems.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StorageDown extends CommandBase {

  private final Storage m_intakeTest;

  public StorageDown(Storage subsystem) {
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
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.stop();
    }
  }
}
