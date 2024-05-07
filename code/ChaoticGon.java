class ChaoticGon
{

  private Utility utility;

  private Polygon polygon;
  private int currentIndex;
  private int previousIndex;
  private PVector currentPoint;

  private int generationSpeed;

  /* Constructor definition */
  public ChaoticGon(float radius, int numberOfSides)
  {
    this.utility = new Utility();

    this.polygon = new Polygon(numberOfSides, radius);
    this.currentIndex = 0;
    this.previousIndex = 0;
    var posX = random(width);
    var posY = random(height);
    this.currentPoint = new PVector(posX, posY);

    this.generationSpeed = 321;
  }

  /* Function definition */
  public void render()
  {
    this.polygon.render();

    for (int s = 0; s < this.generationSpeed; s++)
    {
      this.currentIndex = (int)random(this.polygon.getSides());
      this.pickRandomVertice();
      this.previousIndex = this.currentIndex;
      var randomVertice = this.polygon.getVertice(this.currentIndex);

      var percentage = 1f/2;
      this.currentPoint = this.utility.lerpedPoint(this.currentPoint,
        randomVertice, percentage);

      this.renderPoint();
    }
  }

  private void pickRandomVertice()
  {
    while (this.currentIndex == this.previousIndex)
      this.currentIndex = (int)random(this.polygon.getSides());
  }

  private void renderPoint()
  {
    pushMatrix();
    translate(width / 2, height / 2);

    noFill();
    var pointA = new PVector(0, 0);
    var pointB = this.currentPoint;
    var distance = this.utility.getDistance(pointA, pointB);
    var hue = map(sin(distance), -1, 1, 0, 360);
    strokeWeight(1);
    stroke(hue, 255, 255, 180);

    point(this.currentPoint.x, this.currentPoint.y);
    popMatrix();
  }
}
