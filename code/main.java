ChaoticGon chaoticGon;

void setup()
{
  surface.setTitle("ChaoticGons");
  surface.setResizable(false);
  surface.setLocation(displayWidth / 3, floor(0.1 * displayHeight));

  createChaoticGon();
  background(0);

  size(720, 720);
  colorMode(HSB, 255, 255, 255);
}

void createChaoticGon()
{
  var phi = (1 + sqrt(5)) / 2;
  var sideNumber = 5;
  var radius = 321;
  var percentage = 0.36 * phi;
  int generationSpeed = 123;

  chaoticGon = new ChaoticGon(radius, sideNumber,
    percentage, generationSpeed);
}

void draw()
{
  chaoticGon.generate();
}
