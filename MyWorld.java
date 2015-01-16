import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * Use "a" and "d" to move the player
 * Aim and click to shoot enemies
 *  
 * There is a bug when the missile does not remove itself from the world 
 * There is a bug when there is extra sounds added to the sound files
 * 
 * @author (Jason Fok) 
 * @version (1.0)
 */
public class MyWorld extends World
{
    // Declare class variables
    private Player player;
    private Platform[] platforms;
    private Spring[] springs;
    private Platform startingPlatform;
    private int platformCounter = 500;
    private ArrayList tempPlatforms;
    private ArrayList tempSprings;
    private ArrayList tempJetpack;
    private ArrayList tempEnemy;
    private ArrayList tempBreakingPlatforms;
    private Fader fader;
    private int score = 0;
    private Counter counter;
    private int randomX; 
    private boolean gameOver = true;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x550 cells with a cell size of 1x1 pixels.
        super(600, 550, 1);
        // Set the order in which Actors are drawn on the screen in this World
        setPaintOrder ( Counter.class, HighScore.class, Fader.class);
        // Counter
        counter = new Counter ("Score: ");
        addObject (counter, 50, 30);
        // Initialize the player
        player = new Player ();
        addObject (player, 300, 530); 
        platforms = new Platform[10]; 
        startingPlatform = new Platform();
        addObject (startingPlatform, 300, 550);
        // Loop through as many Meteors as there are on this level
        for (int i = 0; i < platforms.length; i++)
        {
            // Initialize a new Platform object for each spot in the array
            platforms[i] = new Platform ();
            // Add each platform at a random position anywhere on the X axis and at intervals of 15 on the Y axis
            addObject (platforms[i], Greenfoot.getRandomNumber(600), platformCounter);
            platformCounter = platformCounter - 55;
        }
    }

    /** 
     * Every act, check the keys and move appropriately
     */
    public void act ()
    {
        checkScroll();
        checkPlatform();
        checkXAxis();
        checkDeath();
        // get the current state of the mouse
        MouseInfo m = Greenfoot.getMouseInfo();
        // if the mouse is on the screen...
        if (m != null)
        {
            // if the mouse button was pressed
            if (Greenfoot.mousePressed(null))
            {
                // shoot
                player.shoot(m.getX(), m.getY());
            }
        }
    }

    /**
     * Method called when the Player accidentally touches the bottom fo the screen and ends the game
     */
    public void gameOver ()
    {
        Greenfoot.delay (50);
        if (gameOver)
        {
            fader = new Fader (); // create a new fader
            addObject (fader, 400, 300); // add the fader object to the world
            if (UserInfo.isStorageAvailable()) {
                HighScore highScore = new HighScore (score);
                addObject (highScore, 300, 170);
            }
            Greenfoot.stop();
        }
    }

    /**
     * Checks if the player hits the bottom of the world
     */
    public void checkDeath()
    {
        if (player.getY() >= 549)
        {
            gameOver();
        }
    }

    /**
     * Checks if the player hits the edge of the screen and relocates to the other side of the world if so
     */
    public void checkXAxis ()
    {
        if (player.getX() >= 599)
        {
            player.setLocation (0, player.getY());
        }
        else if (player.getX() <= 0)
        {
            player.setLocation (598, player.getY());
        }
    }

    /**
     * check if a platform is removed, then adds power-ups and new platforms
     */
    public void checkPlatform()
    {
        for (int i = 0; i < platforms.length; i++)
        {            
            if (platforms[i].getY() >= 549)
            {
                removeObject (platforms[i]);
                //Initialize a new Platform object with the variable name of the platform that just got removed
                platforms[i] = new Platform ();
                randomX = Greenfoot.getRandomNumber(550);
                if (Greenfoot.getRandomNumber (10) == 0)
                {
                    //Add each breaking platform at a random position anywhere on the X axis and at intervals of 15 on the Y axis
                    addObject (new BreakingPlatforms(), randomX, 0);
                }
                //Randomize randomX's value again for the regular platform
                randomX = Greenfoot.getRandomNumber(550);
                // Add each platform at a random position anywhere on the X axis and at intervals of 15 on the Y axis
                addObject (platforms[i], randomX, 0);
                if (Greenfoot.getRandomNumber (20) == 0)
                {
                    addObject (new Spring(), randomX, 0);
                }
                //Add Jetpacks randomly
                if (Greenfoot.getRandomNumber (25) == 0)
                {
                    addObject (new Jetpack(), randomX, 0);
                }
                randomX = Greenfoot.getRandomNumber(550);
                //Add enemies randomly
                if (Greenfoot.getRandomNumber (15) == 0)
                {
                    addObject (new Enemy(), randomX, 0);
                }
            }
        }
    }

    /**
     * checks if player is above the middle of the screen, then scrolls everything and increases the players score
     */
    public void checkScroll()
    {
        if (player.getY() <= 300)
        {
            removeObject (startingPlatform);
            scrollEverything();
            score += 5;
            counter.setValue (score);
        }
    }

    /**
     * Scolls all objects down except for the palyer when the player is above the midpoint on the y-axis
     */
    public void scrollEverything()
    {      
        tempPlatforms = (ArrayList)getObjects(Platform.class);
        for (Object o : tempPlatforms)
        {
            Platform p = (Platform)o;
            p.scroll();
        }
        tempBreakingPlatforms = (ArrayList)getObjects(BreakingPlatforms.class);
        for (Object o : tempBreakingPlatforms)
        {
            BreakingPlatforms bp = (BreakingPlatforms)o;
            bp.scroll();
        }
        tempSprings = (ArrayList)getObjects(Spring.class);
        for (Object o : tempSprings)
        {
            Spring s = (Spring)o;
            s.scroll();
        }
        tempJetpack = (ArrayList)getObjects(Jetpack.class);
        for (Object o : tempJetpack)
        {
            Jetpack j = (Jetpack)o;
            j.scroll();
        }
        tempEnemy = (ArrayList)getObjects(Enemy.class);
        for (Object o : tempEnemy)
        {
            Enemy e = (Enemy)o;
            e.scroll();
        }
        player.scroll();
    }
}

