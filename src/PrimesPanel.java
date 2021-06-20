import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimesPanel extends JPanel {

    private JButton Calculate, Clear;
    private JTextField Numbertxt, Colstxt;
    private JLabel numlbl, colslbl;
    private int Cols, boxes, boxheight, buttonsBG, BoxX, BoxY, xCheck, drawCols, drawBox;


    public PrimesPanel() {
        Listenter lis = new Listenter();
        Calculate = new JButton("Calculate");
        numlbl = new JLabel("Number: ");
        colslbl = new JLabel(" Number of columns: ");
        Numbertxt = new JTextField("", 10);
        Colstxt = new JTextField("", 10);
        Clear = new JButton("Clear");

        add(numlbl);
        add(Numbertxt);
        add(colslbl);
        add(Colstxt);
        add(Calculate);
        add(Clear);

        Numbertxt.addActionListener(lis);
        Colstxt.addActionListener(lis);
        Clear.addActionListener(lis);
        Calculate.addActionListener(lis);

    }

    private class Listenter implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException {
            if (e.getSource() == Numbertxt) {
                try {
                    if(Integer.parseInt(Numbertxt.getText())<1)
                        System.out.println("Must input a positive number!");
                    else
                        boxes = Integer.parseInt(Numbertxt.getText());
                    Numbertxt.setText("");

                } catch (NumberFormatException a) {
                    Numbertxt.setText("");
                    System.out.println("Must input a positive number!");
                }
            } else if (e.getSource() == Colstxt) {
                try {
                    if(Integer.parseInt(Colstxt.getText())<1)
                        System.out.println("Must input a positive number!");
                    else
                        Cols = Integer.parseInt(Colstxt.getText());
                    Colstxt.setText("");

                } catch (NumberFormatException a) {
                    Colstxt.setText("");
                    System.out.println("Must input a positive number!");
                }
            } else if (e.getSource() == Calculate) {
                drawBox=boxes;
                drawCols=Cols;
                repaint();
            } else if (e.getSource() == Clear) {
                Cols = 0;
                boxes = 0;
                drawCols=0;
                drawBox=0;
                repaint();

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boxheight = getHeight()/15;
        BoxX = 0;
        BoxY = buttonsBG;
        buttonsBG = getHeight() / 20;
        xCheck = 1;




        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), buttonsBG);

            for (int i = 1; i <= drawBox; i++) {
                if (isPrime(i) == true) {
                    g.setColor(Color.cyan);
                }
                else
                    g.setColor(Color.white);
                g.fillRect(BoxX, BoxY, getWidth() / drawCols, boxheight);
                g.setColor(Color.black);
                g.drawRect(BoxX, BoxY, getWidth() / drawCols, boxheight);
                if (xCheck ==drawCols) {
                    BoxX = 0;
                    xCheck=0;
                    BoxY += boxheight;
                } else
                    BoxX += getWidth() / drawCols;
                xCheck++;
                repaint();

            }
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }


}
