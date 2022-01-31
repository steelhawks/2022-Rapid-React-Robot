
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Music extends SubsystemBase
{
  //SPARK MAX LEFT MOTORS
  public final WPI_TalonFX talonFX;

  public Orchestra orchestra = new Orchestra();

  //DRIVETRAIN CONSTRUCTOR
  public Music(){
    this.talonFX = new WPI_TalonFX(Robot.ROBOTMAP.MUSIC_MOTOR_PORT);
    talonFX.configFactoryDefault();
    talonFX.setNeutralMode(NeutralMode.Coast);
  }

  public void addIntra() {
    orchestra.addInstrument(talonFX);
  }

  public void addMusic(){
    orchestra.loadMusic("mario");
  }

  public void playMusic(){
    orchestra.play();
  }


}