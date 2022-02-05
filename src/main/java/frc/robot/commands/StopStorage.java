package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Storage;

public class StopStorage extends CommandBase {

  private final Storage m_intakeTest;

  public StopStorage(Storage subsystem) {
    m_intakeTest = subsystem;
    addRequirements(m_intakeTest);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.stopStorage();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.stopClimb();
    }
  }
}
