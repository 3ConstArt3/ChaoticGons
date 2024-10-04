class Utility
{

  /* Constructor definition */
  public Utility()
  {
  }

  /* Function definition */
  public float getDistance(PVector pointA, PVector pointB)
  {
    var distXSquared = pow(pointA.x - pointB.x, 2);
    var distYSquared = pow(pointA.y - pointB.y, 2);
    return sqrt(distXSquared + distYSquared);
  }

  public PVector lerpedPoint(PVector pointA, PVector pointB, float percentage)
  {
    var lerpPosX = lerp(pointA.x, pointB.x, percentage);
    var lerpPosY = lerp(pointA.y, pointB.y, percentage);
    return new PVector(lerpPosX, lerpPosY);
  }

  public boolean differBy(int[] indices, int[] params)
  {
    var currentIndex = indices[0];
    var previousIndex = indices[1];
    var distance = params[0];
    var limit = params[1];

    var neighborA = (currentIndex + distance + limit) % limit;
    var neighborB = (currentIndex - distance + limit) % limit;

    return (previousIndex == neighborA || previousIndex == neighborB);
  }
}
