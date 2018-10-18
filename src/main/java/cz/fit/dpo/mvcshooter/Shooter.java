package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author stue
 */
public class Shooter {
    
    public static void main(String[] args) {

        final GameModel gameModel = new GameModel();

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               new MainWindow(gameModel).setVisible(true);
            }
        });
    }
}
