
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class ExtendSolenoid extends CommandBase {

  public ExtendSolenoid(){
    addRequirements(Robot.SOLENOID_TESTING);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.SOLENOID_TESTING.extendSolenoid();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}