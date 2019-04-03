import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.function.Consumer;

@ScriptManifest(name = "Tutorial_island", logo = "", version = 1.0, author = "JattMannu", info = "Automate Tutorial Island")
public class Tutorial_island extends Script {

    @Override
    public void onStart() throws InterruptedException {
        super.onStart(); //Does nothing at of the current API
        log("On Start method has fired");

        log("Trying to talk to guide");
        cb(() -> getNpcs().getAll().stream().filter(npc -> npc.getId() == 3308).findFirst().get().interact("Talk-to"));
        log("Trying to continue");
        cb(() -> dialogues.selectOption("Click here to continue"));

        log("Ended");
    }

    public int onLoop() throws InterruptedException {
        //this.stop();
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
