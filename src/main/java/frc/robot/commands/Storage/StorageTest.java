package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Storage;

public class StorageTest extends CommandBase {

  private final Storage m_intakeTest;
  private final int motor;

  public StorageTest(Storage subsystem, int motor) {
    m_intakeTest = subsystem;
    addRequirements(m_intakeTest);
    this.motor = motor;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.STORAGE.storageTest(this.motor);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
    }
  }
}