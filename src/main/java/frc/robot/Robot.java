/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.StorageTest;
//import frc.robot.subsystems.Music;
import frc.robot.subsystems.SolenoidTesting;
import frc.util.ColorSensor;
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot 
{
  /*****
   * Robot Objects
   *****/
  public static final RobotMap ROBOTMAP = new RobotMap();
  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker();
  public WPI_TalonSRX light = new WPI_TalonSRX(ROBOTMAP.getLightPort());
  public double count = -0.99;

  //public static final Music MUSIC = new Music();
  
  public static final StorageTest INTAKETEST = new StorageTest();
  public static final SolenoidTesting SOLENOID_TESTING = new SolenoidTesting();

  //color sensor testing
  public static final ColorSensor COLOR_SENSOR = new ColorSensor();

  //ultrasonic
  final AnalogInput ultrasonic = new AnalogInput(0);
  

  @Override
  public void robotInit() 
  {
       
    //COMMAND_LINKER.configureCommands();
    light.configContinuousCurrentLimit(1);
    light.enableCurrentLimit(true);

    //CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
    //CommandScheduler.getInstance().registerSubsystem(Robot.ULTRA);
    //CommandScheduler.getInstance().registerSubsystem(Robot.VISION);

    //Button SHIFT_BUTTON = new JoystickButton(Robot.COMMAND_LINKER.DRIVE_JOYSTICK, Robot.ROBOTMAP.getShiftButton());
    //Button ALIGN_BUTTON = new JoystickButton(Robot.COMMAND_LINKER.DRIVE_JOYSTICK, Robot.ROBOTMAP.getAlignButton());

    //SHIFT_BUTTON.whenPressed(new ShiftGear());
    //ALIGN_BUTTON.whenPressed(new Align());

    //colorsensor match with fixed colors
    COLOR_SENSOR.matchfixedcolors();

    //tab.add("currentDistanceCentimeters", currentDistanceCentimeters).getEntry();
    //tab.add("currentDistanceInches", currentDistanceInches).getEntry();

    Robot.COMMAND_LINKER.configureCommands();
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();  

    // MUSIC.addIntra();
    // MUSIC.addMusic();

  }

  @Override
  public void robotPeriodic() 
  {
    
    /*
    double raw_value = ultrasonic.getValue();
    //voltage_scale_factor allows us to compensate for differences in supply voltage.
    double voltage_scale_factor = 5/RobotController.getVoltage5V();
    double currentDistanceCentimeters = raw_value * voltage_scale_factor * 0.125;
    double currentDistanceInches = raw_value * voltage_scale_factor * 0.0492;
    System.out.println(currentDistanceCentimeters + "cm");
    System.out.println(currentDistanceInches + "in");
    */

    //colorsensor
    COLOR_SENSOR.detectColor();
    COLOR_SENSOR.updatecolortable();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() 
  {
    CommandScheduler.getInstance().enable();
    //MUSIC.playMusic();

  }
  public void teleopPeriodic() 
  {
    CommandScheduler.getInstance().run();
    light.set(1.0);

  }

  @Override
  public void testPeriodic() {}


}
