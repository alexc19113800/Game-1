import java.util.*;
public class Level{
    Obstacle[] obst;
    public Level(){
        obst = new Obstacle[0];
    }
    public void setObstacles(Obstacle[] obs){
        obst = obs;
    }
    public void addObstacle(Obstacle obs){
        obst = Arrays.copyOf(obst, obst.length+1);
        obst[obst.length-1] = obs;
    }
    public void addObstacle(Obstacle[] obs){
        obst = Arrays.copyOf(obst, obst.length+obs.length);
        for(int i = obst.length-obs.length; i < obst.length; i++){
            obst[i] = obs[i-obst.length];
        }
    }
}
