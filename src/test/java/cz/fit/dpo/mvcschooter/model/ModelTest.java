package cz.fit.dpo.mvcschooter.model;

import cz.fit.dpo.mvcshooter.model.GameModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {
    @InjectMocks
    private GameModel model;

    @Test
    public void pauseResumeGameTest() {
        model.pauseResumeGame();
        Assert.assertTrue(model.getPause());

        model.pauseResumeGame();
        Assert.assertTrue(!model.getPause());
    }

    @Test
    public void startStopGameTest() {
        Assert.assertTrue(model.getRunGame());

        model.stopGame();
        Assert.assertTrue(!model.getRunGame());

        model.startGame();
        Assert.assertTrue(model.getRunGame());
    }
}
