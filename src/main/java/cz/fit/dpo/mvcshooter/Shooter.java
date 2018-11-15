package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.proxy.GameModelProxy;
import cz.fit.dpo.mvcshooter.proxy.IGameModel;
import cz.fit.dpo.mvcshooter.view.MainWindow;

import javax.swing.*;

/**
 *
 * @author stue
 */
public class Shooter {
    
    public static void main(String[] args) {

        final IGameModel gameModel = new GameModel();
        final IGameModel proxyModel = new GameModelProxy(gameModel);

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                new MainWindow(proxyModel).setVisible(true);
            }
        });
    }
}
