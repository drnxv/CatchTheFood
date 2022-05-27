import java.awt.*;
import java.util.Random;

public class Fruit {
  private int size;
  private Color color;
  private Random rand;
  private int x, y;

  public Fruit(int size) {
    this.size = size;
    this.color = Color.red;
    rand = new Random();
    this.x = rand.nextInt(460) + this.size;
    this.y = rand.nextInt(460) + this.size;
  }

  public Rectangle getBounds() {
    return new Rectangle(this.x, this.y, this.size, this.size);
  }

  public void newLocation() {
    this.x = rand.nextInt(460) + this.size;
    this.y = rand.nextInt(460) + this.size;
  }

  public int getSize() {
    return this.size;
  }

  public void setColor(Color c) {
    this.color = c;
  }

  public Color getColor() {
    return this.color;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
}
