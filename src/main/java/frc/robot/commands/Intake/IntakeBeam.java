package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.util.pathcorder.Follower;

public class IntakeBeam extends CommandBase {
    public IntakeBeam() {
        addRequirements(Robot.STORAGE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.INTAKE.spinRoller(0.8);
        Robot.STORAGE.storageIn(true);  
    }

    @Override
    public boolean isFinished() {
        return !Robot.STORAGE.beamI.get();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.INTAKE.stopRoll();
        Robot.STORAGE.storageMotorStop();
        Robot.FOLLOWER.index = 0;
    }
}