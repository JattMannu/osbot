import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@ScriptManifest(name = "RnJ", logo = "", version = 1.0, author = "JattMannu", info = "Romeo and Juliet")
public class RnJ extends Script {

    private final List<Position> PATH_TO_BERRY = Arrays.asList(
            new Position(3223, 3219, 0),
            new Position(3235, 3219, 0),
            new Position(3251, 3225, 0),
            new Position(3259, 3237, 0),
            new Position(3256, 3247, 0),
            new Position(3251, 3258, 0),
            new Position(3247, 3270, 0),
            new Position(3242, 3279, 0),
            new Position(3251, 3258, 0),
            new Position(3239, 3292, 0),
            new Position(3239, 3300, 0),
            new Position(3231, 3307, 0),
            new Position(3221, 3315, 0),
            new Position(3221, 3326, 0),
            new Position(3210, 3335, 0),
            new Position(3205, 3346, 0),
            new Position(3204, 3354, 0),
            new Position(3203, 3365, 0),
            new Position(3214, 3375, 0),
            new Position(3224, 3378, 0),
            new Position(3235, 3378, 0),
            new Position(3249, 3376, 0),
            new Position(3253, 3375, 0),
            new Position(3273, 3375, 0),
            new Position(3278, 3371, 0)
    );

    @Override
    public void onStart() throws InterruptedException {
        super.onStart(); //Does nothing at of the current API
    }

    public int onLoop() throws InterruptedException {
        getWalking().walkPath(PATH_TO_BERRY);
        sleep(2000);
        log("Trying to pic.");
        getObjects().closest("Cadava bush").interact("Pick-from");
        return 0;
    }
    private boolean isBusy() {
        //sleep(2000);
        return combat.isFighting() || getPlayers().myPlayer().isUnderAttack() || getPlayers().myPlayer().isAnimating() || getPlayers().myPlayer().isMoving();
    }


    private boolean cb(Runnable r) {
        while (isBusy()) {
            log("Char is busy");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        r.run();
        return true;
    }

}
