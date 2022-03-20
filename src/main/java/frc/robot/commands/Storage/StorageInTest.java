package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class StorageInTest extends CommandBase {

  public StorageInTest() {
    addRequirements(Robot.STORAGE);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
   
    if(Robot.STORAGE.beamI.get()) {
        Robot.STORAGE.storageIn(true);
    }
    else if(Robot.ballCount == 0){
        Robot.STORAGE.storageRun(true);
    }
    
    
  }

  @Override
  public boolean isFinished() {
    return Robot.ballCount == 0 ? Robot.STORAGE.beamI.get() : true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.storageMotorStop();
    }
  }
}