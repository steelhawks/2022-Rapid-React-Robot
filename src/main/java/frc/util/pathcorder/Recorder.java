/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.pathcorder;

import java.nio.file.Path;
import java.nio.file.Paths;

import frc.util.CsvLogger;
import frc.robot.Robot;

public class Recorder {
  public double buttonPressed;
  public boolean isRecording;
  public boolean usingRecording;
  public int currentButton;
  public int index;
  public String[] name = { "joystick value y", "joystick twist", "right encoder", "left encoder" };
  public String[] value = { "", "", "rpm", "rpm" };
  public String csvList = "";
  CsvLogger logger = new CsvLogger();

  public Recorder() {
    this.buttonPressed = -1;
    this.isRecording = false;
    this.usingRecording = false;
    this.index = 0;
    this.currentButton = 0;

  }

  public void startLog() {
    logger.init(name, value);

  }

  public void resetButton(){
    this.buttonPressed = -1;
  }
  public void recordJoystick(JoystickRecorder stick) {
    logger.writeData(stick.joystickY, stick.joystickTwist, stick.count, this.buttonPressed);
    this.resetButton();
  }
  
//   public void recordEncoderValues(Encorder enc) {
//     logger.writeData(enc.rightEncoder, enc.leftEncoder);
//   }

  public void exportPath(String pathName) {
    // SmartDashboard.putString("CSV String", csvList);
    try {
      Path path = Paths.get(pathName);
      Path fileName = path.getFileName();
      System.out.println("FileName: " + fileName.toString());
      for (int i = 0; i < 25; i++) {
        logger.writeData(0.0, 0.0, 0);
      }
      logger.forceSync();
      logger.close();

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to load " + Robot.ROBOTMAP.paths + "...");
    }
  }

}
