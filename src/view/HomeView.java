package view;

import interface_adapter.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends JPanel{
    public final String viewName = "Home";

    HomeViewModel homeViewModel;
    ArticleRetrievalController articleRetrievalController;



    //Where the GUI is created:
    JMenuBar menuBar;
    JMenu PrefMenu, Refresh;
    JMenuItem LangMenu;
    JRadioButtonMenuItem EngButton, IceButton;
    JFrame frame;
    JPanel news;

    public HomeView(ArticleRetrievalController controller, HomeViewModel homeViewModel) {
        this.articleRetrievalController = controller;
        this.homeViewModel = homeViewModel;
        homeViewModel.addPropertyChangeListener((PropertyChangeListener) this);



        // Menu section
            //Create the menu bar.
        menuBar = new JMenuBar();
        frame = new JFrame();


            //Preferences
        PrefMenu = new JMenu("Preferences");
        PrefMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(PrefMenu);

            //Languages submenu
        LangMenu = new JMenu("Language");
        LangMenu.setMnemonic(KeyEvent.VK_L);

        ButtonGroup languages = new ButtonGroup();
        EngButton = new JRadioButtonMenuItem("English");
        EngButton.setMnemonic(KeyEvent.VK_E);
        EngButton.setSelected(true);
        languages.add(EngButton);
        LangMenu.add(EngButton);

        IceButton = new JRadioButtonMenuItem("Icelandic");
        IceButton.setMnemonic(KeyEvent.VK_I);
        languages.add(IceButton);
        PrefMenu.add(IceButton);


            //Refresh button
        Refresh = new JMenu("Refresh");
        Refresh.setMnemonic(KeyEvent.VK_R);
        menuBar.add(Refresh);


        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                articleRetrievalController.execute();
            }
        });

        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        //Headlines
        List headlines = new ArrayList<String>();
        for

    }
}
