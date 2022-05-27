import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// very simple game where you must catch the fruit which spawns in random
// locations before the timer runs out - feel free to make changes and send a
// PR if need be
public class GamePanel extends JPanel {
  private final int w;
  private final int h;
  private Fruit fruit;
  private Snake snake;
  private Timer timer;
  private JLabel timerLabel;
  private int time;
  private int score = 0;
  private JButton start, playAgain;
  private JLabel tutorial;
  private boolean hasStarted = false;

  public GamePanel(int w, int h) {
    // panel dimensions
    this.w = w;
    this.h = h;

    // both drawings of the game
    this.fruit = new Fruit(20);
    this.snake = new Snake(30);

    // how many seconds left to catch the fruit
    this.time = 5;
    this.timer = new Timer(1000, new StartGame());

    // label for the practice session
    this.tutorial = new JLabel("Use WASD to move, catch the fruit before " +
        "the time expires!");
    this.tutorial.setLayout(null);
    this.tutorial.setForeground(Color.white);
    this.tutorial.setBounds(250, 250, 100, 100);

    // label for the timer
    this.timerLabel = new JLabel("Time Left: " + String.valueOf(time));
    this.timerLabel.setForeground(Color.white);

    // start button
    this.start = new JButton("START");
    this.start.setFocusable(false);
    this.start.addActionListener(new StartGame());

    // play again button
    this.playAgain = new JButton("PLAY AGAIN");
    this.playAgain.setFocusable(false);
    this.playAgain.setEnabled(false);
    this.playAgain.addActionListener(new PlayAgain());

    // setting the dimensions of the panel window
    this.setPreferredSize(new Dimension(w, h));
    this.setBackground(Color.black);
    this.addKeyListener(new Movement());
    this.setFocusable(true);

    // adding all elements to the frame
    this.add(timerLabel);
    this.add(start);
    this.add(playAgain);
    this.add(tutorial);
  }

  // second constructor with only dimension data
  public GamePanel() {
    this.w = 500;
    this.h = 500;
  }

  // main paint method which draws the components onto the panel
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(fruit.getColor());
    g.fillOval(fruit.getX(), fruit.getY(), fruit.getSize(), fruit.getSize());
    g.setColor(snake.getColor());
    g.fillRect(snake.getX(), snake.getY(), snake.getSize(), snake.getSize());
  }

  // checks to see if the snake and fruit have collided with each other
  public void checkCollision() {
    if (snake.getBounds().intersects(fruit.getBounds())) {
      score++;
      resetTime();
      fruit.newLocation();
    }
    repaint();
  }

  public void countdown() {
    time--;
    timerLabel.setText("Time Left: " + time);
  }

  public void resetTime() {
    this.time = 5;
    timerLabel.setText("Time Left: " + time);
  }

  private class Movement implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_W) {
        snake.changeDirection("up");
        if (snake.getY() <= 0) {
          snake.setY(0);
        }
      } else if (e.getKeyCode() == KeyEvent.VK_S) {
        snake.changeDirection("down");
        if (snake.getY() >= (getH() - snake.getSize())) {
          snake.setY(getH() - snake.getSize());
        }
      } else if (e.getKeyCode() == KeyEvent.VK_D) {
        snake.changeDirection("right");
        if (snake.getX() + snake.getSize() >= getW()) {
          snake.setX(getW() - snake.getSize());
        }
      } else if (e.getKeyCode() == KeyEvent.VK_A) {
        snake.changeDirection("left");
        if (snake.getX() <= 0) {
          snake.setX(0);
        }
      }

      // if the game has started -> not in practice mode
      if (hasStarted) {
        checkCollision();
      }
      repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
  }

  // start button private class
  private class StartGame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      hasStarted = true;
      timer.start();
      countdown();
      start.setEnabled(false);
      tutorial.setText("");
      if (time == 0) {
        JOptionPane.showMessageDialog(null,
            "Sorry you lose! You scored " + score +
                " points");
        timer.stop();
        start.setEnabled(false);
        playAgain.setEnabled(true);
      }
    }
  }

  private class PlayAgain implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      time = 5;
      timerLabel.setText("Time Left: " + time);
      score = 0;
      timer.start();
      start.setEnabled(false);
      playAgain.setEnabled(false);
    }
  }

  public int getW() {
    return this.w;
  }

  public int getH() {
    return this.h;
  }
}
