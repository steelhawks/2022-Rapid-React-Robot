// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsBase;
// import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Storage.*;
import frc.robot.commands.Vision.*;
import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.*;
import frc.util.pathcorder.Follower;
import frc.util.pathcorder.Recorder;
import frc.util.pathcorder.pathselector.PathSelector;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // robot objects

  public static final RobotMap ROBOTMAP = new RobotMap();
  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final Intake INTAKE = new Intake();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker();
  public static final ButtonMap BUTTON_MAP = new ButtonMap();
  public static final Storage STORAGE = new Storage();
  public static final Climber CLIMBER = new Climber();
  public static final Vision VISION = new Vision();

  public static final Recorder RECORDER = new Recorder();
  public static final Follower FOLLOWER = new Follower();
  public static final PathSelector PATH_SELECTOR = new PathSelector();

  public static final Command follow = new Follow();
  public static final Command SAMPLEAUTOPATH0 = new SampleAutopath0();
  public static final Command SAMPLEAUTOPATH1 = new SampleAutopath1();

  Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  public static final SequentialCommandGroup routine1 = new SequentialCommandGroup(
      new ParallelRaceGroup(
        new AutoShoot(), new WaitCommand(2)),
      
      // new IntakeRetract(), 
      //new ParallelRaceGroup(
      //  new SampleAuthopath0, new IntakeSpinReverse(),
      //),    
      //also can do:
      // new IntakeSpinReverse(), 
      new SampleAutopath0());
      //intake the ball

  public static final SequentialCommandGroup routine2 = new SequentialCommandGroup(
      new ParallelRaceGroup(
          new AutoShoot(), new WaitCommand(2)),
      new IntakeDownAuton(),
      new SampleAutopath0(), // same thing as (shoot) and (go towards general area of ball)

      //if proceeding be sure the sampleautpath0 does not drive over the ball. Need some sufficient room in front to see ball.
      new ParallelRaceGroup(
          new GoToBall(), new RunAllOfStorage(), new WaitCommand(2)), // then Go to ball for hopefully 2 seconds, spinning intake for all of it.
      
      //if we have beam breakers:
      // new ParallelRaceGroup(
      //     new GoToBall(), new AutonPickUpBall() // autopickupball will continue to run intake+storage until the gotoball no longer sees a ball infront.
      // ),
      new StopAllOfStorage()); //safety check again to stop all storage after ball is in robot. 
      // new IntakeRetract(), //when driving back to hub, fold up intake to prevent injury.
      // new SampleAutopath1(), // drive back to general hub location.
      // new AlignToHub()); //turn by rotating first to center hub, then optionally fix up the straight line distance.

  public static int ballCount = 0;

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command m_autonomousCommand;

  Ultrasonic ultrasonic = new Ultrasonic(2, 3);

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();
    Robot.COMMAND_LINKER.configurePeriodicBindings();
    Robot.COMMAND_LINKER.configureCommands();

    ROBOTMAP.paths.add("driveoutfromhub.csv"); // 0
    ROBOTMAP.paths.add("back.csv"); // 1

    Robot.FOLLOWER.importPath(ROBOTMAP.paths);
    PATH_SELECTOR.presetPaths();
    PATH_SELECTOR.loadPresetPath();

    m_chooser.setDefaultOption("Simple Auto", routine1);
    m_chooser.addOption("Complex Auto", routine2);
    SmartDashboard.putData("choose auto", m_chooser);

    CommandScheduler.getInstance().enable();

    // compressor.disable();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    DRIVETRAIN.shuffleBoard();
    CLIMBER.shuffleBoard();

    // SmartDashboard.putBoolean("beam intact", beam.get());
    SmartDashboard.putNumber("ultrainch", ultrasonic.getRangeInches());
    // SmartDashboard.putData("intakeextend", new IntakeExtend());

    VISION.updateNetworkValues();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser code works with
   * the Java SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser make sure to add
   * them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

    // CommandScheduler.getInstance().schedule(routine1);
    // routine1.execute();

    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); // **These are necessary to set the LL to look down w/ correct ball color
                                // pipeline.
    STORAGE.storageMotorStop();

    DRIVETRAIN.highGear(); 

    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();

    // How to call autonPickUpBall()
    // VISION.autonPickUpBall();

    // DRIVETRAIN.rotateToHub();

    // if (DRIVETRAIN.goToBall()) {
    // if(DRIVETRAIN.rotateToHub()){
    // DRIVETRAIN.straightHub();
    // }
    // }

    // DRIVETRAIN.goToBall();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

    DRIVETRAIN.highGear();

    // routine1.cancel();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // VISION.faceLimelightUp();
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); // **These are necessary to set the LL to look down w/ correct ball color
                                // pipeline.
    INTAKE.stopRoll();
    STORAGE.storageMotorStop();
    INTAKE.stopRoll();

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    CommandScheduler.getInstance().run();

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();

  }

  // Shuffleboard.getTab("commands");

  // SmartDashboard.putBoolean("beam", light);
  // SmartDashboard.putNumber("beam Intake", count1);
  // intake
  // // ShuffleboardTab
  // SmartDashboard.putData("intakeretract", new IntakeRetract());
  // SmartDashboard.putData("intakeextend", new IntakeExtend());
  // SmartDashboard.putData("intakespin", new IntakeSpin());
  // SmartDashboard.putData("intakespinreverse", new IntakeSpinReverse());
  // //storage
  // SmartDashboard.putData("storageDown", new StorageDown());
  // SmartDashboard.putData("sushiIn", new StorageIn());
  // SmartDashboard.putData("sushiOut", new StorageOut());
  // SmartDashboard.putData("storageUp", new StorageUp());
  // //climber
  // SmartDashboard.putData("climberrollwinch", new ClimberRollWinch());
  // SmartDashboard.putData("climberunrollwinch", new ClimberUnrollWinch());
  // SmartDashboard.putData("climbertoggle", new ClimberToggleSolenoid());
}
