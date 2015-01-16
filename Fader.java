import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

//Borrowed from Mr.Cohen
//Starts here:
/**
 * Fader spawns a black square over the board that fades in and out to
 * provide a fading effect. This effect is built to pause the scenario
 * by incorporating delay into a loop.
 * 
 * @author Jordan Cohen
 * @version 0.1.0
 */
public class Fader extends Actor
{
    GreenfootImage fade;

    /**
     * Default constructor - When a new Fader is added
     */
    public Fader ()
    {
        fade = new GreenfootImage (800, 600);
        fade.setColor (Color.BLACK);
        fade.fill();
        fade.setTransparency (0);
        setImage (fade);
    }

    /**
     * addedToWorld(World w) is automatically called when a Fader object
     * is added to the World. In this case, stop the game (delay in loop
     * has that effect) and gradually make the black square darker.
     */
    public void addedToWorld (World w)
    {
        for (int fader = 0; fader <= 255; fader += 20)
        {
            fade.setTransparency(fader);
            Greenfoot.delay(2);
        }

    }

    /**
     * When finished updating the level, call this method to
     * slowly fade the black square back to clear, and eventually
     * remove it.
     */
    public void fadeBackIn ()
    {
        Greenfoot.delay(10);
        for (int fader = 255; fader >= 0; fader -= 20)
        {
            fade.setTransparency(fader);
            Greenfoot.delay(2);
        }  
        getWorld().removeObject(this);
    }
}
//End of borrowed code

