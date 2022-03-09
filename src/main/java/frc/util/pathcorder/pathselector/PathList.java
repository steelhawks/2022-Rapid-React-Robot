package frc.util.pathcorder.pathselector;
import java.util.ArrayList; 
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathList {
    public ArrayList <DashboardPath> existingPaths; 

    DashboardPath path1;
    DashboardPath path2;
    DashboardPath path3;
    DashboardPath path4;
    DashboardPath path5;
    DashboardPath path6;

    public PathList(){
        this.existingPaths = new ArrayList<DashboardPath>(0); 
    }

    public void setDefaults(){
        // imported csv paths
        String[] csv1 = {};
        String[] csv2 = {};
        String[] csv3 = {};
        String[] csv4 = {};
        String[] csv5 = {};
        String[] csv6 = {};
 
        path1 = new DashboardPath("testpath", new SequentialCommandGroup(), csv1);
        
        path2 = new DashboardPath("finaltestpath", new SequentialCommandGroup(), csv2);
        path3 = new DashboardPath("Red 3", new SequentialCommandGroup(), csv3);
        path4 = new DashboardPath("Blue 1", new SequentialCommandGroup(), csv4);
        path5 = new DashboardPath("Blue 2", new SequentialCommandGroup(), csv5);
        path6 = new DashboardPath("Blue 3", new SequentialCommandGroup(), csv6);

        // add them to the list of dashboard paths to be loaded
        this.existingPaths.add(path1);
        this.existingPaths.add(path2);
        this.existingPaths.add(path3);
        this.existingPaths.add(path4);
        this.existingPaths.add(path5);
        this.existingPaths.add(path6);
    }
    

}



