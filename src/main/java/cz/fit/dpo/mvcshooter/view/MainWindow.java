package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.GameController;
import cz.fit.dpo.mvcshooter.proxy.IGameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ondrej Stuchlik
 */
public class MainWindow extends JFrame {


    public MainWindow(final IGameModel gameModel) {
        try {
            final Canvas view = new Canvas(0, 0, gameModel.getConfWidth(), gameModel.getConfHeight());
            final GameController gameController = view.CreateController();
            gameController.setGameModel(gameModel);
            view.setGameModel(gameModel);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("These birds are angry!");
            this.setResizable(false);

            Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(
                  (int) (window.getWidth() / 2 - 250),
                  (int) (window.getHeight() / 2 - 250));

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    // delegate to controller
                    gameController.onKeyPressed(evt);
                }
            });

            this.add(view);
            this.pack();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public void showHelp() {
        JOptionPane.showMessageDialog(this, 
              "Controls: \n"
              + "here goes some description...");
    }
}
