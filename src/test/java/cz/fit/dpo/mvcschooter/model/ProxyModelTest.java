package cz.fit.dpo.mvcschooter.model;

import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.model.entity.Enemy;
import cz.fit.dpo.mvcshooter.proxy.GameModelProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ProxyModelTest {
    @InjectMocks
    private GameModelProxy proxy;

    @Mock
    private GameModel model;

    @Test
    public void allGameObjectsTest() {
        Enemy enemy = new Enemy(50, 50, 0.0f);
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemy);
        Mockito.when(model.getEnemies()).thenReturn(enemyList);

        Assert.assertEquals(enemy, proxy.getEnemies().get(0));
    }
}
