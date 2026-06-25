package com.eutiers.tagger;

import com.eutiers.tagger.command.EuTiersCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EuTiersClient implements ClientModInitializer {

    public static final String MOD_ID = "eutiers";
    public static final Logger LOGGER = LoggerFactory.getLogger("EuTiersTagger");

    /** Loaded once at startup; read by the mixins and the command. */
    public static TierConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = TierConfig.load();
        TierManager.INSTANCE.start(CONFIG);

        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> EuTiersCommand.register(dispatcher));

        LOGGER.info("EU Tiers Tagger ready. Tier endpoint base = {}", CONFIG.baseUrl);
    }
}
