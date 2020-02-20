ArrayList<Ray> rays = new ArrayList<Ray>();
ArrayList<Wall> walls = new ArrayList<Wall>();

String mode = "ray"; 

float movex = 0;
float movey = 0;
float xoff = 0;
float yoff = 1000;

int wall_total = 5;
int ray_total = 50;



void setup() {
  fullScreen(P2D);
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

void draw() {
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

void mouseClicked() {
  walls.clear();
  walls.add(new Wall(0, 0, width, 0));
  walls.add(new Wall(0, 0, 0, height));
  walls.add(new Wall(width, height, 0, height));
  walls.add(new Wall(width, height, width, 0));
  for (int i=0; i<wall_total; i+=1) {
    walls.add(new Wall(random(width), random(height), random(width), random(height)));
  }
}
