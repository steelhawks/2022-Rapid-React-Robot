package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class AutoShoot extends CommandBase 
{    
  public AutoShoot() {
      addRequirements(Robot.STORAGE);

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() 
    {
        Robot.STORAGE.storageRun(true);
    }

  @Override
  public boolean isFinished() 
  {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.STORAGE.storageMotorStop();
  }
}