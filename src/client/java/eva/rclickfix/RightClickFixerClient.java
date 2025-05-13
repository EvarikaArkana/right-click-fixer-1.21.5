package eva.rclickfix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ClientModInitializer;

public class RightClickFixerClient implements ClientModInitializer {
    public static final String MOD_ID = "right-click-fixer-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        LOGGER.info("Hoi! im eva");
        try {
            wait(332);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("imma do a fish\nblub blub");
    }
}