import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
    private JLabel l;

    public ButtonListener(JLabel label)
    {
        l = label;
    }

    public void actionPerformed(ActionEvent e)
    {
        String[] vargu = {"2", "3"};

        String text = "";
        if (!l.getText().equals("0"))
        {
            text = l.getText();
        }

        if(Detyra.count != 240)
        {
            if (e.getActionCommand().equals("+") && !l.getText().equals("0") && !l.getText().equals(""))
            {
                Detyra.shenja = "+";
                Detyra.numrat.add(Integer.parseInt(text));
                l.setText("");
            }

            if (e.getActionCommand().equals("x") && !l.getText().equals("0") && !l.getText().equals(""))
            {
                Detyra.shenja = "x";
                Detyra.numrat.add(Integer.parseInt(text));
                l.setText("");
            }

            for (String s : vargu)
            {
                if (e.getActionCommand().equals(s))
                {
                    text = text + s;
                    l.setText(text);
                }
            }

            if (e.getActionCommand().equals("=") && !l.getText().equals("0") && !l.getText().equals(""))
            {
                Detyra.numrat.add(Integer.parseInt(text));
                if (Detyra.shenja.equals("+"))
                {
                    l.setText(operation("+") + "");
                    Detyra.numrat.add(operation("+"));
                }
                if (Detyra.shenja.equals("x"))
                {
                    l.setText(operation("x") + "");
                    Detyra.numrat.add(operation("x"));
                }
            }

            if (e.getActionCommand().equals("C"))
            {
                l.setText("0");
                Detyra.numrat.clear();
            }

            if(e.getActionCommand().equals("Reset"))
            {
                Detyra.reset_boolean = true;
                Detyra.stopwatch.stop();
                Detyra.count = 240;
                Detyra.timer_label.setText("Time remaining: 04:00");
            }
        }

        if(e.getActionCommand().equals("Start"))
        {
            Detyra.count = 240;
            Detyra.startTimer(240);
        }

        if(e.getActionCommand().equals("Try Again") || e.getActionCommand().equals("Play Again"))
        {
            End_Panel.again = true;
        }
    }

    public int operation(String shenja)
    {
        int result = 0;

        int a = Detyra.numrat.get(Detyra.numrat.size()-1);
        int b = Detyra.numrat.get(Detyra.numrat.size()-2);

        if(shenja.equals("+"))
        {
            result = a+b;
        }
        if(shenja.equals("x"))
        {
            result = a*b;
        }

        return result;
    }
}
