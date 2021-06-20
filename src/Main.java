import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        {
            JFrame frame = new JFrame("Multithreading Tester");
            PrimesPanel t = new PrimesPanel();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 1200);
            frame.add(t);
        }
    }
}
