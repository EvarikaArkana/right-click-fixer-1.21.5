package eva.rclickfix;

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

        LOGGER.info("imma do a fish\n\n\n\n\nblub blub");
    }
}