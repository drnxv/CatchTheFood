import javax.swing.*;

public class GameFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Catch the fruit");
    frame.getContentPane().add(new GamePanel(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
