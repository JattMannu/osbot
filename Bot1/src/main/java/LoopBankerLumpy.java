import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.*;
import java.util.stream.Stream;

@ScriptManifest(name = "LoopBankerLumpy", logo = "", version = 1.0, author = "JattMannu", info = "Walks in loop and trade with people")
public class LoopBankerLumpy extends Script {

    private final List<Position> LUMPY_TO_FISH = Arrays.asList(
            new Position(3205, 3208, 0),
            new Position(3205, 3210, 0),
            new Position(3215, 3210, 0),
            new Position(3215, 3218, 0),
            new Position(3223, 3218, 0),
            new Position(3230, 3218, 0),
            new Position(3235, 3218, 0),
            new Position(3235, 3209, 0),
            new Position(3235, 3201, 0),
            new Position(3244, 3201, 0),
            new Position(3244, 3190, 0),
            new Position(3243, 3177, 0),
            new Position(3243, 3167, 0),
            new Position(3243, 3157, 0),
            new Position(3243, 3152, 0),
            new Position(3243, 3150, 0)
    );

    private final List<Position> LOOP = Arrays.asList(
            new Position(3243, 3150, 0),
            new Position(3242, 3150, 0),
            new Position(3242, 3151, 0),
            new Position(3243, 3151, 0),
            new Position(3243, 3150, 0)
    );

    private final List<Position> SECOND_FLOOR_BANKING = Arrays.asList(
            new Position(3205, 3209, 0),
            new Position(3205, 3217, 0),
            new Position(3209, 3219, 0)
    );
    @Override
    public void onStart() throws InterruptedException {
        super.onStart(); //Does nothing at of the current API
    }

    public int onLoop() throws InterruptedException {

        //Collections.reverse(LUMPY_TO_FISH);
        //getWalking().walkPath(LUMPY_TO_FISH);
        sleep(2000);
        //if (LUMPY_TO_FISH.get(0).equals(new Position(3205, 3208, 0))){

        log("LoopBankerLumpy: BANKING");
        while (!getPlayers().myPlayer().getPosition().equals(new Position(3205, 3208, 0))) {
            log("Walking bank");
            getWalking().webWalk(new Position(3205, 3208, 0));
            sleep(1000);
        }

        while (!getBank().isOpen()){
            getWalking().webWalk(closestTo(myPlayer()));
            getBank().open();
            sleep(5000);
        }

        while (!getInventory().isEmpty()) {
            getBank().depositAll();
            sleep(5000);
        }


        while (getBank().isOpen()){
            getBank().close();
            sleep(5000);
        }

        getWalking().webWalk(new Position(3243, 3150, 0));


//            getObjects().closest(16671).interact("Climb-up");
//            sleep(2000);
//            getObjects().closest(16672).interact("Climb-up");
//            sleep(2000);
//            getWalking().walkPath(SECOND_FLOOR_BANKING);
            //getBank().open();
            //getBank().open();
        //}
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

    private enum Bank {

        DRAYNOR(Banks.DRAYNOR),

        AL_KHARID(Banks.AL_KHARID),

        LUMBRIDGE(Banks.LUMBRIDGE_UPPER),

        FALADOR_EAST(Banks.FALADOR_EAST),

        FALADOR_WEST(Banks.FALADOR_WEST),

        VARROCK_EAST(Banks.FALADOR_EAST),

        VARROCK_WEST(Banks.VARROCK_WEST),

        SEERS(Banks.CAMELOT),

        CATHERBY(Banks.CATHERBY),

        EDGEVILLE(Banks.EDGEVILLE),

        YANILLE(Banks.YANILLE),

        GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD),

        ARDOUNGE_NORTH(Banks.ARDOUGNE_NORTH),

        ARDOUNE_SOUTH(Banks.ARDOUGNE_SOUTH),

        CASTLE_WARS(Banks.CASTLE_WARS),

        DUEL_ARENA(Banks.DUEL_ARENA),

        PEST_CONTROL(Banks.PEST_CONTROL),

        CANIFIS(Banks.CANIFIS),

        BLAST_FURNACE(new Area(1949, 4956, 1947, 4958)),

        TZHAAR(Banks.TZHAAR);

        private final Area area;

        Bank(Area area) {

            this.area = area;

        }

    }

    public static Area closestTo(Entity e) {

        HashMap<Bank, Integer> distMap = new HashMap<Bank, Integer>();

        for (Bank b : Bank.values()) {

            distMap.put(b, e.getPosition().distance(b.area.getRandomPosition()));

        }

        HashMap<Integer, Bank> distMapSorted = sortByDistance(distMap);

        Area cBank = distMapSorted.values().toArray(new Bank[Bank.values().length])[0].area;

        return cBank;

    }

    private static <K, V extends Comparable<? super V>> HashMap<V, K> sortByDistance(Map<K, V> map) {

        HashMap<V, K> result = new LinkedHashMap<>();

        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> result.put(e.getValue(), e.getKey()));

        return result;

    }

}
