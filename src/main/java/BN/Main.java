package BN;

//-----imports-----//

import arc.Events;
import arc.util.CommandHandler;
import arc.util.Log;
import mindustry.Vars;
import mindustry.entities.type.Player;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.net.Administration;
import mindustry.plugin.Plugin;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.SplittableRandom;


public class Main extends Plugin {
    public static JSONObject data = new JSONObject();
    private boolean understood = false;

    public Main()  {
        if (!byteCode.hasDir("mind_db")) byteCode.mkdir("mind_db");
        if (!byteCode.has("badName")) {

            byteCode.make("badName", data);
        }
        data = byteCode.get("badName");

        Events.on(EventType.PlayerJoin.class, event -> {
            Player player = event.player;
            if (byteCode.has("badName")) {
                data = byteCode.get("badName");
            }
            if (data != null && !data.isEmpty()) {
                String name = player.name.toLowerCase().replace("3", "e").replace("1", "i").replace("6", "g").replace("!", "i").replace("$", "s").replace(" ", "").replace("@", "a").replace("4", "a").replace("0", "o");
                name = byteCode.noColors(name);
                for (String key : data.keySet()) {
                    if (name.contains(key)) {
                        player.getInfo().timesKicked--;
                        Call.onKick(player.con, "Invalid name\n" +
                                name.replace(key, "[scarlet]"+key+"[]"), 1);
                        Log.info("Kicked (" + player.uuid + " | " + byteCode.noColors(player.name) + ") for invalid name.");
                        return;
                    }
                }
            } else {
                Log.err("badName.cn is empty or malformed!");
            }
        });
    }
    public void registerServerCommands(CommandHandler handler){
        handler.register("bn", "[bad-word]", "lists all bad words blacklisted. use word after command to black list it.", arg -> {
            if (arg.length == 0) {
                if (byteCode.has("badName")) {
                    data = byteCode.get("badName");
                    if (data != null && !data.isEmpty()) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("Blacklisted words in name:\n");
                        for (String key : data.keySet()) {
                            builder.append(key+"\n");
                        }
                        Log.info(builder.toString());
                    }
                }
            } else {
                if (byteCode.has("badName")) {
                    byteCode.putStr("badName", arg[0], "");
                    Log.info("Done!");
                }
            }
        });
        handler.register("bn-remove", "<bad-word>", "removes word from badName blacklist.", arg -> {
            if (byteCode.has("badName")) {
                data = byteCode.get("badName");
                if (data.has(arg[0])) {
                    byteCode.remove("badName", arg[0]);
                    Log.info("Done!");
                } else {
                    Log.err("badName does not contain `" + arg[0] + "`!");
                }
            }

        });
        handler.register("bn-clear", "resets badName.cn to default", arg -> {
            if (!understood) {
                Log.warn("This command will reset badName.cn, use command again to reset.");
                understood = true;
                return;
            }
            data = new JSONObject();
            data.put("hitler", "");
            byteCode.save("badName", data);
            Log.info("Done!");
        });
    }
}
