import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.List;
/**
 * Keeps the high score globally
 * 
 * @Brian Huang
 * @ V 1.00
 */
public class HighScore extends Actor
{
    private GreenfootImage scoreBoard;
    private Color textColor = new Color (100,66,255);
    private Font textFont = new Font ("Calibri", Font.BOLD, 18);
    private Font titleFont = new Font ("Calibri", Font.BOLD, 24);

    public HighScore(int currentScore)
    {   
        scoreBoard = new GreenfootImage(400,500);
        scoreBoard.setColor (Color.WHITE);
        scoreBoard.fill();
        
        List top10 = UserInfo.getTop(10);
        UserInfo myInfo = UserInfo.getMyInfo();
        
        if (myInfo.getScore() < currentScore) 
        {
            myInfo.setScore(currentScore);
            myInfo.store();
        }
        
        scoreBoard.setFont (titleFont);
        scoreBoard.setColor (Color.BLACK);
        scoreBoard.drawString ("TOP TEN HIGH SCORES",75,40);
        
        scoreBoard.setFont (textFont);
        scoreBoard.setColor (textColor);
        
        if (top10 !=null)
        {
            for (int i = 0; i<top10.size(); i++) 
            {
                scoreBoard.drawString((i+1) + ". " + (((UserInfo)top10.get(i)).getUserName()),20,100+i*25);
                scoreBoard.drawString(Integer.toString((((UserInfo)top10.get(i)).getScore())),350,100+i*25);
            }
        }
        
        scoreBoard.drawString(myInfo.getUserName(),20,100+12*25);
        scoreBoard.drawString(Integer.toString(myInfo.getScore()),350,100+12*25);
        scoreBoard.drawString("Your World High Score Ranking is: " + myInfo.getRank (), 20, 100+14*25);
        this.setImage(scoreBoard);

    }
}