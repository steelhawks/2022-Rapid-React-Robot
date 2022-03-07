// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.util.pathcorder;
// import frc.robot.Robot;
// import frc.util.pathcorder.PathList;;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import java.util.ArrayList;
// import java.io.File;

package frc.util.subsystems.pathcorder;
import frc.robot.Robot;
import frc.util.subsystems.pathcorder.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;






public class PathSelector {

  private SendableChooser<DashboardPath> chooser;

  public PathSelector(){
    chooser = new SendableChooser<>();
  }

  public void presetPaths(){
    PathList pathList = new PathList();
    pathList.setDefaults();
    ArrayList<DashboardPath> paths = pathList.existingPaths;
    for(int i = 0; i < paths.size(); i++){
      String optionName = paths.get(i).pathName;
      chooser.addOption(optionName, paths.get(i));
    }
    SmartDashboard.putData("Preset Path", chooser);
  }

  public void loadPresetPath(){
    DashboardPath choice = (DashboardPath)chooser.getSelected();
    Robot.ROBOTMAP.autonCommands = choice.commands;
    ArrayList<String> csvFiles = new ArrayList<>();
    for(String file : choice.csvFiles){
      csvFiles.add(file);
    }
    Robot.ROBOTMAP.paths = csvFiles;
  }
}
