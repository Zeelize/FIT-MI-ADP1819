package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.Cannon;
import cz.fit.dpo.mvcshooter.model.GameModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Canvas extends JPanel { 
    GraphicsDrawer drawer = new GraphicsDrawer();

    public Canvas(int x, int y, int width, int height) {
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width,height));
        this.setVisible(true);        
    }
    
    public void setGameModel(GameModel gameModel) {
        drawer.setGameModel(gameModel);
    }

    public void redraw() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    
        drawer.drawCannon(g);
    }
    
}
