package com.infinityraider.infinitylib.utility.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

interface IItemHandlerBridge extends IItemHandler {

    @Override
    default ItemStack getStackInSlot(int slot) {
        return getInventorySlot(slot);
    }

    ItemStack getInventorySlot(int slot);

}
