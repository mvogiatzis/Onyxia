package onyxia.game;

import java.awt.*;
import javax.swing.*;

/**
 * Class ImagePanel - An extended JPanel used in Onyxia application.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * This class is used to display the Room's background image that is an entire
 * JPanel.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class ImagePanel extends JPanel{
    
    /**
     * The ImageIcon of the Panel
     */
    private ImageIcon imgIcon;
    
    /**
     * Constructor of ImagePanel class. 
     * @param imagePath String The relative path of the panel's image.
     * @throws NullPointerException If no image is found a NullPointerException
     * is thrown.
     */
    public ImagePanel(String imagePath) throws NullPointerException 
    {
        setImagePath(imagePath);
    }
    
    /**
     * Try to set the path of the image of the panel.
     * @param imgPath The relative path that leads to the panel image location.
     */
    public void setImagePath(String imgPath)
    {
        try 
        {
            imgIcon = new ImageIcon(getClass().getResource(imgPath));
            repaint();
        } 
        catch (NullPointerException np)
        {
            System.out.println("NullPointerException: "+np.toString());
        }
    }
    
    /**
     * This method overrides the Swing screen painter one every time it want
     * this component displayed. It copies the internal image to the screen.
     * 
     * @param g The graphics context that can be used to draw on this component.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Dimension size = getSize();
        g.clearRect(0, 0, size.width, size.height);
        if(imgIcon.getImage() != null) {
            g.drawImage(imgIcon.getImage(), 0, 0, null);
        }
    }
    
    
}
