package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.Storage.*;
import frc.robot.subsystems.ElevatorLevels;
import frc.util.LEDColor;
import frc.util.LEDMode;
import frc.robot.commands.LedCommand;
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
                // .whileHeld(new StorageUp());
                .whenPressed(new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new ElevatorCommand(ElevatorLevels.LOW), 
                                new LedCommand(LEDColor.WHITE, LEDMode.STATIC)), 
                        new ParallelRaceGroup(new LedCommand(LEDColor.WHITE, LEDMode.PULSE), 
                        new WaitCommand(0.7)), 
                        new LedCommand(LEDColor.OFF, LEDMode.STATIC)));

        this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsReverseButton)
                // .whileHeld(new StorageDown());
                .whenPressed(new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new ElevatorCommand(ElevatorLevels.MID), 
                                new LedCommand(LEDColor.BLUE, LEDMode.STATIC)), 
                        new ParallelRaceGroup(new LedCommand(LEDColor.BLUE, LEDMode.PULSE), 
                        new WaitCommand(0.7)), 
                        new LedCommand(LEDColor.OFF, LEDMode.STATIC)));

        this.controller.mapButton(Robot.BUTTON_MAP.storageFastOutButton)
                // .whileHeld(new StorageUpFast());
                .whenPressed(new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new ElevatorCommand(ElevatorLevels.HIGH), 
                                new LedCommand(LEDColor.RED, LEDMode.STATIC)), 
                        new ParallelRaceGroup(new LedCommand(LEDColor.RED, LEDMode.PULSE), 
                        new WaitCommand(0.7)), 
                        new LedCommand(LEDColor.OFF, LEDMode.STATIC)));
        // Intake to Storage
        this.controller.mapButton(Robot.BUTTON_MAP.storageInButton)
                .whenHeld(new StorageIn());
        this.controller.mapButton(Robot.BUTTON_MAP.storageOutButton)
                .whenHeld(new StorageOut());

        // Intake
        this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinButton)
                .whenHeld(new IntakeSmart())
                .whenPressed(new LedCommand(null, LEDMode.RAINBOW))
                .whenReleased(new LedCommand(LEDColor.OFF, null));
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
                .whenHeld(new ClimberRollWinch())
                .whenPressed(new LedCommand(LEDColor.RED, LEDMode.STATIC))
                .whenReleased(new LedCommand(LEDColor.OFF, null));
        this.controller.mapButton(Robot.BUTTON_MAP.climberWinchUpButton)
                .whenHeld(new ClimberUnrollWinch())
                .whenPressed(new LedCommand(LEDColor.GREEN, LEDMode.STATIC))
                .whenReleased(new LedCommand(LEDColor.OFF, null));
        

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
