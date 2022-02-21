package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeExtend extends CommandBase {
    public IntakeExtend() {
        addRequirements(Robot.INTAKE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.INTAKE.intakeExtendSolenoid();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}