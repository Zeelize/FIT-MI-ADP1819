package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.GameController;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.observer.IObserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Canvas extends JPanel implements IObserver {
    GraphicsDrawer drawer = new GraphicsDrawer();
    private GameModel gameModel;


    public Canvas(int x, int y, int width, int height) {
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width,height));
        this.setVisible(true);        
    }
    
    public void setGameModel(GameModel gameModel) {
        if (this.gameModel != null && this.gameModel != gameModel) {
            this.gameModel.deattachObserver(this);
        }

        this.gameModel = gameModel;
        this.gameModel.attachObserver(this);
    }

    public GameController CreateController() {
        return new GameController();
    }

    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    
        drawer.drawCannon(g, this.gameModel.getCannon());
        drawer.drawInfo(g, this.gameModel.getInfo());

        for (Enemy e: this.gameModel.getEnemies()) {
            drawer.drawEnemy(g, e);
        }
    }
    
}
