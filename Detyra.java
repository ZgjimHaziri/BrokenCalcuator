import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Detyra extends JPanel
{
    private JLabel label = new JLabel("0",SwingConstants.RIGHT);
    private JButton button2 = new JButton("2");
    private JButton button3 = new JButton("3");
    private JButton button_plus = new JButton("+");
    private JButton button_x = new JButton("x");
    private JButton button_equal = new JButton("=");
    private JButton button_ac = new JButton("C");
    private JButton reset = new JButton("Reset");
    private JButton start = new JButton("Start");

    static String shenja = "";
    static ArrayList<Integer> numrat= new ArrayList<Integer>();
    static boolean reset_boolean;

    int[] rezultatet;
    int[] result_line = new int[8];

    JFrame frame = new JFrame();

    static Timer stopwatch;
    static int count = 240;
    static int delay = 1000;

    static JLabel timer_label = new JLabel("Time remaining: 04:00",SwingConstants.CENTER);

    public Detyra()
    {
        rezultatet = randomArray(8);
        frame.setSize(600,500);
        frame.setTitle("Broken Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setResizable(false);

        add(label);
        add(timer_label);
        add(button2);
        add(button3);
        add(button_plus);
        add(button_x);
        add(button_equal);
        add(button_ac);
        add(reset);
        add(start);
        button2.addActionListener(new ButtonListener(label));
        button3.addActionListener(new ButtonListener(label));
        button_plus.addActionListener(new ButtonListener(label));
        button_x.addActionListener(new ButtonListener(label));
        button_equal.addActionListener(new ButtonListener(label));
        button_ac.addActionListener(new ButtonListener(label));
        reset.addActionListener(new ButtonListener(label));
        start.addActionListener(new ButtonListener(label));

        frame.setVisible(true);
    }

    public void calculator(Graphics2D g)
    {
        g.setColor(Color.blue);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Broken Calculator",130,35);

        GradientPaint paint2 = new GradientPaint(270,50,Color.GRAY,220,100,Color.darkGray,true);

        g.setPaint(paint2);
        g.fillRoundRect(270,50,270,300,20,20);
        g.setColor(Color.black);
        BasicStroke stroke = new BasicStroke(4);
        g.setStroke(stroke);
        g.drawRoundRect(270,50,270,300,20,20);

        String[] s = {"Most of the keys","have fallen off the","calculator. You","have 4 minutes to", "make these eight","numbers."};
        g.setFont(new Font("Arial",Font.PLAIN,25));

        for(int i=0;i<s.length;i++)
        {
            g.drawString(s[i],10,80 + i*32 );
        }

        g.setFont(new Font("Times New Roman",Font.BOLD,25));

        drawNumbers(g);

        g.setStroke(new BasicStroke());
        label.setBackground(Color.white);
        label.setBounds(285,65,240,55);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        label.setFont(new Font("Arial",Font.BOLD,30));
        label.setOpaque(true);

        timer_label.setBackground(Color.LIGHT_GRAY);
        timer_label.setForeground(Color.BLACK);
        timer_label.setBounds(300,420,220,40);
        timer_label.setFont(new Font("Arial",Font.PLAIN,20));
        timer_label.setOpaque(true);

        int width = 40;
        int space = 7;

        buttonSet(button2,335,245, Color.ORANGE,Color.black,1);
        buttonSet(button3,335 + width + space,245,Color.ORANGE,Color.BLACK,1);
        buttonSet(button_plus,335 + 2*(width + space),245 + (width + space),Color.ORANGE,Color.BLACK,1);
        buttonSet(button_equal,335 + 3*(width + space),245 + (width + space),Color.ORANGE,Color.white,1);
        buttonSet(button_x,335 + 2*(width + space),245 - (width + space),Color.ORANGE,Color.black,1);
        buttonSet(button_ac,335 + 3*(width + space),245 - 2*(width + space),Color.red,Color.white,1);
        buttonSet(reset,430,15,Color.GRAY,Color.BLACK,2);
        buttonSet(start,330,360,Color.GRAY,Color.BLACK,3);
    }

    public void buttonSet(JButton b,int x,int y, Color c,Color c2,int version)
    {
        b.setBackground(c);
        b.setForeground(c2);
        b.setFont(new Font("Arial",Font.BOLD,35));
        b.setMargin(new Insets(0, 0, 0, 0));

        if(version==1)
        {
            b.setBounds(x,y,40,40);
        }
        else if(version==2)
        {
            b.setFont(new Font("Arial",Font.PLAIN,15));
            b.setBounds(x,y,100,20);
        }
        else
        {
            b.setFont(new Font("Arial",Font.PLAIN,20));
            b.setBounds(x,y,140,30);
        }
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        //GradientPaint paint1 = new GradientPaint(0,0,Color.blue,100,100,Color.cyan,true);

        g2.setPaint(Color.white);
        g2.fillRect(0,0,1000,1000);
        g2.setColor(Color.black);

        calculator(g2);

        int x, y = 0;
        for (int i = 0; i < rezultatet.length; i++)
        {
            if (!label.getText().equals("") && Integer.parseInt(label.getText())==rezultatet[i])
            {
                result_line[i] = rezultatet[i];
            }
        }

        BasicStroke stroke = new BasicStroke(5);
        g2.setStroke(stroke);

        for(int i=0;i<result_line.length;i++)
        {
            if (i % 2 == 0)
            {
                x = 40;
                y = 310 + i * 20;
            }
            else
            {
                x = 150;
            }
            if(result_line[i]!=0)
            {
                g2.drawLine(x - 10, y - 10, x + 30, y - 10);
            }
        }

        g2.setStroke(new BasicStroke());

        if(notZero(result_line))
        {
            rezultatet = randomArray(8);
            End_Panel.again = false;
            result_line = new int[rezultatet.length];
            stopwatch.stop();
            frame.getContentPane().remove(this);
            frame.getContentPane().add(new End_Panel(true,frame,this));
        }

        if(reset_boolean)
        {
            rezultatet = randomArray(8);
            label.setText("0");
            result_line = new int[rezultatet.length];
            g.clearRect(0,0,1000,1000);
            reset_boolean = false;
            Detyra.numrat.clear();
            stopwatch.stop();
            count = 240;
            repaint();
        }

        if(count == 0)
        {
            rezultatet = randomArray(8);
            frame.getContentPane().remove(this);
            frame.getContentPane().add(new End_Panel(false,frame,this));
        }

        this.repaint();
    }

    public boolean notZero(int[] a)
    {
        boolean rez = true;

        for(int i=0;i<a.length;i++)
        {
            if(a[i]==0) rez = false;
        }

        return rez;
    }

    public void drawNumbers(Graphics2D g)
    {
        int x,y =0;
        for(int i=0;i<rezultatet.length;i++)
        {
            if(i%2==0)
            {
                x = 40;
                y = 310 + i*20;
            }
            else
            {
                x= 150;
            }
            g.drawString(""+ rezultatet[i],x,y);
        }
    }

    public static void startTimer(int timePass)
    {
        ActionListener action = new ActionListener()
        {
            int m=0;
            int s=0;

            public void actionPerformed(ActionEvent e)
            {
                if(count==0)
                {
                    timer_label.setText("Time passed!");
                    stopwatch.stop();
                }
                else
                {

                    timer_label.setText("Time remaining: " + digitFixer(count/60 + "") + ":" + digitFixer(count%60 + ""));
                    count--;
                }
            }
        };

        stopwatch = new Timer(delay,action);
        stopwatch.setInitialDelay(0);
        stopwatch.start();
        count = timePass;
    }

    public static String digitFixer(String s)
    {
        if(s.length()==1)
        {
            s = "0" + s;
        }

        return s;
    }

    public int[] randomArray(int i)
    {
        int[] rez = new int[i];

        int min = 4;
        int max = 100;
        int range = max - min + 1;
        int random = 0;
        boolean e = true;

        int count = 0;
        while(count<i)
        {
            random = (int)(Math.random()*range) + min;

            for(int a: rez)
            {
                if(random==a)
                {
                    e = false;
                }
            }
            if(e)
            {
                rez[count] = random;
                count++;
                e = true;
            }
        }
        return rez;
    }

    public static void main(String[] args)
    {
        new Detyra();
    }
}