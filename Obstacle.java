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

    public void setMovement(double bot,double top, double spd, boolean xOrY,double upOrDown){
        movB=bot;
        movT=top;
        speed=spd;
        moveX=xOrY;//true=x
        if(upOrDown>=0){uOD=1;}else{uOD=-1;}
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
    public boolean isInside(double xpos, double ypos){
        if(xpos > x-w &&xpos < x+w && ypos > y-h &&ypos < y+h){
            return true;
        }
        return false;
    }
    public boolean passedThrough(double px, double py, double cx, double cy){
        if(px > x-w &&px < x+w && cx > x-w &&cx < x+w){
            if(py<y-h&&cy>y+h||cy<y-h&&py>y+h){
                return true;
            }
        }else if(py > y-h &&py < y+h&&cy > y-h &&cy < y+h){
            if(px < x-w && cx > x+w || cx < x-w && px > x+w){
                return true;
            }
        }
        return false;
    }
    public Obstacle copy(){
        Obstacle temp = new Obstacle(x,y,w,h);
        temp.setMovement(movB,movT,speed,moveX,uOD);
        return temp;
    }
}