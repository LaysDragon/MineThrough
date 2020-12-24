package com.tommysource.minethrough;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

enum StunProtocol {
    UDP, TCP
}


@Config(modid = MineThrough.MODID)
public class ModConfig {


    @Config.Name("Stun Protocol")
    public static StunProtocol stunProtocol = StunProtocol.UDP;

    @Config.Name("Stun Server")
    public static Server stunServer = new Server("stun1.l.google.com", 19305);

    static class Server {

        @Config.Name("Host")
        public String host;

        @Config.Name("Port")
        public int port;

        public Server(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }


    @Mod.EventBusSubscriber(modid = MineThrough.MODID)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MineThrough.MODID)) {
                ConfigManager.sync(MineThrough.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
