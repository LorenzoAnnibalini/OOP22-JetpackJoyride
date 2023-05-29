package it.unibo.jetpackjoyride.model;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;

/**
 * JUnit test for the HitboxImpl class.
 */
public class HitboxImplTest {

    /**
     * Test for the checkCollision method.
     * This test check if the entity is in collision with the player in every
     * frontal position.
     * 
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testHitboxCollision() throws IOException, ParseException {
        final double xPlayer = 30.0;
        final double yPlayer = 15.0;
        final int heightPositionEntity = 380;
        final int heightPositionPlayer = 350;
        final Point2d positionEntity = new Point2d(xPlayer - 10, heightPositionEntity);
        final long dt = 2;
        int y = 0;
        final Point2d positionPlayer = new Point2d(xPlayer - 10, heightPositionPlayer);
        final HitboxImpl hitboxPlayer = new HitboxImpl(xPlayer, yPlayer, positionPlayer);
        final HitboxImpl hitbox = new HitboxImpl(xPlayer, yPlayer, positionEntity);
        final GameObject entity = new GameObject(positionEntity, new Vector2d(positionPlayer, positionEntity),
                hitbox);

        while (y >= -xPlayer) {
            entity.updateState(dt);
            entity.getHitbox().updateHitbox(entity.getCurrentPos());
            if (entity.getHitbox().checkCollision(hitboxPlayer)) {
                y--;
                entity.setPos(new Point2d(positionEntity.getX(), positionEntity.getY() + y));
                break;
            } else if (entity.getCurrentPos().getX() < 0) {
                fail("Entity is out of the screen");
                break;
            }
        }
    }

}
