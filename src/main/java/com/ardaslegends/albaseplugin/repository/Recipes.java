package com.ardaslegends.albaseplugin.repository;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class Recipes {
    public Recipes() {
        Plugin plugin = AL_Base_Plugin.getPlugin();

        ShapedRecipe dropper = new ShapedRecipe(new ItemStack(Material.DROPPER));
        String[] dropperShape = {"ccc",
                "cgc",
                "ccc"};
        dropper.shape(dropperShape);
        dropper.setIngredient('c', Material.COBBLESTONE);
        dropper.setIngredient('r', Material.GOLD_INGOT);
        plugin.getServer().addRecipe(dropper);

        ShapedRecipe dispenser = new ShapedRecipe(new ItemStack(Material.DISPENSER));
        String[] dispenserShape = {"ccc",
                "cbc",
                "cgc"};
        dispenser.shape(dispenserShape);
        dispenser.setIngredient('c', Material.COBBLESTONE);
        dispenser.setIngredient('b', Material.BOW);
        dispenser.setIngredient('r', Material.GOLD_INGOT);
        plugin.getServer().addRecipe(dispenser);



        ShapedRecipe pouch = new ShapedRecipe(new ItemStack(Material.addMaterial(4097, false)));
        String[] pouchShape = {" s ",
                "l l",
                "lll"};
        pouch.shape(pouchShape);
        pouch.setIngredient('s', Material.STRING);
        pouch.setIngredient('l', Material.LEATHER);
        plugin.getServer().addRecipe(pouch);
    }
}
