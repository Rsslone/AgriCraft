package com.infinityraider.agricraft.crafting;

import com.infinityraider.agricraft.reference.AgriNBT;
import com.infinityraider.agricraft.utility.CustomWoodType;
import infinityraider.infinitylib.Tags;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;

public class CustomWoodShapedRecipe extends ShapedRecipes {

    public CustomWoodShapedRecipe(CustomWoodType type, String name, NonNullList<Ingredient> ingredients, ItemStack result) {
        super("", 3, 3, ingredients, result);
        this.setRegistryName(Tags.MOD_ID, (type + "_" + name).replaceAll(":", "_"));
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = super.getCraftingResult(inv);
        // Vanilla Ingredient matching ignores NBT, so multiple wood-type recipes all match
        // the same input. Read the actual material tag from the first tagged input slot and
        // copy it onto the result so the correct wood type is preserved.
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.hasTagCompound() && stack.getTagCompound().hasKey(AgriNBT.MATERIAL)) {
                result.setTagCompound(stack.getTagCompound().copy());
                return result;
            }
        }
        return result;
    }

}
