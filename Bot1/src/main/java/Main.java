
import org.osbot.rs07.api.Combat;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.utility.ConditionalSleep2;

import javax.sql.rowset.Predicate;
import java.util.List;
import java.util.concurrent.Callable;

@ScriptManifest(name = "osbot1", logo = "", version =1.0 , author ="JattMannu" , info ="osbot1" )
public class Main extends Script {
    static int count = 0;

    @Override
    public void onStart() throws InterruptedException {
        super.onStart(); //Does nothing at of the current API
        log("On Start method has fired");
    }

    public int onLoop() throws InterruptedException {
        sleep(1000); //So that I do not get stuck in forever loop. :)
        if (combat.isFighting()|| getPlayers().myPlayer().isUnderAttack() || getPlayers().myPlayer().isAnimating() || getPlayers().myPlayer().isMoving() ) return 0;

        if( Math.random()  < 0.3333 ) {
            log("Pickup feathers");
            final GroundItem feather = getGroundItems().closest(314);
            ConditionalSleep2.sleep(6500, 500, new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    feather.interact("take");
                    return !getPlayers().myPlayer().isMoving();
                }
            });
            log("Loop : " + count++);
        }else if( Math.random() > 0.6666 ) {
            log("Pickup Bones");
            final GroundItem bone = getGroundItems().closest(526);
            ConditionalSleep2.sleep(6500, 500, new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    bone.interact("take");
                    return !getPlayers().myPlayer().isMoving();
                }
            });
            while(hasBones()) {
                for (Item item : inventory.getItems()) {
                    if (item != null && item.getId() == 526) {
                        item.interact("bury");
                        ConditionalSleep2.sleep(6500, 500, new Callable<Boolean>() {
                            public Boolean call() throws Exception {
                                bone.interact("take");
                                return !getPlayers().myPlayer().isAnimating();
                            }
                        });
                    }
                }
            }

            log("Loop : " + count++);
        }
        else {
            log("Attack Chicken!!!");
            final NPC Chicken = getNpcs().closest(new Filter<NPC>() {
                public boolean match(NPC npc) {
                    return npc.getName().equals("Chicken") && npc.isAttackable();
                    //return false;
                }
            });
            if (Chicken.interact("Attack")) {
                ConditionalSleep2.sleep(6500, 500, new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        return !combat.isFighting();
                    }
                });
            }
        }
        return 0;
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
        log("The user has exit");
    }

    private boolean isMoving(Position p) throws InterruptedException {
        while (!getPlayers().myPlayer().getPosition().equals(p)){
            sleep(500);
            log("from"+getPlayers().myPlayer().getPosition()+"isMoving to" + p);
        }
        return true;
    }

    private boolean hasBones() throws InterruptedException {

        for (Item item :inventory.getItems()) {

            if(item!=null && item.getId() == 526) {
                sleep(100);
                log("bones in bag!!!");
                return true;
            }
        }
        log("No bones in bag");
        return false;
    }

}
