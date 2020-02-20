class Wall {
  PVector start;
  PVector end;
  Wall(float x1, float y1, float x2, float y2) {
    start = new PVector(x1, y1);
    end = new PVector(x2, y2);
  }

  void show() {
    stroke(200, 10, 100);
    strokeWeight(5);
    strokeCap(ROUND);
    line(start.x, start.y, end.x, end.y);
  }

}
