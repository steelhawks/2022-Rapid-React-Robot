/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.subsystems.pathcorder;

import edu.wpi.first.wpilibj.Filesystem;
//import edu.wpi.first.wpilibj.Joystick;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

//import java.sql.Time;

import java.util.ArrayList;

import frc.robot.Robot;
import frc.util.subsystems.pathcorder.AutonPath;

public class Follower {

  public int index = 0;
  public boolean shouldDouble = false;
  public boolean isFinished = false;
  private ArrayList<AutonPath> AutonPaths = new ArrayList<AutonPath>(0);
  private int pathIndex = 0;

  public void follow() {
    AutonPath currentPath = AutonPaths.get(pathIndex);

    // FOLLOW JOYSTICK RECORDING
    if (index < currentPath.joystickYValues.size()) {
      Robot.DRIVETRAIN.DIFF_DRIVE.arcadeDrive(currentPath.joystickYValues.get(index),
          currentPath.joystickRotationValues.get(index));

      // FOLLLOW BUTTON RECORDING
      int currentButtonInput = (int)Math.round(currentPath.joystickButtonInputs.get(index));
      readButtonInput(currentButtonInput);
      
      // DOUBLING FILES TO ENSURE ACCURACY
      index++;
      if (shouldDouble == true) 
      {
        shouldDouble = false;
        index++;
      } 
      else {
        shouldDouble = true;
      }

      isFinished = false;
    } 

    else 
    {
      Robot.DRIVETRAIN.DIFF_DRIVE.arcadeDrive(0, 0);
      System.out.println(index);
      pathIndex++;
      isFinished = true;
    }
  }

  public void importPath(ArrayList<String> paths) {
    try {
      this.AutonPaths.clear();
      // insert path to csv
      for (String pathName : paths) {
        System.out.println("PATH NAME" + pathName);
        Path path = Paths.get(pathName);
        Path fileName = path.getFileName();
        File TBR = fileName.toFile();
        String file = fileName.toString();
        System.out.println("this is name: " + file);
        bufferedReader(TBR);
      }

      // throw new IOException();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to load " + Robot.ROBOTMAP.paths + "...");
    }
  }

  public void bufferedReader(File fileName) {
    try {

      String filePathName = Filesystem.getDeployDirectory().toString() + "/" + fileName;
      System.out.println(filePathName);
      File exFile = new File(filePathName);
      System.out.println(exFile);
      FileReader in = new FileReader(exFile);
      System.out.println("made filereader");
      // System.out.println("File reader" + in.ready());
      BufferedReader br = new BufferedReader(in);
      System.out.println("made bufferedreader");
      // System.out.println("buffered reader" + br.ready());
      br.readLine();
      br.readLine();
      AutonPath newPath = new AutonPath();
      String line = null;

      while ((line = br.readLine()) != null) {
        String[] joystickValue = line.split(",");
        double joystickY = Double.parseDouble(joystickValue[0]);
        double joystickRotation = Double.parseDouble(joystickValue[1]);
        double joystickButtonInput = Double.parseDouble(joystickValue[3]);
        // if (valueOne > 1 || valueOne < -1 || valueTwo > 1 || valueTwo < -1) {
        //   newPath.encoderRightValues.add(valueOne);
        //   newPath.encoderLeftValues.add(valueTwo);
        // } 
        // else {
          // newPath.joystickYValues.add(valueOne);
          // newPath.joystickRotationValues.add(valueTwo);
        // }
        newPath.joystickYValues.add(joystickY);
        newPath.joystickRotationValues.add(joystickRotation);
        newPath.joystickButtonInputs.add(joystickButtonInput);

      }
      AutonPaths.add(newPath);
      br.close();
      // // br.read();
      // // br.ready();
    } catch (Exception e) {
      System.out.println("Could not find file");
    }
  }


  private void readButtonInput(int buttonInput){
    if(buttonInput == -1){
      // do nothing
    }
    else if(buttonInput == Robot.BUTTON_MAP.intakeToggleSolenoidButton){
      Robot.INTAKE.intakeToggleSolenoid();
    }
    else if(buttonInput == Robot.BUTTON_MAP.intakeSpinButton){
      Robot.INTAKE.spinRoller(Robot.ROBOTMAP.intakeSpeed);
    }
    else if(buttonInput == -Robot.BUTTON_MAP.intakeSpinButton){
      Robot.INTAKE.stop();
    }
    else if(buttonInput == Robot.BUTTON_MAP.storageMoveBallsUpButton){
      Robot.STORAGE.storageRun(true);
    }
    else if(buttonInput == -Robot.BUTTON_MAP.storageMoveBallsUpButton){
      Robot.STORAGE.stop();
    }
    else if(buttonInput == Robot.BUTTON_MAP.storageInButton){
      Robot.STORAGE.storageIn(true);
    }
    else if(buttonInput == Robot.BUTTON_MAP.storageOutButton){
      Robot.STORAGE.storageIn(false);
    }
    else if(buttonInput == -Robot.BUTTON_MAP.storageOutButton) {
      Robot.STORAGE.stop();
    }

  }

  // String csvFile = " ";
  // BufferedReader br = null;
  // String line = "";
  // String cvsSplitBy = ",";

  // public void get
  // {
  // if(joystickValues.equals("joystick"))
  // {
  // System.out.println("")
  // }
}
