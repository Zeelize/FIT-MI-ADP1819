package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.GameController;
import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.model.entity.GameObject;
import cz.fit.dpo.mvcshooter.observer.IObserver;

import javax.swing.*;
import java.awt.*;


public class Canvas extends JPanel implements IObserver {
    private GraphicsDrawer drawer = new GraphicsDrawer();
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
            this.gameModel.detachObserver(this);
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

        drawer.setGraphics(g);

        for (GameObject o : this.gameModel.getGameObjects()) {
            o.acceptVisitor(drawer);
        }
    }
    
}
