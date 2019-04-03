import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.function.Consumer;

@ScriptManifest(name = "Alkharid", logo = "", version =1.0 , author ="JattMannu" , info ="Plays like a player" )
public class Alkharid extends Script {
    public int onLoop() throws InterruptedException {
        sleep(5000);
        getGroundItems().getAll().stream().forEach(new Consumer<GroundItem>() {
            public void accept(GroundItem groundItem) {
                log(groundItem.toString());
            }
        });
        getObjects().getAll().stream().forEach(new Consumer<RS2Object>() {
            public void accept(RS2Object rs2Object) {
                log(rs2Object.toString());
            }
        });

        log("##############################");
        return 0;
    }
}
