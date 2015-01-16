import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private Missile m;
    private Player p;
    private boolean removeMe = false;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        p = (Player)getOneIntersectingObject(Player.class);
        //Player only dies to enemy if below 400 pixel on the Y-zxis
        if (p != null && p.getY() > 200)
        {
            MyWorld m = (MyWorld)getWorld();
            m.gameOver();
        }
        //If player hits enemy dies
        if (p != null)
        {
            removeMe = true;            
        }
        //If enemy is below the screen then he dies
        if (getY() >= 549)
        {
            removeMe = true;
        }
        m = (Missile)getOneIntersectingObject(Missile.class);
        //If enemy is hit by missile he is removed
        if (m != null)
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
