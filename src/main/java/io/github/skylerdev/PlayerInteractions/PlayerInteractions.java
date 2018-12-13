package io.github.skylerdev.PlayerInteractions;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerInteractions extends JavaPlugin {
    
    public static final Logger LOGGER = Logger.getLogger("McWiki");
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        
        LOGGER.log(Level.INFO, "[PlayerInteractions] Loaded " + toString() + " successfully.");
        
        getServer().getPluginManager().registerEvents(new EventsListener(this), this);

    }
    
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("playerinteractions")) {
            if (args.length == 0) {
                displayHelp(sender);
            } else {
                if (args[0].equals("reload")) {
                    sender.sendMessage("§aReloaded PlayerInteractions config.");
                    reload();
                    return true;
                }
                if (args[0].equals("help")) {
                    displayHelp(sender);
                }
            }

            return true;
        }
        
        return true;
    }
    
    private void reload() {
        getServer().getPluginManager().registerEvents(new EventsListener(this), this);
    }
    
    public void displayHelp(CommandSender sender) {
        sender.sendMessage("§d" + this.toString());
        sender.sendMessage("§7By skylerdev");
        sender.sendMessage("§f/playerinteractions <help/reload>");
    }
    
    
    @Override
    public void onDisable() {

    }

}
