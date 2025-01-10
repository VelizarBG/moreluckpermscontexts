package velizarbg.moreluckpermscontexts;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ContextManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import velizarbg.moreluckpermscontexts.contexts.HasPermissionLevelContext;

public class MoreLuckPermsContextsMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("moreluckpermscontexts");

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ContextManager contextManager = LuckPermsProvider.get().getContextManager();

			for (int i = 1; i <= 4; i++) {
				contextManager.registerCalculator(new HasPermissionLevelContext(i));
			}
		});
	}
}
