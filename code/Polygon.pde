class Polygon
{

  private int numberOfSides;
  private float radius;

  private ArrayList<PVector> vertices;

  /* Constructor definition */
  public Polygon(int numberOfSides, float radius)
  {
    this.numberOfSides = numberOfSides;
    this.radius = radius;
    this.constructPolygon();
  }

  /* Function definition */
  private void constructPolygon()
  {
    this.vertices = new ArrayList<PVector>();
    var deltaAngle = TAU / this.numberOfSides;

    for (float angle = 0; angle < TAU; angle += deltaAngle)
    {
      var posX = this.radius * cos(angle);
      var posY = this.radius * sin(angle);
      this.vertices.add(new PVector(posX, posY));
    }
  }

  public PVector getVertice(int position)
  {
    return this.vertices.get(position);
  }

  public int getSides()
  {
    return this.numberOfSides;
  }

  public void render()
  {
    if (this.vertices != null)
    {
      pushMatrix();
      translate(width / 2, height / 2);

      for (var vertice : this.vertices)
      {
        noFill();
        strokeWeight(6);
        stroke(300, 255, 255);
        point(vertice.x, vertice.y);
      }

      popMatrix();
    }
  }
}
