package cam72cam.immersiverailroading.gui.container;

import cam72cam.immersiverailroading.entity.LocomotiveSteam;
import cam72cam.mod.gui.container.IContainer;
import cam72cam.mod.gui.container.IContainerBuilder;
import cam72cam.mod.item.Fuzzy;
import cam72cam.mod.item.ItemStack;

public class SteamLocomotiveContainer implements IContainer {
    public final LocomotiveSteam stock;
    private final ItemStack template;

    public SteamLocomotiveContainer(LocomotiveSteam stock) {
        this.stock = stock;
        this.template = Fuzzy.BUCKET.example();
    }

    public void draw(IContainerBuilder container){
        int currY = 0;
        int horizSlots = stock.getInventoryWidth();
        int inventoryRows = (int) Math.ceil(((double)stock.getInventorySize()-2) / horizSlots);
        int slotY = 0;

        currY = container.drawTopBar(0, currY, horizSlots * 2);

        int tankY = currY;
        for (int i = 0; i < inventoryRows; i++) {
            currY = container.drawSlotRow(null, 0, horizSlots * 2, 0, currY);
            if (i == 0) {
                slotY = currY;
            }
        }

        container.drawTankBlock(0, tankY, horizSlots * 2, inventoryRows, stock.getLiquid(), stock.getLiquidAmount() / (float)stock.getTankCapacity().MilliBuckets());

        currY = container.drawBottomBar(0, currY, horizSlots*2);

        currY = container.drawSlotBlock(stock.cargoItems, 2, stock.getInventoryWidth(), 0, currY);

        container.drawSlotOverlay(template, 1, slotY);
        container.drawSlot(stock.cargoItems, 0, 1, slotY);
        container.drawSlot(stock.cargoItems, 1,  1 + horizSlots * 2 * 16, slotY);

        String quantityStr = String.format("%s/%s", stock.getLiquidAmount(), stock.getTankCapacity().MilliBuckets());
        container.drawCenteredString(quantityStr, 0, slotY);

        currY = container.drawPlayerInventoryConnector(0, currY, horizSlots);
        currY = container.drawPlayerInventory(currY, horizSlots*2);
    }

    @Override
    public int getSlotsX() {
        return stock.getInventoryWidth() * 2;
    }

    @Override
    public int getSlotsY() {
        return stock.getInventorySize() / stock.getInventoryWidth();
    }
}
