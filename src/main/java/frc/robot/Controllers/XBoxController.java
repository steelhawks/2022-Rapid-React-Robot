package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;
import frc.robot.commands.Storage.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;

public class XBoxController {

    private final Controller controller;

    public XBoxController(Joystick joystickPort) {
        this.controller = new Controller(joystickPort);
    }

    public void mapButtons() {

        // Storage
        this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsUpButton)
                .whileHeld(new StorageUp());

        this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsReverseButton)
                .whileHeld(new StorageDown());

        this.controller.mapButton(Robot.BUTTON_MAP.storageFastOutButton)
                .whileHeld(new StorageUpFast());

        // Intake to Storage
        this.controller.mapButton(Robot.BUTTON_MAP.storageInButton)
                .whenHeld(new StorageIn());
        this.controller.mapButton(Robot.BUTTON_MAP.storageOutButton)
                .whenHeld(new StorageOut());

        // Intake
        this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinButton)
                .whenHeld(new IntakeSpin());
        this.controller.mapButton(Robot.BUTTON_MAP.intakeReverseSpinButton)
                .whenHeld(new IntakeSpinReverse());
        this.controller.mapButton(Robot.BUTTON_MAP.intakeToggleSolenoidButton)
                .whenPressed(new IntakeToggleSolenoid());

        // this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsUpButton)
        // .whenHeld(new StorageUp(Robot.STORAGE));

        // this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsReverseButton)
        // .whenHeld(new StorageDown(Robot.STORAGE));

        // Climber
        this.controller.mapButton(Robot.BUTTON_MAP.climberWinchDownButton)
                .whenHeld(new ClimberRollWinch());
        this.controller.mapButton(Robot.BUTTON_MAP.climberWinchUpButton)
                .whenHeld(new ClimberUnrollWinch());
        this.controller.mapButton(Robot.BUTTON_MAP.climberToggleSolenoidButton)
                .whenPressed(new ClimberToggleSolenoid());
        this.controller.mapButton(Robot.BUTTON_MAP.climbRobotButton)
                .whenHeld(new ClimbRobot());


        this.controller.mapButton(Robot.BUTTON_MAP.climberWinchDownButton)
                .whenHeld(new ClimberRollWinch());
        this.controller.mapButton(Robot.BUTTON_MAP.climberWinchUpButton)
                .whenHeld(new ClimberUnrollWinch());
        

        // //each independant
        // this.controller.mapButton(Robot.BUTTON_MAP.climberWinchDownButton)
        //         .whileHeld(new LeftClimberUp());
        // this.controller.mapButton(Robot.BUTTON_MAP.climberWinchUpButton)
        //         .whenHeld(new RightClimberUp());
        
        // this.controller.mapButton(Robot.BUTTON_MAP.leftClimberDown)
        //         .whenHeld(new LeftClimberDown());
        // this.controller.mapButton(Robot.BUTTON_MAP.rightClimberDown)
        //         .whenHeld(new RightClimberDown());               

        

        // this.controller.mapButton(Robot.BUTTON_MAP.climberPivotButton)
        // .whenHeld(new ClimberPivot());
        // this.controller.mapButton(Robot.BUTTON_MAP.climberPivotReverseButton)
        // .whenHeld(new ClimberPivotReverse());
    }
}
