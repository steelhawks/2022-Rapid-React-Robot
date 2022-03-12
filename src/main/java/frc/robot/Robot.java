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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Storage.*;
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
  
  
  public static final Command follow = new Follow();
  public static final Command SAMPLEAUTOPATH0 = new SampleAutopath0();
  public static final Command SAMPLEAUTOPATH1 = new SampleAutopath1();

  public static final SequentialCommandGroup aGroup = new SequentialCommandGroup(new SampleAutopath0(), new SampleAutopath1());

  private final AnalogInput ultrasonic = new AnalogInput(0);

  public static final Vision VISION = new Vision();

  boolean previous = true;
  DigitalInput beamI = new DigitalInput(1);
  int count1 = 0;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();
    Robot.COMMAND_LINKER.configurePeriodicBindings();
    Robot.COMMAND_LINKER.configureCommands();

    ROBOTMAP.paths.add("testpath.csv");
    ROBOTMAP.paths.add("clockcircle.csv");

    Robot.FOLLOWER.importPath(ROBOTMAP.paths);
    PATH_SELECTOR.presetPaths();
    PATH_SELECTOR.loadPresetPath();
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

    double rawValue = ultrasonic.getValue();
    double voltage_scale_factor = 5/ RobotController.getVoltage5V();
    double currentDistanceCentimeters = rawValue * voltage_scale_factor * 0.125;
    double currentDistanceInches = rawValue * voltage_scale_factor * 0.0492;
    
    VISION.updateNetworkValues();
  

    SmartDashboard.putNumber("dist cm", currentDistanceCentimeters);
    SmartDashboard.putNumber("dist in", currentDistanceInches);
    
    boolean light = beamI.get();
    
    if(light) {
      previous = beamI.get(); 
    } else {
      if(!light == previous){
        count1++; 
        previous = beamI.get(); 
      }
    }
    Shuffleboard.getTab("commands");
    
    SmartDashboard.putBoolean("beam", light); 
    SmartDashboard.putNumber("beam Intake", count1);
    //intake 
    SmartDashboard.putData("intakeretract", new IntakeRetract());
    SmartDashboard.putData("intakeextend", new IntakeExtend());
    SmartDashboard.putData("intakespin", new IntakeSpin());
    SmartDashboard.putData("intakespinreverse", new IntakeSpinReverse());
    //storage
    SmartDashboard.putData("storageDown", new StorageDown());
    SmartDashboard.putData("sushiIn", new StorageIn());
    SmartDashboard.putData("sushiOut", new StorageOut());
    SmartDashboard.putData("storageUp", new StorageUp());
    //climber 
    SmartDashboard.putData("climberrollwinch", new ClimberRollWinch());
    SmartDashboard.putData("climberunrollwinch", new ClimberUnrollWinch());
    SmartDashboard.putData("climbertoggle", new ClimberToggleSolenoid());
  
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
    
    CommandScheduler.getInstance().schedule(aGroup);
    aGroup.execute();
    
    CommandScheduler.getInstance().enable();   
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
  }
  
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    
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
    
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
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
