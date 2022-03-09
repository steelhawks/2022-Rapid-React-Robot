package frc.util.pathcorder.pathselector;



import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DashboardPath {
  public String pathName;
  public SequentialCommandGroup commands;
  public String[] csvFiles;

  public DashboardPath(String pathName, SequentialCommandGroup commands, String[] csvFiles) {
    this.pathName = pathName;
    this.commands = commands;
    this.csvFiles = csvFiles;
  }
}