// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //robot objects
  
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static final RobotMap ROBOTMAP = new RobotMap();
  public static final Drivetrain DRIVETRAIN = new Drivetrain(); 
  public static final Intake INTAKE = new Intake();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker(); 
  public static final ButtonMap BUTTON_MAP = new ButtonMap(); 
  public static final Storage STORAGE = new Storage(); 
  public static final Climber CLIMBER = new Climber();
  public static final Vision VISION = new Vision();

  public final Compressor COMPRESSOR_ONE = new Compressor(8, PneumaticsModuleType.CTREPCM);
  public final Compressor COMPRESSOR_TWO = new Compressor(9, PneumaticsModuleType.CTREPCM);
  
  public final PowerDistribution PDP = new PowerDistribution();
  Ultrasonic ultrasonic = new Ultrasonic(2, 3); 

  public final DigitalInput beam = new DigitalInput(ROBOTMAP.beambreakerPort);

  public int count = 0;
  public boolean previousIntact = true;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();
    Robot.COMMAND_LINKER.configurePeriodicBindings();
    Robot.COMMAND_LINKER.configureCommands();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    Ultrasonic.setAutomaticMode(true);
  
    // COMPRESSOR_ONE.disable();
    // COMPRESSOR_TWO.disable();

    // PDP.clearStickyFaults();

    DRIVETRAIN.GYRO.calibrate();
    DRIVETRAIN.GYRO.reset();
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
    VISION.updateNetworkValues();
    SmartDashboard.putBoolean("beam intact", beam.get());
    SmartDashboard.putNumber("ultrainch", ultrasonic.getRangeInches()); 
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
    CommandScheduler.getInstance().enable();   
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
    STORAGE.storageUpStop();

    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    
    

    // if(DRIVETRAIN.goToBall()) {
    //   if(DRIVETRAIN.rotateToHub()) {
    //     DRIVETRAIN.straightHub();
    //   }
    // }
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().enable();
    VISION.switchToBallPipeline();
    VISION.faceLimelightDown(); //**These are necessary to set the LL to look down w/ correct ball color pipeline.
    INTAKE.stopRoll();
    STORAGE.storageUpStop();
    STORAGE.storageInStop();
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
