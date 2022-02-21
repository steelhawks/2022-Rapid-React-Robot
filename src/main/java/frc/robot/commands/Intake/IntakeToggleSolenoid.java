package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeToggleSolenoid extends CommandBase {
    public IntakeToggleSolenoid() {
        addRequirements(Robot.INTAKE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.INTAKE.intakeToggleSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
    }
}