import javax.swing.*;
import java.awt.*;

public class End_Panel extends JPanel
{
    boolean win;

    JButton try_again = new JButton("Try Again");
    JButton play_again = new JButton("Play Again");

    JFrame frame;
    static boolean again = false;
    JPanel game;

    public End_Panel(boolean w,JFrame f,JPanel p)
    {
        win = w;
        frame = f;
        game = p;

        if(win)
        {
            add(play_again);
            play_again.addActionListener(new ButtonListener(new JLabel()));
            repaint();
        }
        else
        {
            add(try_again);
            try_again.addActionListener(new ButtonListener(new JLabel()));
            repaint();
        }
    }

    public void paintComponent(Graphics g)
    {
        g.setFont(new Font("Times New Roman",Font.BOLD,40));

        Color bg = new Color(179, 177, 177, 98);

        if(win)
        {
            buttonSet(play_again,235,300,bg,Color.BLACK);
            g.fillRect(0, 0, 1000, 1000);
            g.setColor(Color.white);
            g.drawString("YOU WON!", 200, 200);
            g.setFont(new Font("Times New Roman",Font.BOLD,20));
            g.drawString("Time remaining: " + Detyra.digitFixer(Detyra.count/60 + "") + ":" + Detyra.digitFixer(Detyra.count%60 + ""),210,260);
        }
        else
        {
            buttonSet(try_again,235,250,bg,Color.BLACK);
            g.fillRect(0, 0, 1000, 1000);
            g.setColor(Color.white);
            g.drawString("YOU LOST!", 200, 200);
        }

        if(again)
        {
            Detyra.reset_boolean = true;
            frame.getContentPane().add(game);
            Detyra.timer_label.setText("Time remaining: 04:00");
            frame.getContentPane().remove(this);
            frame.repaint();
        }

        repaint();
    }

    public void buttonSet(JButton b,int x,int y, Color c,Color c2)
    {
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setBounds(x,y,140,30);
        b.setBackground(c);
        b.setForeground(c2);
        b.setFont(new Font("Arial",Font.PLAIN,17));
    }
}