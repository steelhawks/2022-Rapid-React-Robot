package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeBeam extends CommandBase {
    public IntakeBeam() {
        addRequirements(Robot.STORAGE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        System.out.println("ad");
        Robot.INTAKE.spinRoller(false);
        Robot.STORAGE.storageIn(false);  
        Robot.STORAGE.storageRun(true);
    }

    @Override
    public boolean isFinished() {
        return !Robot.STORAGE.beamI.get();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.INTAKE.stopRoll();
        Robot.DRIVETRAIN.stop();
        Robot.STORAGE.storageMotorStop();
    }
}