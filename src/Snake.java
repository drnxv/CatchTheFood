import java.awt.*;
import java.util.Random;

public class Snake {
  private int size;
  private Color color;
  private int speed;
  private Random rand;
  private int x, y;
  private String direction;

  public Snake(int size) {
    this.size = size;
    this.color = Color.green;
    this.speed = 5;
    rand = new Random();
    direction = "right";
    this.x = rand.nextInt(440) + this.size;
    this.y = rand.nextInt(440) + this.size;
  }

  public void changeDirection(String s) {
    switch (s) {
      case "up" -> {
        this.direction = "up";
        this.y -= this.speed;
      }
      case "down" -> {
        this.direction = "down";
        this.y += this.speed;
      }
      case "right" -> {
        this.direction = "right";
        this.x += this.speed;
      }
      case "left" -> {
        this.direction = "left";
        this.x -= this.speed;
      }
    }
  }

  public void newLocation() {
    this.x = rand.nextInt(440) + this.size;
    this.y = rand.nextInt(440) + this.size;
  }

  public Rectangle getBounds() {
    return new Rectangle(this.x, this.y, this.size, this.size);
  }

  public int getSize() {
    return this.size;
  }

  public int getSpeed() {
    return this.speed;
  }

  public Color getColor() {
    return this.color;
  }

  public int getY() {
    return this.y;
  }

  public int getX() {
    return this.x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void setDirection(String s) {
    this.direction = s;
  }

  public String getDirection() {
    return this.direction;
  }
}
