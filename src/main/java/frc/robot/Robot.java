// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsBase;
// import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Intake.IntakeBeam;
import frc.robot.commands.Storage.*;
import frc.robot.commands.Vision.*;
import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.*;
import frc.util.pathcorder.Follower;
import frc.util.pathcorder.Recorder;
import frc.util.pathcorder.pathselector.PathSelector;

// import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.RobotController;
// import edu.wpi.first.networktables.NetworkTableEntry;

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
  public static final Command SAMPLEAUTOPATH0 = new TaxiPath();
  public static final Command SAMPLEAUTOPATH1 = new HangerForward();

  Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  // public static final SequentialCommandGroup autopath1 = new SequentialCommandGroup(
  //     new ParallelRaceGroup(
  //       new AutoShoot(), new WaitCommand(2)), //2
      
  //     // new IntakeRetract(), 
  //     //new ParallelRaceGroup(
  //     //  new SampleAuthopath0, new IntakeSpinReverse(),
  //     //),    
  //     //also can do:
  //     // new IntakeSpinReverse(), 
  //     new SampleAutopath0()); // 2 to 3 seconds

  //     //intake the ball

  //   public static final SequentialCommandGroup autopath2 = new SequentialCommandGroup(
  //     new ParallelRaceGroup(
  //       new AutoShoot(), new WaitCommand(2)), // shoot preloaded ball
  //     new IntakeDownAuton(),
  //     new ParallelCommandGroup(
  //       new SampleAutopath0(), new IntakeBeam()), // intake ball in front

  //     new SampleAutopath1(), // return to hub
      
  //     new ParallelRaceGroup(
  //       new AutoShoot(), new WaitCommand(2)), // shoot second ball

  //     new IntakeRetract(),

  //     new SampleAutopath0() // taxi
  //   );
            
  //   public static final SequentialCommandGroup autopath3 = new SequentialCommandGroup(
  //     new ParallelRaceGroup(
  //       new AutoShoot(), new WaitCommand(2)),

  //     new SampleAutonpathLime(), //should be a shorter version of move forward so the ball is still there.

  //     new ParallelCommandGroup(
  //       new IntakeBeam(), new AlignBall() //picks up ball using limelight tracking.
  //     ),

  //     new SampleAutopath1(), //goes back to hub. should have ball.
  //     new ParallelRaceGroup(
  //       new AutoShoot(), new WaitCommand(2))

  //   );

  //   // after finishing path, add align to ball command with wait command in parallel race group

  // public static final SequentialCommandGroup routine3 = new SequentialCommandGroup(
  //   new SampleAutopath0(),
  //   new SampleAutopath1(),
  //   new SampleAutopath2());

  // 1 ball and taxi
  public static final SequentialCommandGroup autopath1 = new SequentialCommandGroup(
    // Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)),

    // Taxi
    new TaxiPath()
  );

  // 2 ball hangar tarmac
  // the robot aligns diagonally with one corner touching the fender and the other one ball away from the fender
  public static final SequentialCommandGroup autopath2 = new SequentialCommandGroup(

    new ParallelRaceGroup(
      new IntakeRetract(), new WaitCommand(0.4)
    ), 

    // new ParallelRaceGroup(
    //   new IntakeExtend(), new WaitCommand(2)
    // ), 
    
    //Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(0.4)
    ), 
      

    // Intake ball
    new ParallelDeadlineGroup(
      new IntakeBeam(),
      new HangerForward()
    ),
  

    // Align to the ball
    // new ParallelRaceGroup(
    //   new VisionAdjustBall(),
    //   new IntakeBeam(),
    //   new WaitCommand(2)
    // ),

    new ParallelRaceGroup(
      new IntakeExtend(), new WaitCommand(0.4)
    ), 
    // new WaitCommand(2),

    // Return to hub
    // new SampleAutopath2(),
    
      new HangerBackward(),
    
    // new ParallelCommandGroup(
    //   new SampleAutopath2(),
    //   new StorageBeam()
    // ),


    // // Shoot ball
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(0.5)
    ),
    new TaxiPath()
  );

  //2 ball terminal tarmac
  public static final SequentialCommandGroup autopath3 = new SequentialCommandGroup(

  
    new ParallelRaceGroup(
      new IntakeRetract(), new WaitCommand(0.4)
    ), 
    
    //Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(0.4)
    ), 

    // Intake ball
    new ParallelDeadlineGroup(
      new IntakeBeam(),
      new TerminalForward()
    ),

    new ParallelRaceGroup(
      new IntakeBeam(),
      new WaitCommand(1)
    ),

    new ParallelRaceGroup(
      new IntakeExtend(), new WaitCommand(0.4)
    ), 
    // new WaitCommand(2),

    // Return to hub
    // new SampleAutopath2(),
    
      new TerminalBackward(),

      new WaitCommand(.5),
    
    // new ParallelCommandGroup(
    //   new SampleAutopath2(),
    //   new StorageBeam()
    // ),


    // // Shoot ball
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(1)
    ),
    new TaxiTerminal()
  );

  public static int ballCount = 0;

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command m_autonomousCommand;

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

    // ROBOTMAP.paths.add("sami.csv"); // 0
    // ROBOTMAP.paths.add("samibutt.csv"); // 1
    // ROBOTMAP.paths.add("3ball_3.csv"); // 2
    // ROBOTMAP.paths.add("samiShort.csv"); // 3

    // 0 - taxi
    // 1- Get ball hangar
    // 2 - Return hub hangar
    // 3 - Get ball 1 terminal
    // 4 - Get ball 2 terminal
    // 5 - Return hub terminal
    ROBOTMAP.paths.add("samiShort.csv");
    ROBOTMAP.paths.add("hangarleft1.csv");
    ROBOTMAP.paths.add("hangarleft2.csv");
    ROBOTMAP.paths.add("terminalforward.csv");
    ROBOTMAP.paths.add("terminalshorttaxi.csv");

    
    
    
    Robot.FOLLOWER.importPath(ROBOTMAP.paths);
    PATH_SELECTOR.presetPaths();
    PATH_SELECTOR.loadPresetPath();
    DRIVETRAIN.resetGyro();
    DRIVETRAIN.resetEncoders();


    // m_chooser.setDefaultOption("One Ball", routine1);
    // m_chooser.addOption("Two Ball", routine2);
    // m_chooser.addOption("Three Ball", routine3);
    // m_chooser.addOption("Two Ball Lime", limeRoutine);

    // SmartDashboard.putData("choose auto", m_chooser);

    m_chooser.setDefaultOption("1 ball taxi", autopath1);
    m_chooser.addOption("2 Ball Hangar", autopath2);
    m_chooser.addOption("2 Ball Terminal", autopath3);


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
    STORAGE.shuffleBoard();

    // SmartDashboard.putBoolean("beam intact", beam.get());
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
