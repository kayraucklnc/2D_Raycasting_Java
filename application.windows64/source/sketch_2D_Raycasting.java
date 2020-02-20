import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_2D_Raycasting extends PApplet {

ArrayList<Ray> rays = new ArrayList<Ray>();
ArrayList<Wall> walls = new ArrayList<Wall>();

String mode = "ray"; 

float movex = 0;
float movey = 0;
float xoff = 0;
float yoff = 1000;

int wall_total = 5;
int ray_total = 50;



public void setup() {
  
  //size(1280, 720);
  background(80);
  for (int i=0; i<360; i+=(int)360/ray_total) {
    rays.add(new Ray(i));
  }
  walls.add(new Wall(0, 0, width, 0));
  walls.add(new Wall(0, 0, 0, height));
  walls.add(new Wall(width, height, 0, height));
  walls.add(new Wall(width, height, width, 0));
  for (int i=0; i<wall_total; i+=1) {
    walls.add(new Wall(random(width), random(height), random(width), random(height)));
  }
}

public void draw() {
  background(50);


  //mouseX = (int)map(noise(xoff),0,1,0,width);
  //mouseY = (int)map(noise(yoff),0,1,0,height);
  //xoff+=0.010;
  //yoff+=0.010;


  for (int k=0; k<walls.size(); k+=1) {
    walls.get(k).show();
  }
  for (int i=0; i<rays.size(); i+=1) {


    rays.get(i).show();

    PVector recP = new PVector(0, 0);
    double rec = Double.POSITIVE_INFINITY;

    for (int k=0; k<walls.size(); k+=1) {
      if (rec>rays.get(i).inter(walls.get(k))) {      
        rec = rays.get(i).inter(walls.get(k));
        recP.x = rays.get(i).itr.x;
        recP.y = rays.get(i).itr.y;
      }
    }
    rays.get(i).display(recP.x, recP.y);
  }
}

public void mouseClicked() {
  walls.clear();
  walls.add(new Wall(0, 0, width, 0));
  walls.add(new Wall(0, 0, 0, height));
  walls.add(new Wall(width, height, 0, height));
  walls.add(new Wall(width, height, width, 0));
  for (int i=0; i<wall_total; i+=1) {
    walls.add(new Wall(random(width), random(height), random(width), random(height)));
  }
}
class Wall {
  PVector start;
  PVector end;
  Wall(float x1, float y1, float x2, float y2) {
    start = new PVector(x1, y1);
    end = new PVector(x2, y2);
  }

  public void show() {
    stroke(200, 10, 100);
    strokeWeight(5);
    strokeCap(ROUND);
    line(start.x, start.y, end.x, end.y);
  }

}
class Ray {
  float angle;
  PVector dir;
  PVector itr = new PVector(0, 0);


  Ray(float angle) {
    this.angle = angle*PI/180;
    this.dir = PVector.fromAngle(this.angle).mult(dist(0, 0, width, height));
  }

  public void show() {
    push();
    stroke(255, 255, 255, 2);
    line( mouseX, mouseY, mouseX +this.dir.x, mouseY+this.dir.y);
    pop();
  }

  public float inter(Wall other) {
    //this.show();

    float x1 = other.start.x;
    float x2 = other.end.x;
    float x3 = mouseX;
    float x4 = mouseX +this.dir.x;
    float y1 = other.start.y;
    float y2 = other.end.y;
    float y3 = mouseY;
    float y4 = mouseY+this.dir.y;

    float div = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);

    float tu_div =(x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);

    if (tu_div !=0) {    
      float t = ((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/tu_div;
      float u = -((x1-x2)*(y1-y3)-(y1-y2)*(x1-x3))/tu_div;

      if (1>t && t>0 && u>0) {      
        this.itr.x = ((x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/div;
        this.itr.y = ((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/div;

        //this.display(this.itr.x, this.itr.y);
      }
    }
    return dist(mouseX, mouseY, this.itr.x, this.itr.y);
  }


  public void display(float x, float y) {
    push();
    stroke(255);
    fill(255);
    strokeWeight(3);
    line(mouseX, mouseY, x, y);
    //ellipse(x, y, 5, 5);
    pop();
  }
}

  public void settings() {  fullScreen(P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_2D_Raycasting" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
