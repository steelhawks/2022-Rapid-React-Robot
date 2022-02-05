package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class InitiateDrive extends CommandBase {
  public InitiateDrive() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.DRIVETRAIN.arcadeDrive(Robot.COMMAND_LINKER.driveJoystick);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}