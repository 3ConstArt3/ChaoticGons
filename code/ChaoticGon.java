class ChaoticGon
{

  private float radius;
  private int sideNumber;
  private float percentage;

  private ArrayList<PVector> vertices;

  private int currentIndex;
  private int previousIndex;
  private PVector currentPoint;

  private int generationSpeed;

  /* Constructor definition */
  public ChaoticGon(float radius, int sideNumber,
    float percentage, int generationSpeed)
  {
    this.radius = radius;
    this.sideNumber = sideNumber;
    this.percentage = percentage;

    this.createVertices();

    this.currentIndex = 0;
    this.previousIndex = 0;
    this.pickRandomSeedPoint();

    this.generationSpeed = generationSpeed;
  }

  /* Function definition */
  private void createVertices()
  {
    this.vertices = new ArrayList<PVector>();
    var deltaAngle = TWO_PI / this.sideNumber;

    for (float angle = 0; angle < TAU; angle += deltaAngle)
    {
      var posX = radius * cos(angle);
      var posY = radius * sin(angle);
      this.vertices.add(new PVector(posX, posY));
    }
  }

  private void pickRandomSeedPoint()
  {
    var posX = random(width);
    var posY = random(height);
    this.currentPoint = new PVector(posX, posY);
  }

  public void generate()
  {
    for (int s = 0; s < this.generationSpeed; s++)
    {
      this.currentIndex = (int)random(this.vertices.size());
      this.pickRandomVertice();
      this.previousIndex = this.currentIndex;
      var randomVertice = this.vertices.get(this.currentIndex);

      this.currentPoint.x = lerp(this.currentPoint.x, randomVertice.x, this.percentage);
      this.currentPoint.y = lerp(this.currentPoint.y, randomVertice.y, this.percentage);

      this.renderPoint();
    }
  }

  private void pickRandomVertice()
  {
    while (this.currentIndex == this.previousIndex)
      this.currentIndex = (int)random(this.vertices.size());
  }

  private void renderPoint()
  {
    pushMatrix();
    translate(width / 2, height / 2);

    noFill();
    strokeWeight(2);

    var hue = map(sin(this.currentIndex), -1, 1, 0, 255);
    stroke(hue, 255, 255, 180);
    point(this.currentPoint.x, this.currentPoint.y);
    popMatrix();
  }
}
