// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsBase;
// import edu.wpi.first.wpilibj.PneumaticsControlModule;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Storage.*;
import frc.robot.commands.Vision.GoToBall;
import frc.robot.commands.Vision.VisionAdjustBall;
import frc.robot.Controllers.Controller;
import frc.robot.Controllers.XBoxController;
import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.*;
import frc.util.Limelight;
import frc.util.pathcorder.Follower;
import frc.util.pathcorder.Recorder;
import frc.util.pathcorder.pathselector.PathSelector;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.RobotController;


import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //robot objects
  
  public static final RobotMap ROBOTMAP = new RobotMap();
  public static final Drivetrain DRIVETRAIN = new Drivetrain(); 
  public static final Intake INTAKE = new Intake();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker(); 
  public static final ButtonMap BUTTON_MAP = new ButtonMap(); 
  public static final Storage STORAGE = new Storage(); 
  public static final Climber CLIMBER = new Climber();
  
  public static final Recorder RECORDER = new Recorder();
  public static final Follower FOLLOWER = new Follower();
  public static final PathSelector PATH_SELECTOR = new PathSelector();
  public static final XBoxController X_BOX_CONTROLLER = new XBoxController(Robot.COMMAND_LINKER.operatorGamepad); 
  
  
  public static final Command follow = new Follow();
  public static final Command SAMPLEAUTOPATH0 = new SampleAutopath0();
  public static final Command SAMPLEAUTOPATH1 = new SampleAutopath1();

  Compressor compressor1 = new Compressor(8, PneumaticsModuleType.CTREPCM);
  Compressor compressor2 = new Compressor(9, PneumaticsModuleType.CTREPCM);

  public static final SequentialCommandGroup autopath = new SequentialCommandGroup(
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    ), 
    new ParallelCommandGroup(
      new SampleAutopath0(), 
      new VisionAdjustBall(),
      new SequentialCommandGroup(
      new WaitCommand(1), new IntakeSpinReverse()
    )),
    
    new SequentialCommandGroup(
      new SampleAutopath1(),
      new ParallelRaceGroup(
        new AutoShoot(), new WaitCommand(2)
      )
    )//, 
      // new SampleAutopath0Reverse(),
      // new ParallelRaceGroup(
      //   new AutoShoot(), new WaitCommand(2) 
      // )
    );
    
  public static final SequentialCommandGroup autopath2 = new SequentialCommandGroup(
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)),
    new ParallelCommandGroup(
      new SampleAutopath0(), new IntakeBeam()),
    // new StorageBeam(),
    // new GoToBall(),
    // new WaitCommand(1),
    // new GoToBall() //erase when using path.
    new SampleAutopath1(),

    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2))
  );

  // THIS IS 2 Ball
  public static final SequentialCommandGroup autopath3= new SequentialCommandGroup(

    //Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    ), 

    // Intake ball
    new ParallelRaceGroup(
      new SampleAutopath2(), 
      new SequentialCommandGroup(
        new WaitCommand(1), new IntakeSpinReverse())
    ),

    // Return to hub
    new SampleAutopath3(),

    // Shoot ball
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    )
    );


    
    // 3 Ball
    public static final SequentialCommandGroup autopath4 = new SequentialCommandGroup(

    // Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    ), 

    // Intake ball
    
    new ParallelRaceGroup(
      new SampleAutopath2(), 
      new SequentialCommandGroup(
        new WaitCommand(1), new IntakeSpinReverse()
      )
    ),

    // Intake other ball
    new ParallelRaceGroup(
      new SampleAutopath4(),
      new SequentialCommandGroup(
        new WaitCommand(1), new IntakeSpinReverse()
      )
    ),

    // Return to hub
    new SampleAutopath5(),

    // Shoot both balls
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    )
    );

    // 2 Ball Hangar
  public static final SequentialCommandGroup autopath5 = new SequentialCommandGroup(

    //Shoot pre loaded
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    ), 

    // // Intake ball
    // new ParallelRaceGroup(
    //   new SampleAutopath0(), // 8 before
    //   new VisionAdjustBall(),
    //   new SequentialCommandGroup(
    //     new WaitCommand(1), new IntakeSpinReverse())
    // ),

    // Bring down intake
    
    // 
    new ParallelRaceGroup(
      new SampleAutopath0(),
      new IntakeSpinReverse()
    ),

    // Align to the ball
    new ParallelRaceGroup(
      new VisionAdjustBall(),
      new IntakeSpinReverse(),
      new WaitCommand(2)
    ),

    // Return to hub
    new SampleAutopath1(), // 9 before

    // Shoot ball
    new ParallelRaceGroup(
      new AutoShoot(), new WaitCommand(2)
    )
    );


  public static int ballCount = 0;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command m_autonomousCommand;



  // private final AnalogInput ultrasonic = new AnalogInput(0);

  public static final Vision VISION = new Vision();
  // public DigitalInput beamI = new DigitalInput(Robot.ROBOTMAP.beambreakerPort2);
  // public DigitalInput beamS = new DigitalInput(Robot.ROBOTMAP.beambreakerPort); 
  // boolean previous = true;
  // boolean previous2 = true; 
  // int count1 = 0;

  // Ultrasonic ultrasonic = new Ultrasonic(2, 3); 
 
  


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();
    Robot.COMMAND_LINKER.configurePeriodicBindings();
    Robot.COMMAND_LINKER.configureCommands();

    // ROBOTMAP.paths.add("ba.csv");
    Robot.DRIVETRAIN.GYRO.reset();
    Robot.DRIVETRAIN.GYRO.calibrate();

    //bestAutonBack.csv is bad 
    ROBOTMAP.paths.add("newRecord.csv"); // 8
    ROBOTMAP.paths.add("Auton2BallReverse.csv"); // 9
    ROBOTMAP.paths.add("bestAuton.csv"); // 0
    //ROBOTMAP.paths.add("sami2.csv"); // 1
    ROBOTMAP.paths.add("AngledAuton.csv"); // 2
    ROBOTMAP.paths.add("AngledAutonBack.csv"); // 3
    ROBOTMAP.paths.add("AngledAuton2.csv"); // 4
    ROBOTMAP.paths.add("AngledAutonBack2.csv"); // 5
    ROBOTMAP.paths.add("driveoutfromhub.csv"); // 6
    ROBOTMAP.paths.add("3BallAuton1.csv");//7
    // ROBOTMAP.paths.add("Auton2Ball.csv"); // 8
    // ROBOTMAP.paths.add("Auton2BallReverse.csv"); // 9

    Robot.FOLLOWER.importPath(ROBOTMAP.paths);
    PATH_SELECTOR.presetPaths();
    PATH_SELECTOR.loadPresetPath();

    m_chooser.setDefaultOption("Simple Auto", autopath);
    m_chooser.addOption("Complex Auto", autopath2);
    m_chooser.addOption("Angled Auto 2 Ball", autopath3);
    m_chooser.addOption("Angled Auto 3 Ball", autopath4);
    m_chooser.addOption("Hangar 2 Ball", autopath5);
    SmartDashboard.putData("choose auto", m_chooser);
    
    compressor1.disable();
    compressor2.disable();
  }
  
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    DRIVETRAIN.shuffleBoard();
    STORAGE.shuffleBoard();

    // SmartDashboard.putBoolean("beam intact", beam.get());
    // SmartDashboard.putNumber("ultrainch", ultrasonic.getRangeInches()); 

    // double rawValue = ultrasonic.getValue();
    
    // double voltage_scale_factor = 5/ RobotController.getVoltage5V();
    // double currentDistanceCentimeters = rawValue * voltage_scale_factor * 0.125;
    // double currentDistanceInches = rawValue * voltage_scale_factor * 0.0492;
    
    VISION.updateNetworkValues();
    Limelight.updateValues();
    //     if(previous != beamI.get() && beamI.get()){
    //         Robot.ballCount++;
    //         Robot.STORAGE.storageIn(true);
    
    //     }
    //       else{
    //         previous = beamI.get();
    //       }
       
    // if(previous2 != beamS.get() && beamS.get()){
    //           Robot.STORAGE.storageRun(5);; 
    //       }
    //         previous2 = beamS.get();
    //     }

    // SmartDashboard.putNumber("dist cm", currentDistanceCentimeters);
    // SmartDashboard.putNumber("dist in", currentDistanceInches);
    
    // boolean light = beamI.get();
    
    // if(light) {
    //   previous = beamI.get(); 
    // } else {
    //   if(!light == previous){
    //     count1++; 
    //     previous = beamI.get(); 
    //   }
    // }
    // Shuffleboard.getTab("commands");
    
    // SmartDashboard.putBoolean("beam", light); 
    // SmartDashboard.putNumber("beam Intake", count1);
    //intake 
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
  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
    // CommandScheduler.getInstance().schedule(autopath);
    // autopath.execute();
    DRIVETRAIN.stop();

    CommandScheduler.getInstance().enable();   
    Robot.VISION.ballCount = 0;
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
    STORAGE.storageMotorStop();
    
    // DRIVETRAIN.goToBall();
    // DRIVETRAIN.goToBall();

    // for(int i = 1; i<5; i++) {
    //   if(i%4 == 0) {
    //     DRIVETRAIN.goToBall();
    //   }

    // }

    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    
  }
  
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();


    //How to call autonPickUpBall()
    //VISION.autonPickUpBall();
    
    // DRIVETRAIN.rotateToHub();

    // if (DRIVETRAIN.goToBall()) {
    //   if(DRIVETRAIN.rotateToHub()){
    //     DRIVETRAIN.straightHub();
    //   }
    // }

    // DRIVETRAIN.goToBall();
  }

  
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().enable();
    autopath.cancel();
    autopath2.cancel();
    autopath3.cancel();
    autopath4.cancel();
    autopath5.cancel();
    
    DRIVETRAIN.stop();
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
    INTAKE.stopRoll();
    STORAGE.storageMotorStop();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
