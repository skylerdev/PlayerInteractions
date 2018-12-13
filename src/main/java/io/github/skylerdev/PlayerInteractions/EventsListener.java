package io.github.skylerdev.PlayerInteractions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class EventsListener implements Listener {

    private FileConfiguration config;
    private PlayerInteractions plugin;

    public EventsListener(PlayerInteractions plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();

    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEntityEvent event) {
        Player sourcep = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        
        if ((clickedEntity instanceof Player) && event.getHand().equals(EquipmentSlot.HAND)) {
            Player destp = (Player) clickedEntity;
            if (destp.hasPermission("playerinteractions.interact")) {
                
                String command = config.getString("command");
                String senderString = config.getString("sender");
                
                command = command.replace("{clicker}", sourcep.getName());
                command = command.replace("{clicked}", destp.getName());

                
                // TODO: clicked bug? or just ! bug
                // TODO: timeout

                // TODO: test all cases

                CommandSender sender;
                if (senderString.equals("console")) {
                    sender = Bukkit.getConsoleSender();
                } else if (senderString.equals("clicker")) {
                    sender = (CommandSender) sourcep;
                } else if (senderString.equals("clicked")) {
                     sender = (CommandSender) destp;
                } else {
                    Bukkit.getConsoleSender()
                            .sendMessage("[PlayerInteractions] [ERROR]: Config value sender not defined properly");
                    return;
                }

                Bukkit.dispatchCommand(sender, command);

            }
        }

    }
    
    public void reload() {
        config = plugin.getConfig();
    }

}
