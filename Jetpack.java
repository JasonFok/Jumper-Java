import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jetpack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jetpack extends Actor
{
    /**
     * Act - do whatever the Jetpack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (getY() >= 549 || getOneIntersectingObject (Player.class) != null  )
        {
            getWorld().removeObject (this);
        }
    }    

    public void scroll()
    {
        setLocation (getX(), getY() + 5);
    }
}
