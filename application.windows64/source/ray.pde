class Ray {
  float angle;
  PVector dir;
  PVector itr = new PVector(0, 0);


  Ray(float angle) {
    this.angle = angle*PI/180;
    this.dir = PVector.fromAngle(this.angle).mult(dist(0, 0, width, height));
  }

  void show() {
    push();
    stroke(255, 255, 255, 2);
    line( mouseX, mouseY, mouseX +this.dir.x, mouseY+this.dir.y);
    pop();
  }

  float inter(Wall other) {
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


  void display(float x, float y) {
    push();
    stroke(255);
    fill(255);
    strokeWeight(3);
    line(mouseX, mouseY, x, y);
    //ellipse(x, y, 5, 5);
    pop();
  }
}
