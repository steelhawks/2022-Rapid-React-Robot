package frc.robot.commands.Storage;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class StopAllOfStorage extends CommandBase {
    
  public StopAllOfStorage() {}

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.INTAKE);
    list.add(Robot.STORAGE);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
      // run EVERYTHING for STOPPING ALL INTAKE AND STORAGE OF THE BALL
    Robot.INTAKE.stopRoll();
    Robot.STORAGE.storageMotorStop();
    Robot.INTAKE.stopRoll();
  }
    
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
