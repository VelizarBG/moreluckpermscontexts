package velizarbg.moreluckpermscontexts.contexts;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class HasPermissionLevelContext implements ContextCalculator<ServerPlayerEntity> {
	public static final String HAS_PERMISSION_LEVEL_KEY = "has_permission_level_";
	private final int permissionLevel;
	private final String hasPermissionLevelKey;

	public HasPermissionLevelContext(int permissionLevel) {
		this.permissionLevel = permissionLevel;
		this.hasPermissionLevelKey = HAS_PERMISSION_LEVEL_KEY + permissionLevel;
	}

	@Override
	public void calculate(@NotNull ServerPlayerEntity target, @NotNull ContextConsumer consumer) {
		consumer.accept(hasPermissionLevelKey, target.hasPermissionLevel(permissionLevel) ? "true" : "false");
	}

	@Override
	public @NotNull ContextSet estimatePotentialContexts() {
		ImmutableContextSet.Builder builder = ImmutableContextSet.builder();

		builder.add(hasPermissionLevelKey, "true");
		builder.add(hasPermissionLevelKey, "false");

		return builder.build();
	}
}
