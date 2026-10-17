package velizarbg.moreluckpermscontexts.contexts;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import org.jetbrains.annotations.NotNull;

public class HasPermissionLevelContext implements ContextCalculator<ServerPlayer> {
	public static final String HAS_PERMISSION_LEVEL_KEY = "has_permission_level_";
	private final int permissionLevel;
	private final String hasPermissionLevelKey;

	public HasPermissionLevelContext(int permissionLevel) {
		this.permissionLevel = permissionLevel;
		this.hasPermissionLevelKey = HAS_PERMISSION_LEVEL_KEY + permissionLevel;
	}

	@Override
	public void calculate(@NotNull ServerPlayer target, @NotNull ContextConsumer consumer) {
		consumer.accept(hasPermissionLevelKey, Boolean.toString(
			target.permissions().hasPermission(new Permission.HasCommandLevel(PermissionLevel.byId(permissionLevel)))));
	}

	@Override
	public @NotNull ContextSet estimatePotentialContexts() {
		ImmutableContextSet.Builder builder = ImmutableContextSet.builder();

		builder.add(hasPermissionLevelKey, "true");
		builder.add(hasPermissionLevelKey, "false");

		return builder.build();
	}
}
