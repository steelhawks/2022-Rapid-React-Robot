package frc.robot.commands.Vision;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class RunAllOfStorage extends CommandBase {
    
  public RunAllOfStorage() {}

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
    // run EVERYTHING for INTAKING THE BALL
    Robot.INTAKE.spinRoller(false);
    Robot.STORAGE.storageIn(false); //sushi IN
    Robot.STORAGE.storageRunSlow(true); //storage UP

  }
    
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
