import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BreakingPlatforms here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BreakingPlatforms extends Actor
{
    private static GreenfootSound bounceSound = new GreenfootSound("bounce.mp3");
    private boolean removeMe;
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (this.getY() >= 549)
        {
            removeMe = true;
        }
        Player p = (Player) getOneIntersectingObject (Player.class);  
        if (p != null && p.getVelocity() >= 0)
        {
            removeMe = true;
        }
        if (removeMe)
        {
            getWorld().removeObject (this);
        }
    }    

    public void scroll()
    {
        setLocation (getX(), getY() + 5);
    }
}
