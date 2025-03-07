package cam72cam.immersiverailroading.items.nbt;

import cam72cam.immersiverailroading.registry.EntityRollingStockDefinition;
import cam72cam.mod.item.ItemStack;

public class ItemTextureVariant {
	public static void set(ItemStack stack, String texture) {
		if (texture != null) {
			EntityRollingStockDefinition def = ItemDefinition.get(stack);
			if (def != null && def.textureNames.containsKey(texture)) {
				stack.getTagCompound().setString("texture_variant", texture);
				return;
			}
		}
		stack.getTagCompound().remove("texture_variant");
	}
	
	public static String get(ItemStack stack) {
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("texture_variant")) {
			EntityRollingStockDefinition def = ItemDefinition.get(stack);
			String texture = stack.getTagCompound().getString("texture_variant");
			if (texture != null && def != null && def.textureNames.containsKey(texture)) {
				return texture;
			}
		}
		return null;
	}
}
