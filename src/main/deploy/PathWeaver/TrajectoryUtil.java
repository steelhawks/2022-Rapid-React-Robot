package edu.wpi.first.wpilibj.trajectory; 

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator; 
import java.lang.Object; 
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil; 
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig; 
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.geometry.Translation2d;



public class TrajectoryUtil {

//This object can be used to represent a point or a vector.
// Translation2d path = new Translation2d(6.716, 4.273);

//
// SwerveDriveKinematics anything = new SwerveDriveKinematics(path);

//json file 
String trajectoryJSON = "paths/Path1.wpilib.json";

//Trajectory 
Trajectory trajectory = new Trajectory();

// TrajectoryConfig config = new TrajectoryConfig(5,3);
// config.setKinematics(anything);
// DifferentialDriveVoltageConstraint voltConstraint = new DifferentialDriveVoltageConstraint(/* Your SimpleMotorFeedForward Object */, /* Your kinematics object */, maximumVoltage);
// // For the SimpleMotorFeedForward Object, construct a new object using the average of the kVolts, the average of the kV, and the average of the kA values.
// config.addConstraint(voltConstraint);
// config.setInverted(inverted);

// List<Pose2d> waypoints = new List<Pose2d>();
// /* Add your waypoints here, starting with the beginning of the path */
// Trajectory generator = TrajectoryGenerator.generateTrajectory​(waypoints, config);


@Override
public void robotInit() {
   try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
   } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
   }
}

}

