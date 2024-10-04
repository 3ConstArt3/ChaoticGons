class ChaoticGon
{

  private Utility utility;

  private Polygon polygon;
  private int previousIndex;
  private PVector currentPoint;

  private int generationSpeed;

  /* Constructor definition */
  public ChaoticGon(float radius, int numberOfSides)
  {
    this.utility = new Utility();

    this.polygon = new Polygon(numberOfSides, radius);
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
      var currentIndex = (int)random(this.polygon.getSides());
      currentIndex = this.pickRandomVertice(currentIndex);
      this.previousIndex = currentIndex;
      var randomVertice = this.polygon.getVertice(currentIndex);

      /* 
       * Ideal percentages, for some beautiful
       * results - some depend on the number of
       * vertices of the polygon - are given below:
       *
       * --> 1f/2
       * --> 2f/3
       * --> 3f/5
       */
      var percentage = 3f/5;
      this.currentPoint = this.utility.lerpedPoint(this.currentPoint,
        randomVertice, percentage);

      this.renderPoint();
    }
  }

  private int pickRandomVertice(int currentIndex)
  {
    /*
     * ******************* Rule *******************
     * 
     * According to this rule of the chaos
     * game, the currently chosen vertex, should
     * have a distance(d) to the previously chosen
     * one, different than n (e) P(n), where P(n)
     * is the number of sides of the polygon.
     *
     * --> numberOfSides = [3-8]
     */
    var indices = new int[2];
    indices[0] = currentIndex;
    indices[1] = this.previousIndex;

    var params = new int[2];
    /* 
     * d <= floor(this.polygon.getSides() / 2).
     * That happens due to the modular logic 
     * that is used to calculate the distance
     * between the vertices of the polygon.
     * After that bound, the process repeats
     * itself from the starting point(0).
     */
    params[0] = 1;
    params[1] = this.polygon.getSides();

    while (this.utility.differBy(indices, params))
    {
      currentIndex = (int)random(this.polygon.getSides());
      indices[0] = currentIndex;
    }

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
