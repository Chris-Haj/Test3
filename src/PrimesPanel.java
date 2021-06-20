import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimesPanel extends JPanel {

    private JButton Calculate, Clear;
    private JTextField Numbertxt, Colstxt;
    private JLabel numlbl, colslbl;
    private int Cols, boxes, boxheight, buttonsBG, BoxX, BoxY, xCheck, drawCols, drawBox;
    private String warning="",ColsString="",BoxesString="";


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
                    if(Integer.parseInt(Numbertxt.getText())<1) {
                        warning = "Must input a positive number!";
                        repaint();
                    }
                    else {
                        boxes = Integer.parseInt(Numbertxt.getText());
                        BoxesString="Number of boxes to draw: "+Numbertxt.getText();
                        warning="";
                        repaint();
                    }
                    Numbertxt.setText("");

                } catch (NumberFormatException a) {
                    Numbertxt.setText("");
                    warning="Must input a positive number!";
                    repaint();
                }
            } else if (e.getSource() == Colstxt) {
                try {
                    if(Integer.parseInt(Colstxt.getText())<1) {
                        warning = "Must input a positive number!";
                        repaint();
                    }
                    else {
                        Cols = Integer.parseInt(Colstxt.getText());
                        warning = "";
                        ColsString="Number of columns to draw: "+Colstxt.getText();
                        repaint();
                    }
                    Colstxt.setText("");

                } catch (NumberFormatException a) {
                    Colstxt.setText("");
                    warning="Must input a positive number!";
                    repaint();
                }
            } else if (e.getSource() == Calculate) {
                drawBox=boxes;
                drawCols=Cols;
                BoxesString="Boxes drawn: "+boxes;
                ColsString="Columns drawn: "+Cols;
                repaint();
            } else if (e.getSource() == Clear) {
                Cols = 0;
                boxes = 0;
                drawCols=0;
                drawBox=0;
                BoxesString="";
                ColsString="";
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
        buttonsBG = getHeight() / 16;
        xCheck = 1;




        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), buttonsBG);
        g.setColor(Color.black);
        g.drawString("Please press enter after passing a number!",(getWidth()/5)*2,getHeight()/23);
        g.drawString(warning,(getWidth()/5)*2+50,getHeight()/18);
        g.drawString(BoxesString,getWidth()/40,getHeight()/33);
        g.drawString(ColsString,getWidth()/40,getHeight()/23);

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