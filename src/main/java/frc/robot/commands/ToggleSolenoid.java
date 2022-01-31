
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class ToggleSolenoid extends CommandBase {

  public ToggleSolenoid(){
    addRequirements(Robot.SOLENOID_TESTING);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.SOLENOID_TESTING.toggleSolenoid();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}