package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.entity.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Ondrej Stuchlik
 */
public class GraphicsDrawer {
    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;

    private Graphics g;
 

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream("/images/cannon.png"));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy1.png"));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy2.png"));
            missileImage = ImageIO.read(getClass().getResourceAsStream("/images/missile.png"));
            collisionImage = ImageIO.read(getClass().getResourceAsStream("/images/collision.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    public void visitCannon(Cannon c) {
        if (g == null) return;
        this.drawCannon(g, c);
    }

    public void visitEnemy(Enemy e) {
        if (g == null) return;
        this.drawEnemy(g, e);
    }

    public void visitCollision(Collision c) {
        if (g == null) return;
        this.drawCollision(g, c);
    }

    public void visitMissile(Missile m) {
        if (g == null) return;
        this.drawMissile(g, m);
    }

    public void visitGameInfo(GameInfo i) {
        if (g == null) return;
        this.drawInfo(g, i);
    }

    public void drawCannon(Graphics g, Cannon cannon) {
        g.drawImage(cannonImage,
              cannon.getPosX() - cannonImage.getWidth()/2,
              cannon.getPosY() - cannonImage.getHeight()/2, null);
    }
    
    public void drawMissile(Graphics g, Missile missile) {
        g.drawImage(missileImage,
                missile.getPosX() - missileImage.getWidth()/2,
                missile.getPosY() - missileImage.getHeight()/2, null);
    }
    
    public void drawEnemy(Graphics g, Enemy enemy) {
        g.drawImage(enemyImage1,
                enemy.getPosX() - enemyImage1.getWidth()/2,
                enemy.getPosY() - enemyImage1.getHeight()/2, null);
    }
    
    public void drawCollision(Graphics g, Collision collision) {
        g.drawImage(collisionImage,
                collision.getPosX() - collisionImage.getWidth()/2,
                collision.getPosY() - collisionImage.getHeight()/2, null);
    }
    
    public void drawInfo(Graphics g, GameInfo info) {
        g.drawString(info.getText(), info.getPosX(), info.getPosY());
    }
    

}
