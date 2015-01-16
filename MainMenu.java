import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    private Fader fader; 
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 550, 1); 
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            fader = new Fader (); // create a new fader
            addObject (fader, 400, 300); // add the fader object to the world
            Greenfoot.setWorld(new MyWorld()); // goes to the game
        }
    }
}
