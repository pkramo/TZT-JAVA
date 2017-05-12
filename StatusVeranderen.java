/**
 * Created by User on 11-5-2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Created by pjott on 11-5-2017.
 */
public class StatusVeranderen extends JFrame {
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JFrame jf = new JFrame();
    JTextField jt = new JTextField(30);
    public StatusVeranderen() {
        setTitle("Titel");
        setSize(1920, 1080);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //jp = new JPanel();
        //jp2 = new JPanel();
        //jp.add(jl);
        //jt = new JTextField("Please enter all your shit in here");
        jp.add(jl);
        jp.add(jt);
        jt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();

            }
        });
        //jb = new JButton("Press me");
        //jp.add(jb);
        add(jp);
    }
    public static void main(String[] args){
        StatusVeranderen d = new StatusVeranderen();
    }
}

