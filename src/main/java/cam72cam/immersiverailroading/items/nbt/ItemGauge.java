package cam72cam.immersiverailroading.items.nbt;

import cam72cam.immersiverailroading.library.Gauge;
import cam72cam.immersiverailroading.registry.EntityRollingStockDefinition;
import cam72cam.mod.item.ItemStack;

public class ItemGauge {	
	public static void set(ItemStack stack, Gauge gauge) {
		stack.getTagCompound().setDouble("gauge", gauge.value());
	}

	public static boolean has(ItemStack stack) {
		return stack.getTagCompound() != null && stack.getTagCompound().hasKey("gauge");
	}
	
	public static Gauge get(ItemStack stack) {
		if (has(stack)){
			return Gauge.from(stack.getTagCompound().getDouble("gauge"));
		}
		
		EntityRollingStockDefinition def = ItemDefinition.get(stack.copy());
		if (def != null) {
			return def.recommended_gauge;
		}
		return Gauge.from(Gauge.STANDARD);
	}
}
