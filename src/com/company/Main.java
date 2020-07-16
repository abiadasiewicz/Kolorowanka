package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    Main() {
        initComponents();
    }

    public void initComponents() {
        this.setSize(400, 300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setTitle("Pasek narzędzi");

        pasekNarzedzi.add(new KolorowyButton(new ActionColor("Zmieniam kolor na zielony", new ImageIcon("zielony.gif"), Color.green)));
        pasekNarzedzi.add(new KolorowyButton(new ActionColor("Zmieniam kolor na niebieski", new ImageIcon("niebieski.gif"), Color.blue)));
        pasekNarzedzi.add(new KolorowyButton(new ActionColor("Zmieniam kolor na czerwony", new ImageIcon("czerwony.gif"), Color.red)));
        pasekNarzedzi.add(button);


        this.getContentPane().setLayout(new GridLayout(2, 1));
        this.getContentPane().add(pasekNarzedzi);
        this.getContentPane().add(panel);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setCursor(Cursor.getDefaultCursor());
                aktywny=null;
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               
                if(aktywny!=null)
                {
                panel.setBackground((Color) aktywny.getAction().getValue("kolor"));
                }
            }
        });
    }

    private JToolBar pasekNarzedzi = new JToolBar("Nazwa");
    private JButton button = new JButton("Wyłącz malowanie");
    private JPanel panel = new JPanel();
    private KolorowyButton aktywny=null;

    private class ActionColor extends AbstractAction {

        public ActionColor(String toolTip, Icon icon, Color colour) {
            this.putValue(Action.SHORT_DESCRIPTION, toolTip);
            this.putValue(Action.SMALL_ICON, icon);
            this.putValue("kolor", colour);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
aktywny=(KolorowyButton) e.getSource();
        }
    }


    private class KolorowyButton extends JButton {

        public KolorowyButton(ActionColor actionColor) {
            super(actionColor);

            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < pasekNarzedzi.getComponentCount(); i++) {
                        if (pasekNarzedzi.getComponent(i) instanceof KolorowyButton) {
                            KolorowyButton tmp = (KolorowyButton) pasekNarzedzi.getComponent(i);
                            tmp.setBackground(Color.WHITE);
                            tmp.setZaznaczony(false);
                            // rootPane.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                            panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("kursorek.png").getImage(), new Point(0, 0), "Kursor"));
                        }
                    }
                    ten.setBackground((Color) actionColor.getValue("kolor"));
                    ten.setZaznaczony(true);
                }
            });
        }

        public void setZaznaczony(boolean zaznacz) {
            this.zaznaczony = zaznacz;
        }

        KolorowyButton ten = this;
        boolean zaznaczony = false;
    }


    public static void main(String[] args) {
        // write your code her
        new Main().setVisible(true);
    }
}
