import java.util.*;
public class Obstacle{
    double x,y,w,h;
    double movB,movT;
    boolean moveX;
    int uOD;
    double speed;
    public Obstacle(double xp,double yp,double wid,double hei){
        x=xp;y=yp;w=wid;h=hei;
    }

    public void setMovement(double bot,double top, double spd, boolean xOrY,int upOrDown){
        movB=bot;
        movT=top;
        speed=spd;
        moveX=xOrY;//true=x
        uOD=upOrDown;
        if(uOD>=0){uOD=1;}else{uOD=-1;}
    }
    
    public void paint()
    {
        StdDraw.setPenRadius(0.15);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(x,y,w,h);
    }
    
    public void move(){
        if(moveX){
            x+=uOD*speed;
            if(x<=movB){
                uOD=1;
            }else if(x>=movT){
                uOD=-1;
            }
        }else{
            y+=uOD*speed;
            if(y<=movB){
                uOD=1;
            }else if(y>=movT){
                uOD=-1;
            }
        }
    }
}