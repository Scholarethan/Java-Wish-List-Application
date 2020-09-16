//Citation: Learned how to do UI from TellerApp class as instructed by TA.
//Phase 3 Citations:
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
//https://docs.oracle.com/javase/7/docs/api/java/awt/Frame.html#setUndecorated(boolean)
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TopLevelDemoProject/src/components/TopLevelDemo.java
//https://docs.oracle.com/javase/tutorial/uiswing/components/jcomponent.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
//https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
//http://www.java2s.com/Tutorials/Java/Swing_How_to/Layout/Combine_BorderLayout_and_BoxLayout.htm
//https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
//https://stackoverflow.com/questions/761341/boxlayout-cant-be-shared-error
//https://www.tutorialspoint.com/how-can-we-add-multiple-sub-panels-to-the-main-panel-in-java
//https://coderanch.com/t/343686/java/setLocationRelativeTo
//https://stackoverflow.com/questions/49662195/boxlayout-from-bottom-to-top-javaswing
//http://www.java2s.com/Code/Java/Swing-JFC/AsimpleJScrollPaneforaJListcomponent.htm
//https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
//https://www.geeksforgeeks.org/java-swing-jtextarea/
//https://www.tutorialspoint.com/awt/awt_action_listener.htm
//https://www.geeksforgeeks.org/anonymous-inner-class-java/
//https://www.geeksforgeeks.org/nested-classes-java/
//https://stackoverflow.com/questions/9733702/clicking-the-cancel-button-showinputdialogue
//https://stackoverflow.com/questions/21228284/exception-in-thread-awt-eventqueue-0-java-lang-nullpointerexception-error
//https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
//http://www.nullpointer.at/2011/08/21/java-code-snippets-howto-resize-an-imageicon/#comment-11870
//https://docs.oracle.com/javase/tutorial/sound/playing.html
//https://stackoverflow.com/questions/3927941/system-sounds-in-java
//https://www.rgagnon.com/javadetails/java-play-a-windows-sound.html
//https://stackoverflow.com/questions/13963392/add-image-to-joptionpane
//https://www.youtube.com/watch?v=ChpZKgTCoJI

//This class contains all of the contents specifically related to the GUI

package ui;

import model.Item;
import model.WishList;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import static ui.App.*;

public class GUI extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    protected static final String spaceCreator = " \n \n \n \n \n \n \n \n \n \n \n \n \n "
            + " \n \n\n \n \n \n \n \n \n \n \n \n \n \n \n";
    protected static JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel listHeaderPanel;
    protected static JTextArea listTextBox;
    protected static ImageIcon happyPic;
    protected static ImageIcon sadPic;

    //Constructs main window
    //EFFECTS: sets up window which wish list application will be displayed in
    public GUI() {
        makeHappyPic();
        makeSadPic();
        frame = new JFrame("Wish List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        makeMainPanel();
        makeMenu();
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void makeSadPic() {
        sadPic = new ImageIcon("sadpic.jpg");
        Image image = sadPic.getImage();
        Image scaledImage = image.getScaledInstance(100,120,Image.SCALE_SMOOTH);
        sadPic = new ImageIcon(scaledImage);
    }

    private void makeHappyPic() {
        happyPic = new ImageIcon("happypic.jpg");
        Image image = happyPic.getImage();
        Image scaledImage = image.getScaledInstance(120,100,Image.SCALE_SMOOTH);
        happyPic = new ImageIcon(scaledImage);
    }

    private void makeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.PINK);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
        makeWelcomePanel();
        makeListHeaderPanel();
        makeListPanel();
        makeButtonPanel();
    }

    private void makeListHeaderPanel() { //In the future, I want to center the text / fix spacing issues
        listHeaderPanel = new JPanel();
        listHeaderPanel.setBackground(Color.lightGray);
        listHeaderPanel.setLayout(new BoxLayout(listHeaderPanel, BoxLayout.LINE_AXIS));
        listHeaderPanel.add(Box.createHorizontalStrut(40));
        listHeaderPanel.add(new JLabel("1.Item Names"));
        listHeaderPanelAddSpacing();
        listHeaderPanel.add(new JLabel("2.Category"));
        listHeaderPanelAddSpacing();
        listHeaderPanel.add(new JLabel("3.Cost ($)"));
        listHeaderPanelAddSpacing();
        listHeaderPanel.add(new JLabel("4.Purchased?"));
        listHeaderPanelAddSpacing();
        listHeaderPanel.add(new JLabel("5.Archived?"));
        listHeaderPanel.add(Box.createHorizontalStrut(20));
        mainPanel.add(listHeaderPanel);
    }

    private void listHeaderPanelAddSpacing() {
        listHeaderPanel.add(Box.createHorizontalStrut(40));
        listHeaderPanel.add(new JSeparator(SwingConstants.VERTICAL));
    }

    private void makeListPanel() {
        listTextBox = new JTextArea(spaceCreator);
        listTextBox.setEditable(false);
        JScrollPane listScrollBox = new JScrollPane(listTextBox);
//        myWishList = new WishList();
        printWishList();
        mainPanel.add(listScrollBox);
    }

    //EFFECTS: creates the north header panel and adds it to frame
    private void makeWelcomePanel() {
        JLabel welcome = new JLabel("Welcome to your wish list!");
        welcome.setFont(new Font("Comic Sans", Font.BOLD, 16));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.PINK);
        welcomePanel.add(welcome);
        mainPanel.add(welcomePanel);
    }

    //EFFECTS: creates the south button panel and adds it to frame
    private void makeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        createAddButton();
        createRemoveButton();
        createPurchasedButton();
        createArchiveButton();
        createSearchButton();
        mainPanel.add(buttonPanel);
    }

    //EFFECTS: Creates and implements the add button on the buttonPanel
    private void createAddButton() {
        JButton add = new JButton("Add Item");
        buttonPanel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item userItem = promptUserForItem();
                if (userItem == null) {
                    return;
                }
                addItemToWishList(userItem);
                printWishList();
            }
        });
    }

    //EFFECTS: Creates and implements the remove button on the buttonPanel
    private void createRemoveButton() {
        JButton remove = new JButton("Remove Item");
        buttonPanel.add(remove);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item userItem = promptUserForItem();
                if (userItem == null) {
                    return;
                }
                removeItemFromWishList(userItem);
                printWishList();
            }
        });
    }

    //EFFECTS: Creates and implements the purchased button on the buttonPanel
    private void createPurchasedButton() {
        JButton purchase = new JButton("Mark Purchased");
        buttonPanel.add(purchase);
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item userItem = promptUserForItem();
                if (userItem == null) {
                    return;
                }
                markAsPurchasedInWishList(userItem);
                printWishList();
            }
        });
    }

    //EFFECTS: Creates and implements the archive button on the buttonPanel
    private void createArchiveButton() {
        JButton archive = new JButton("Archive Item");
        buttonPanel.add(archive);
        archive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item userItem = promptUserForItem();
                if (userItem == null) {
                    return;
                }
                markAsArchived(userItem);
                printWishList();
            }
        });
    }

    //EFFECTS: Creates and implements the search button on the buttonPanel
    private void createSearchButton() {
        JButton search = new JButton("Search for Item");
        buttonPanel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item userItem = promptUserForItem();
                if (userItem == null) {
                    return;
                }
                printItem(userItem);
            }
        });
        mainPanel.add(buttonPanel);
    }

    //EFFECTS: creates the drop down menu tab and adds it to frame
    private void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuLoad = new JMenuItem("Load");
        JMenuItem menuSave = new JMenuItem("Save");
        menuBar.add(menu);
        menu.add(menuLoad);
        menu.add(menuSave);
        menuLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWishList();
                printWishList();
            }
        });
        menuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWishList();
            }
        });
        frame.setJMenuBar(menuBar);
    }

}





