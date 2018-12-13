package io.github.skylerdev.PlayerInteractions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventsListener implements Listener {

    private FileConfiguration config;

    private String senderString;
    private String command;

    public EventsListener(PlayerInteractions plugin) {
        config = plugin.getConfig();

        senderString = config.getString("sender");
        command = config.getString("command");
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEntityEvent event) {
        Player sourcep = event.getPlayer();
        org.bukkit.entity.Entity entity = event.getRightClicked();

        if (entity instanceof Player) {
            Player destp = (Player) entity;

            command.replace("{clicker}", destp.toString());
            command.replace("{clicked}", sourcep.toString());
            //TODO: clicked bug? or just ! bug
            //TODO: timeout 
            
            //TODO: test all cases
            

            CommandSender sender;
            if (senderString.equals("console")) {
                sender = Bukkit.getConsoleSender();
            } else if (senderString.equals("clicker")) {
                sender = (CommandSender) sourcep;
            } else if (senderString.equals("clicked")) {
                sender = (CommandSender) destp;
            } else {
                Bukkit.getConsoleSender().sendMessage("[PlayerInteractions] [ERROR]: Config value sender not defined properly");
                return;
            }

            Bukkit.dispatchCommand(sender, command);

        }

    }

}
