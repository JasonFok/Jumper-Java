import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  

/** 
 * The person who will move about platforms. 
 * @author - Jason Fok 
 */  
public class Player  extends Actor  
{   
    private int speed = 10;
    private double velocityY = 0;
    private double acceleration = 0.5;
    private int timer = 1;
    private int jetpackTimer;
    private String lastKeyPressed;
    private boolean springBounce = false;
    private boolean jetpackBounce = false;
    private Platform p;
    private BreakingPlatforms bp;
    private static GreenfootSound bounceSound = new GreenfootSound("bounce.mp3");
    private static GreenfootSound springBounceSound = new GreenfootSound ("SpringBounce.wav");
    private static GreenfootSound jetpackSound = new GreenfootSound ("jetpack.mp3");

    /** 
     * Act - do whatever the Person wants to do. This method is called whenever 
     * the 'Act' or 'Run' button gets pressed in the environment. 
     */  
    public void act()   
    {  
        checkKeys();
        checkFall();
        checkSpring();
        checkJetpack();
    }      

    /**
     * Check to see if the player touches the side of the screen and moves it to the other side of the world
     */
    private void checkKeys ()
    {
        int changeX = 0;
        if (Greenfoot.isKeyDown("a")) 
        {
            if (jetpackTimer > 0)
            {
                setImage ("FrogWithJetpack-left.png");
            }
            else
            {
                setImage ("player-left.png");
            }
            lastKeyPressed = "a";
            changeX = changeX - speed;
        }
        if (Greenfoot.isKeyDown("d"))   
        {
            if (jetpackTimer > 0)
            {
                setImage ("FrogWithJetpack-right.png");
            }
            else
            {
                setImage ("player-right.png");
            }
            lastKeyPressed = "d";
            changeX = changeX + speed;
        }
        // Removes jetpack from player if jetPackTimer = 0
        if (jetpackTimer == 0)
        {
            if (lastKeyPressed == "a")
            {
                setImage ("player-left.png");
            }
            else
            {
                setImage ("player-right.png");
            }
        }
        else
        {
            //Makes sure to put the jetpack on the player for the duration of the power-up even if they don't press any keys 
            if (lastKeyPressed == "a")
            {
                setImage ("FrogWithJetpack-left.png");
            }
            else
            {
                setImage ("FrogWithJetpack-right.png");
            }
        }
        setLocation (getX() + changeX, getY());
    }  

    /**
     * Makes the player fall if it is not touching a platform
     */
    private void fall()
    {
        setLocation (getX(), getY() + (int) velocityY);
        //Make sure that the velocity does not get too high
        if (velocityY <= 14)
        {
            velocityY = acceleration + velocityY;
        }
    }

    /**
     * Checks if the player jumping or falling
     */
    private void checkFall()
    {
        if (onPlatform() && velocityY >= 0)
        {
            jump();
        }
        else
        {
            fall();
        }
    }

    /**
     * Checks if the player is touching a platform
     */
    private boolean onPlatform()
    {
        Actor under = getOneObjectAtOffset (0, getImage().getHeight() /2, Platform.class);
        p = (Platform)getOneIntersectingObject(Platform.class);
        if (p != null && velocityY >= 0)
        {
            if (bounceSound.isPlaying())
            {
                bounceSound.stop();
            }
            bounceSound.play();
        }
        return under != null;
    }

    private boolean onBreakingPlatform()
    {
        Actor underBreakingPlatform = getOneObjectAtOffset (0, getImage().getHeight() /2, BreakingPlatforms.class);
        bp = (BreakingPlatforms)getOneIntersectingObject(BreakingPlatforms.class);
        if (bp != null)
        {
            if (bounceSound.isPlaying())
            {
                bounceSound.stop();
            }
            bounceSound.play();
        }
        return underBreakingPlatform != null;
    }

    /**
     * Makes the player jump by changing it's velocity
     */
    public void jump()
    {
        velocityY = -14;
        fall();
    }

    /**
     * Moves the player down when the scroll method is called
     */
    public void scroll()
    {
        setLocation (getX(), getY() + 5);
    }

    public void checkSpring()
    {
        if (onSpring())
        {
            velocityY = -30;
            if (springBounce)
            {
                springBounce = false;
                if (springBounceSound.isPlaying())
                {
                    springBounceSound.stop();
                }
                springBounceSound.play();
            }
        }
    }

    public boolean onSpring ()
    {
        Actor s = getOneIntersectingObject (Spring.class);   
        springBounce = true;
        return s != null;
    }

    /**
     * Checks what the player should do if he lands on a Jetpack
     */
    public void checkJetpack()
    {
        if (onJetpack())
        {
            velocityY = -14;
            acceleration = 0;
            jetpackTimer = 200;
        }
        if (jetpackTimer == 0)
        {
            acceleration = 0.5;
            if (jetpackSound.isPlaying())
            {
                jetpackSound.stop();
            }
        }
        else
        {
            jetpackTimer--;
            if (jetpackBounce)
            {
                jetpackSound.play();
                jetpackBounce = false;
            }
        }
    }

    public boolean onJetpack()
    {
        Actor j = getOneIntersectingObject (Jetpack.class);   
        jetpackBounce = true;
        return j != null;
    }

    /**
     * public method to "shoot" a missile  in the direction the player clicked 
     */ 
    public void shoot (int targetX, int targetY)
    {
        Missile m = new Missile (targetX, targetY);
        getWorld().addObject (m, this.getX(), this.getY());
    }

    public double getVelocity()
    {
        return velocityY;
    }

    public int getJetpackTimer()
    {
        return jetpackTimer;
    }

    public void changeVelocity()
    {
        velocityY = 0;
    }

    public void changeAcceleration()
    {
        acceleration = 0;
    }
}
