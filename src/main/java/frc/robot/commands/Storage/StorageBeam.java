package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class StorageBeam extends CommandBase {
    
    public StorageBeam() {
        addRequirements(Robot.STORAGE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.STORAGE.storageRunSlow(true);
    }

    @Override
    public boolean isFinished() {
       return !Robot.STORAGE.beamS.get();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.STORAGE.storageMotorStop();
    }
}