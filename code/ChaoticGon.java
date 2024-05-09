class ChaoticGon
{

  private Utility utility;

  private Polygon polygon;
  private int previousIndex;
  private int intersections;
  private PVector currentPoint;

  private int generationSpeed;

  /* Constructor definition */
  public ChaoticGon(float radius, int numberOfSides)
  {
    this.utility = new Utility();

    this.polygon = new Polygon(numberOfSides, radius);
    this.previousIndex = 0;
    this.intersections = 0;
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
      var currentIndex = (int)random(this.polygon.getSides());
      currentIndex = this.pickRandomVertice(currentIndex);
      this.previousIndex = currentIndex;
      var randomVertice = this.polygon.getVertice(currentIndex);

      var percentage = 1f/2;
      this.currentPoint = this.utility.lerpedPoint(this.currentPoint,
        randomVertice, percentage);

      this.renderPoint();
    }
  }

  private int pickRandomVertice(int currentIndex)
  {
    /*
     * According to this rule of the chaos
     * game, the currently chosen vertex, can't
     * be the same as the previously chosen
     * vertex.
     *
     * --> numberOfSides = 5
     */
    //while (currentIndex == this.previousIndex)
    //  currentIndex = (int)random(this.polygon.getSides());

    /*
     * According to this rule of the chaos
     * game, the currently chosen vertex, can't
     * be 2 places away from the previously
     * chosen vertex.
     *
     * --> numberOfSides = 4
     */
    var indices = new int[2];
    indices[0] = currentIndex;
    indices[1] = this.previousIndex;

    var params = new int[2];
    params[0] = 1;
    params[1] = this.polygon.getSides();
    while (this.utility.differBy(indices, params))
    {
      currentIndex = (int)random(this.polygon.getSides());
      indices[0] = currentIndex;
    }

    /*
     * According to this rule of the chaos
     * game, the currently chosen vertex, can't
     */

    //if (this.previousIndex == currentIndex)
    //  this.intersections++;

    //if (this.intersections == 2)
    //{
    //  while (abs(currentIndex - this.previousIndex) != 1)
    //    currentIndex = (int)random(this.polygon.getSides());

    //  this.intersections = 0;
    //}

    return currentIndex;
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
