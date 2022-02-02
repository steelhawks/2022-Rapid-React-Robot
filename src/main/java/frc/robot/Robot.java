/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DiffDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.StorageTest;
import frc.robot.subsystems.Music;
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
  public double count = -0.99;

  public static final Music MUSIC = new Music();
  
  public static final StorageTest INTAKETEST = new StorageTest();
  public static final SolenoidTesting SOLENOID_TESTING = new SolenoidTesting();

  //color sensor testing
  public static final ColorSensor COLOR_SENSOR = new ColorSensor();

  //ultrasonic
  final AnalogInput ultrasonic = new AnalogInput(0);
  

  @Override
  public void robotInit() 
  {
    //tab.add("currentDistanceCentimeters", currentDistanceCentimeters).getEntry();
    //tab.add("currentDistanceInches", currentDistanceInches).getEntry();

    COMMAND_LINKER.configureRegisteredSubsystems();  
    COMMAND_LINKER.configureCommands();

    MUSIC.addIntra();
    MUSIC.addMusic();
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
    MUSIC.playMusic();

  }
  public void teleopPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {}


}
