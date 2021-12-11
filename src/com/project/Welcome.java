package com.project;

/**
 *
 * @author Tom Marvolo Riddle
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome implements ActionListener
{
    JFrame frame ;
    JLabel welcome , to , mms , medical ;
    JPanel panel ;
    JButton next ;
    Welcome()
    {
        frame = new JFrame();        
        panel = new JPanel();
        next = new JButton("Next");
        welcome = new JLabel("Welcome");
        to = new JLabel("To");
        mms = new JLabel("Medical Management System");
        to.setFont(new Font("Bebas Neue" , Font.BOLD , 50));
        welcome.setFont(new Font("Bebas Neue" , Font.BOLD , 80));
        mms.setFont(new Font("Bebas Neue" , Font.BOLD , 65));
        medical = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\medicine2.png").getImage().getScaledInstance(512,512, Image.SCALE_DEFAULT)));

        //----------- set layouts to panel and frame -----------------
        frame.setLayout(new BorderLayout());
        panel.setLayout(null);;
        
        //-------------- decorate panel and components on panel ---------------
        panel.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        panel.setBackground(new Color(152, 251, 152));
        next.setForeground(new Color(255,250,250));
        next.setBackground(new Color(0,0,0));
        
        next.setFocusable(false);
        
        //------------ actionlistner to next --------------
        next.addActionListener(this);
        
        //--------------setbounds to components ----------
        welcome.setBounds(450, 150, 700, 80);
        to.setBounds(580, 300, 400, 50);
        mms.setBounds(200, 400, 1000, 85);
        medical.setBounds(350, 80, 512, 512);
        
        next.setBounds(1150, 600, 65, 25);
        
        //--------------- add components to panel and frame ---------
        frame.add(panel , BorderLayout.CENTER);
        panel.add(welcome);
        panel.add(to);
        panel.add(next);
        panel.add(mms);
        panel.add(medical);

        //--------------- frame settings -------------------        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == next)
        {
            new Login();
            frame.dispose();
        }
    }
    
}
