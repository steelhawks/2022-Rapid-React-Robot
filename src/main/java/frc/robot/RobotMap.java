package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RobotMap 
{

  public RobotMap() {}
  
  /*Store instance variables, values, ports needed for the robot*/


  public final String pathName = "deploy";
  public final String deployDirectory = "C:/Code/2020AutonBot/deploy";
  public SequentialCommandGroup autonCommands = new SequentialCommandGroup();
  public ArrayList<String> paths = new ArrayList<String>();

  //Joystick Input Ports

  final int joystickPortOne = 0;

  //DRIVETRAIN Motor ID
  public final int drivetrainLeftMotorPortOne = 1;
  public final int drivetrainLeftMotorPortTwo = 2;
  public final int drivetrainLeftMotorPortThree = 3;
  
  public final int drivetrainRightMotorPortOne = 4;
  public final int drivetrainRightMotorPortTwo = 5;
  public final int drivetrainRightMotorPortThree = 6;

  public final int drivetrainSolenoidPortOn = 0; //make sure it is right
  public final int drivetrainSolenoidPortOff = 1;
 
  //Storage % Shooter Motor ID
  public final int storageMotorPortOneUp = 10; //shooter
  public final int storageMotorPortTwoUp = 12; //shooter
  public final int storageMotorPortIn = 11; //sushi rollers
  public final double storageSpeedUp = 0.75; //shooting speed
  public final double storageSpeedIn = 1; //sushi roller speed

  //Gyro
  public final double KP_GYRO = 0.008;

  //Intake Motor & solenoids
  public final int intakeMotorOnePort = 7;
  public final int intakeSoleForward = 2;
  public final int intakeSoleReverse = 3;
  public final double intakeSpeed = 0.75;

  //Climber ??
    public final int climberLeftPort = 12;
    public final int climberRightPort = 14;
    public final int climberSoleLeftForward = 4;
    public final int climberSoleLeftReverse = 5;
    public final int climberSoleRightForward = 6;
    public final int climberSoleRightReverse = 7;
    public final double climberSpeed = 0.5;

    public final int LimelightMotorPort = 7;
    public final int beamBreakerPortOne = 1;
    public final int beamBreakerPortTwo = 4; 
    

  /*****
   Constructor method
   *****/

  //  public int getLeftMotorPortOne()
  //  {
  //    return this.drivetrainLeftMotorPortOne; 
  //  }
  //  public int getLeftMotorPortTwo()
  //  {
  //    return this.drivetrainLeftMotorPortTwo; 
  //  }
  //  public int getLeftMotorPortThree()
  //  {
  //    return this.drivetrainLeftMotorPortThree; 
  //  }
  //  public int getRigtMotorPortOne()
  //  {
  //    return this.drivetrainRightMotorPortOne; 
  //  }
  //  public int getRightMotorPortTwo()
  //  {
  //    return this.drivetrainLeftMotorPortTwo;  
  //  }
  //  public int getRightMotorPortThree()
  //  {
  //    return this.drivetrainRightMotorPortThree; 
  //  }
  //  public int getJoystickPortOne()
  //  {
  //    return this.JoystickPortOne; 
  //  }
  //  public double getKPGyro()
  // {
  //   return this.KP_GYRO;
  // }
  // public double getIntakeSpeed() {
  //   return this.intakeSpeed;
  // }

  // public int getWinchPort() {
  //   return this.WINCH_PORT;
  // }

  // public double getClimberSpeed() {
  //   return this.climberSpeed;
  // }
}
