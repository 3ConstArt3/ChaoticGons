ChaoticGon chaoticGon;

void setup()
{
  surface.setTitle("ChaoticGons");
  surface.setResizable(false);
  surface.setLocation(displayWidth / 3, floor(0.1 * displayHeight));

  createChaoticGon();
  background(0);

  size(720, 720, P2D);
  colorMode(HSB, 360, 255, 255);
}

void createChaoticGon()
{
  var numberOfSides = 5;
  var radius = 321f;

  chaoticGon = new ChaoticGon(radius, numberOfSides);
}

void draw()
{
  chaoticGon.render();
}
