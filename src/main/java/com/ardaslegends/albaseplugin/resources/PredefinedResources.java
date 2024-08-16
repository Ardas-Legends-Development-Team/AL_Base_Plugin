package com.ardaslegends.albaseplugin.resources;

import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileResourceModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PredefinedResources {

    /**
     * This method is setting up the Files for the Resources
     */
    public static void setUpResources() {
        //Lumbercamp
        new File(SafeFileManager.getPredefinedResourceFolder(), "Lumber_Camp").mkdirs();
        for (SafefileResourceModel wood : setUpLumbercamp()) {
            SafeFileManager.safeResource(wood, "Lumber_Camp");
        }
        //Quarry
        new File(SafeFileManager.getPredefinedResourceFolder(), "Quarry").mkdirs();
        for (SafefileResourceModel stone : setUpQuarry()) {
            SafeFileManager.safeResource(stone, "Quarry");
        }
        //Farm
        new File(SafeFileManager.getPredefinedResourceFolder(), "Farm").mkdirs();
        for (SafefileResourceModel crop : setUpFarm()) {
            SafeFileManager.safeResource(crop, "Farm");
        }
        //Slaughterhouse
        new File(SafeFileManager.getPredefinedResourceFolder(), "Slaughterhouse").mkdirs();
        for (SafefileResourceModel animalDrop : setUpSlaughterhouse()) {
            SafeFileManager.safeResource(animalDrop, "Slaughterhouse");
        }
        //Orchard
        new File(SafeFileManager.getPredefinedResourceFolder(), "Orchard").mkdirs();
        for (SafefileResourceModel fruit : setUpOrchard()) {
            SafeFileManager.safeResource(fruit, "Orchard");
        }
        //Mine
        new File(SafeFileManager.getPredefinedResourceFolder(), "Mine").mkdirs();
        for (SafefileResourceModel ore : setUpMine()) {
            SafeFileManager.safeResource(ore, "Mine");
        }
        //Fishing Lodge
        new File(SafeFileManager.getPredefinedResourceFolder(), "Fishing_Lodge").mkdirs();
        for (SafefileResourceModel fish : setUpFisingLodge()) {
            SafeFileManager.safeResource(fish, "Fishing Lodge");
        }
        //Dye House
        new File(SafeFileManager.getPredefinedResourceFolder(), "Dye_House").mkdirs();
        for (SafefileResourceModel dye : setUpDyeHouse()) {
            SafeFileManager.safeResource(dye, "Dye House");
        }
        //House of Lore
        new File(SafeFileManager.getPredefinedResourceFolder(), "House_of_Lore").mkdirs();
        for (SafefileResourceModel scroll : setUpHouseOfLore()) {
            SafeFileManager.safeResource(scroll, "House of Lore");
        }
        //Claimbuild Taxes
        new File(SafeFileManager.getPredefinedResourceFolder(), "Income").mkdirs();
        for (SafefileResourceModel tax : setUpIncome()) {
            SafeFileManager.safeResource(tax, "Claimbuild Income");
        }
        //Pearl Fisher
        new File(SafeFileManager.getPredefinedResourceFolder(), "Pearl_Fisher").mkdirs();
    }

    /**
     * This method provides a List with the "default" Resources from a Lumbercamp
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpLumbercamp(){
        List<SafefileResourceModel> woods = new ArrayList<>();

        SafefileResourceModel larchWood = new SafefileResourceModel("Larch Wood", 468, 1, 1720, "None");
        woods.add(larchWood);
        SafefileResourceModel pearWood = new SafefileResourceModel("Pear Wood", 232, 1, 1720, "None");
        woods.add(pearWood);
        SafefileResourceModel charredWood = new SafefileResourceModel("Charred Wood", 178, 3, 1720, "None");
        woods.add(charredWood);
        SafefileResourceModel hollyWood = new SafefileResourceModel("Holly Wood", 427, 2, 1720, "None");
        woods.add(hollyWood);
        SafefileResourceModel shirePineWood = new SafefileResourceModel("Shire Pine Wood", 178, 0, 1720, "None");
        woods.add(shirePineWood);
        SafefileResourceModel mahoganyWood = new SafefileResourceModel("Mahogany Wood", 610, 0, 1720, "None");
        woods.add(mahoganyWood);
        SafefileResourceModel appleWood = new SafefileResourceModel("Apple Wood", 232, 0, 1720, "None");
        woods.add(appleWood);
        SafefileResourceModel kanukaWood = new SafefileResourceModel("Kanuka Wood", 900, 1, 1720, "None");
        woods.add(kanukaWood);
        SafefileResourceModel greenOakWood = new SafefileResourceModel("Green-Oak Wood", 745, 1, 1720, "None");
        woods.add(greenOakWood);
        SafefileResourceModel limeWood = new SafefileResourceModel("Lime Wood", 559, 3, 1720, "None");
        woods.add(limeWood);
        SafefileResourceModel firWood = new SafefileResourceModel("Fir Wood", 525, 3, 1720, "None");
        woods.add(firWood);
        SafefileResourceModel dragonbloodWood = new SafefileResourceModel("Dragonblood Wood", 900, 0, 1720, "None");
        woods.add(dragonbloodWood);
        SafefileResourceModel almondWood = new SafefileResourceModel("Almond Wood", 745, 3, 1720, "None");
        woods.add(almondWood);
        SafefileResourceModel baobabWood = new SafefileResourceModel("Baobab Wood", 525, 1, 1720, "None");
        woods.add(baobabWood);
        SafefileResourceModel orangeWood = new SafefileResourceModel("Orange Wood", 559, 2, 1720, "None");
        woods.add(orangeWood);
        SafefileResourceModel willowWood = new SafefileResourceModel("Willow Wood", 610, 1, 1720, "None");
        woods.add(willowWood);
        SafefileResourceModel acaciaWood = new SafefileResourceModel("Acacia Wood", 162, 0, 1720, "None");
        woods.add(acaciaWood);
        SafefileResourceModel mapleWood = new SafefileResourceModel("Maple Wood", 468, 0, 172, "None");
        woods.add(mapleWood);
        SafefileResourceModel oakWood = new SafefileResourceModel("Oak Wood", 17, 0, 1720, "None");
        woods.add(oakWood);
        SafefileResourceModel aspenWood = new SafefileResourceModel("Aspen Wood", 745, 0 , 1720, "None");
        woods.add(aspenWood);
        SafefileResourceModel datePalmWood = new SafefileResourceModel("Date Palm Wood", 468, 2, 1720, "None");
        woods.add(datePalmWood);
        SafefileResourceModel jungleWood = new SafefileResourceModel("Jungle Wood", 17, 3, 1720, "None");
        woods.add(jungleWood);
        SafefileResourceModel lairelosseWood = new SafefileResourceModel("Lairelosse Wood", 745, 2, 1720, "None");
        woods.add(lairelosseWood);
        SafefileResourceModel bananaWood = new SafefileResourceModel("Banana Wood", 427, 3, 1720, "None");
        woods.add(bananaWood);
        SafefileResourceModel cedarWood = new SafefileResourceModel("Cedar Wood", 525, 2, 1720, "None");
        woods.add(cedarWood);
        SafefileResourceModel chestnutWood = new SafefileResourceModel("Chestnut Wood", 525, 0, 1720, "None");
        woods.add(chestnutWood);
        SafefileResourceModel pineWood = new SafefileResourceModel("Pine Wood", 559, 0, 1720, "None");
        woods.add(pineWood);
        SafefileResourceModel lebethornWood = new SafefileResourceModel("Lebethron Wood", 427, 0, 1720, "None");
        woods.add(lebethornWood);
        SafefileResourceModel birchWood = new SafefileResourceModel("Birch Wood", 17, 2, 1720, "None");
        woods.add(birchWood);
        SafefileResourceModel oliveWood = new SafefileResourceModel("Olive Wood", 610, 3, 1720, "None");
        woods.add(oliveWood);
        SafefileResourceModel beechWood = new SafefileResourceModel("Beech Wood", 427, 1, 1720, "None");
        woods.add(beechWood);
        SafefileResourceModel mangoWood = new SafefileResourceModel("Mango Wood", 232, 3, 1720, "None");
        woods.add(mangoWood);
        SafefileResourceModel rottenWood = new SafefileResourceModel("Rotten Wood", 532, 0, 1720, "None");
        woods.add(rottenWood);
        SafefileResourceModel spruceWood = new SafefileResourceModel("Spruce Wood", 17, 1, 1720, "None");
        woods.add(spruceWood);
        SafefileResourceModel mirkOakWood = new SafefileResourceModel("Mirk-Oak Wood", 178, 2, 1720, "None");
        woods.add(mirkOakWood);
        SafefileResourceModel cherryWood = new SafefileResourceModel("Cherry Wood", 232, 2, 1720, "None");
        woods.add(cherryWood);
        SafefileResourceModel darkOakWood = new SafefileResourceModel("Dark-Oak Wood", 162, 1, 1720, "None");
        woods.add(darkOakWood);
        SafefileResourceModel lemonWood = new SafefileResourceModel("Lemon Wood", 559, 1, 1720, "None");
        woods.add(lemonWood);
        SafefileResourceModel mallornWood = new SafefileResourceModel("Mallorn Wood", 178, 1, 1720, "None");
        woods.add(mallornWood);
        SafefileResourceModel redwoodWood = new SafefileResourceModel("Redwood Wood", 762, 1, 1720, "None");
        woods.add(redwoodWood);

        return woods;
    }

    /**
     * This method provides a List with the "default" Resources from a Quarry
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpQuarry() {
        List<SafefileResourceModel> stones = new ArrayList<>();

        SafefileResourceModel chalk = new SafefileResourceModel("Chalk", 165, 5, 3456, "None");
        stones.add(chalk);
        SafefileResourceModel clay = new SafefileResourceModel("Clay", 82, 0, 3456, "None");
        stones.add(clay);
        SafefileResourceModel ice = new SafefileResourceModel("Ice", 79, 0, 3456, "None");
        stones.add(ice);
        SafefileResourceModel packedIce = new SafefileResourceModel("Packed Ice", 174, 0, 3456, "None");
        stones.add(packedIce);
        SafefileResourceModel luigon = new SafefileResourceModel("Luigon", 165, 3, 3456, "None");
        stones.add(luigon);
        SafefileResourceModel redSandstone = new SafefileResourceModel("Red Sandstone", 623, 0, 3456, "None");
        stones.add(redSandstone);
        SafefileResourceModel cargon = new SafefileResourceModel("Cargon", 165, 4, 3456, "None");
        stones.add(cargon);
        SafefileResourceModel whiteSandstone = new SafefileResourceModel("White Sandstone", 778, 0, 3456, "None");
        stones.add(whiteSandstone);
        SafefileResourceModel cobblestone = new SafefileResourceModel("Cobblestone", 4, 0, 3456, "None");
        stones.add(cobblestone);
        SafefileResourceModel redClay = new SafefileResourceModel("Red Clay", 961, 0, 3456, "None");
        stones.add(redClay);
        SafefileResourceModel rohanRock = new SafefileResourceModel("Rohan Rock", 165, 2, 3456, "None");
        stones.add(rohanRock);
        SafefileResourceModel snow = new SafefileResourceModel("Snow", 80, 0, 3456, "None");
        stones.add(snow);
        SafefileResourceModel mordorRock = new SafefileResourceModel("Mordor Rock", 165, 0, 3456, "None");
        stones.add(mordorRock);
        SafefileResourceModel jungleMud = new SafefileResourceModel("Jungle Mud", 616, 0, 3456, "None");
        stones.add(jungleMud);
        SafefileResourceModel gondorRock = new SafefileResourceModel("Gondor Rock", 165, 1, 3456, "None");
        stones.add(gondorRock);
        SafefileResourceModel sandstone = new SafefileResourceModel("Sandstone", 24, 0, 3456, "None");
        stones.add(sandstone);
        SafefileResourceModel hardenedClay = new SafefileResourceModel("Hardened Clay", 172, 0, 3456, "None");
        stones.add(hardenedClay);
        SafefileResourceModel sand = new SafefileResourceModel("Sand", 12, 0, 3456, "None");
        stones.add(sand);

        return stones;
    }

    /**
     * This method provides a List with the "default" Resources from a Farm
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpFarm() {
        List<SafefileResourceModel> crops = new ArrayList<>();

        SafefileResourceModel sugarcane = new SafefileResourceModel("Sugarcane", 338, 0, 576, "None");
        crops.add(sugarcane);
        SafefileResourceModel morgulShroom = new SafefileResourceModel("Morgul Shroom", 242, 0, 576, "None");
        crops.add(morgulShroom);
        SafefileResourceModel flax = new SafefileResourceModel("Flax", 4469, 0, 576, "None");
        crops.add(flax);
        SafefileResourceModel pipeWeed = new SafefileResourceModel("Pipe-Weed", 4140, 0, 576, "None");
        crops.add(pipeWeed);
        SafefileResourceModel reed = new SafefileResourceModel("Reed", 628, 0, 576, "None");
        crops.add(reed);
        SafefileResourceModel potato = new SafefileResourceModel("Potato", 392, 0, 576, "None");
        crops.add(potato);
        SafefileResourceModel melon = new SafefileResourceModel("Melon", 360, 0, 576, "None");
        crops.add(melon);
        SafefileResourceModel carrot = new SafefileResourceModel("Carrot", 391, 0, 576, "None");
        crops.add(carrot);
        SafefileResourceModel leek = new SafefileResourceModel("Leek", 4665, 0, 576, "None");
        crops.add(leek);
        SafefileResourceModel lettuce = new SafefileResourceModel("Lettuce", 4143, 0, 576, "None");
        crops.add(lettuce);
        SafefileResourceModel turnip = new SafefileResourceModel("Turnip", 4667, 0, 576, "None");
        crops.add(turnip);
        SafefileResourceModel yam = new SafefileResourceModel("Yam", 4804, 0, 576, "None");
        crops.add(yam);
        SafefileResourceModel corn = new SafefileResourceModel("Corn", 4638, 0, 576, "None");
        crops.add(corn);
        SafefileResourceModel blackberries = new SafefileResourceModel("Blackberries", 4471, 0, 576, "None");
        crops.add(blackberries);
        SafefileResourceModel wildberries = new SafefileResourceModel("Wildberries", 4727, 0, 576, "None");
        crops.add(wildberries);
        SafefileResourceModel blueberries = new SafefileResourceModel("Blueberries", 4470, 0, 576, "None");
        crops.add(blueberries);
        SafefileResourceModel elderberries = new SafefileResourceModel("Elderberries", 4474, 0, 576, "None");
        crops.add(elderberries);
        SafefileResourceModel cranberries = new SafefileResourceModel("Cranberries", 4473, 0, 576, "None");
        crops.add(cranberries);
        SafefileResourceModel greenGrapes = new SafefileResourceModel("Green Grapes", 4713, 0, 576, "None");
        crops.add(greenGrapes);
        SafefileResourceModel redGrapes = new SafefileResourceModel("Red Grapes", 4712, 0, 576, "None");
        crops.add(redGrapes);
        SafefileResourceModel raspberries = new SafefileResourceModel("Raspberries", 4472, 0, 576, "None");
        crops.add(raspberries);
        SafefileResourceModel wheat = new SafefileResourceModel("Wheat", 296, 0, 1728, "None");
        crops.add(wheat);

        return crops;
    }

    /**
     * This method provides a List with the "default" Resources from a Slaughterhouse
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpSlaughterhouse() {
        List<SafefileResourceModel> animalDrops = new ArrayList<>();

        SafefileResourceModel mutton = new SafefileResourceModel("Mutton", 4616, 0, 1728, "None");
        animalDrops.add(mutton);
        SafefileResourceModel chicken = new SafefileResourceModel("Chicken", 365, 0, 1728, "None");
        animalDrops.add(chicken);
        SafefileResourceModel pork = new SafefileResourceModel("Pork", 319, 0, 1728, "None");
        animalDrops.add(pork);
        SafefileResourceModel rabbit = new SafefileResourceModel("Rabbit", 4329, 0, 1728, "None");
        animalDrops.add(rabbit);
        SafefileResourceModel vension = new SafefileResourceModel("Vension", 4654, 0, 1728, "None");
        animalDrops.add(vension);
        SafefileResourceModel camel = new SafefileResourceModel("Camel", 4668, 0, 1728, "None");
        animalDrops.add(camel);
        SafefileResourceModel beef = new SafefileResourceModel("Beef", 363, 0, 1728, "None");
        animalDrops.add(beef);
        SafefileResourceModel rhino = new SafefileResourceModel("Rhino", 4372, 0, 1728, "None");
        animalDrops.add(rhino);
        SafefileResourceModel lion = new SafefileResourceModel("Lion", 4368, 0, 1728, "None");
        animalDrops.add(lion);
        SafefileResourceModel zebra = new SafefileResourceModel("Zebra", 4370, 0, 1728, "None");
        animalDrops.add(zebra);
        SafefileResourceModel manFlesh = new SafefileResourceModel("Man-flesh", 4819, 0, 1728, "None");
        animalDrops.add(manFlesh);
        SafefileResourceModel leather = new SafefileResourceModel("Leather", 334, 0, 1728, "None");
        animalDrops.add(leather);
        SafefileResourceModel gemsbockHide = new SafefileResourceModel("Gemsbock Hide", 4382, 0, 1728, "None");
        animalDrops.add(gemsbockHide);
        SafefileResourceModel wool = new SafefileResourceModel("Wool", 35, 0, 1728, "None");
        animalDrops.add(wool);
        SafefileResourceModel fur = new SafefileResourceModel("Fur", 4190, 0, 1728, "None");
        animalDrops.add(fur);

        return animalDrops;
    }

    /**
     * This method provides a List with the "default" Resources from a Orchard
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpOrchard() {
        List<SafefileResourceModel> fruits = new ArrayList<>();

        SafefileResourceModel orange = new SafefileResourceModel("Orange", 4575, 0, 576, "None");
        fruits.add(orange);
        SafefileResourceModel banana = new SafefileResourceModel("Banana", 4364, 0, 576, "None");
        fruits.add(banana);
        SafefileResourceModel mango = new SafefileResourceModel("Mango", 4362, 0, 576, "None");
        fruits.add(mango);
        SafefileResourceModel lemon = new SafefileResourceModel("Lemon", 4573, 0, 576, "None");
        fruits.add(lemon);
        SafefileResourceModel lime = new SafefileResourceModel("Lime", 4583, 0, 576, "None");
        fruits.add(lime);
        SafefileResourceModel pomegranate = new SafefileResourceModel("Pomegranate", 4814, 0, 576, "None");
        fruits.add(pomegranate);
        SafefileResourceModel pear = new SafefileResourceModel("Pear", 4206, 0, 576, "None");
        fruits.add(pear);
        SafefileResourceModel cherry = new SafefileResourceModel("Cherry", 4207, 0, 576, "None");
        fruits.add(cherry);
        SafefileResourceModel greenApple = new SafefileResourceModel("Green Apple", 4205, 0, 576, "None");
        fruits.add(greenApple);
        SafefileResourceModel olive = new SafefileResourceModel("Olive", 4688, 0, 576, "None");
        fruits.add(olive);
        SafefileResourceModel date = new SafefileResourceModel("Date", 4408, 0, 576, "None");
        fruits.add(date);
        SafefileResourceModel plum = new SafefileResourceModel("Plum", 4729, 0, 576, "None");
        fruits.add(plum);
        SafefileResourceModel redApple = new SafefileResourceModel("Red Apple", 260, 0, 576, "None");
        fruits.add(redApple);
        SafefileResourceModel cocoa = new SafefileResourceModel("Cocoa", 351, 0, 576, "None");
        fruits.add(cocoa);
        SafefileResourceModel chestnut = new SafefileResourceModel("Chestnut", 4475, 0, 576, "None");
        fruits.add(chestnut);
        SafefileResourceModel mallornNut = new SafefileResourceModel("Mallorn Nut", 4283, 0, 576, "None");
        fruits.add(mallornNut);
        SafefileResourceModel almond = new SafefileResourceModel("Almond", 4726, 0, 576, "None");
        fruits.add(almond);

        return fruits;
    }

    /**
     * This method provides a List with the "default" Resources from a Mine
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpMine() {
        List<SafefileResourceModel> ores = new ArrayList<>();

        SafefileResourceModel coal = new SafefileResourceModel("Coal", 173, 0, 160, "None");
        ores.add(coal);
        SafefileResourceModel lapisLazuli = new SafefileResourceModel("Lapis Lazuli", 22, 0, 64, "None");
        ores.add(lapisLazuli);
        SafefileResourceModel obsidian = new SafefileResourceModel("Obsidian", 49, 0, 64, "None");
        ores.add(obsidian);
        SafefileResourceModel gold = new SafefileResourceModel("Gold", 14, 0, 64, "None");
        ores.add(gold);
        SafefileResourceModel silver = new SafefileResourceModel("Silver", 168, 0, 128, "None");
        ores.add(silver);
        SafefileResourceModel mithril = new SafefileResourceModel("Mithril", 169, 0, 4, "None");
        ores.add(mithril);
        SafefileResourceModel diamond = new SafefileResourceModel("Diamond", 4853, 0, 64, "None");
        ores.add(diamond);
        SafefileResourceModel topaz = new SafefileResourceModel("Topaz", 4848, 0, 128, "None");
        ores.add(topaz);
        SafefileResourceModel salt = new SafefileResourceModel("Salt", 831, 0, 320, "None");
        ores.add(salt);
        SafefileResourceModel amethyst = new SafefileResourceModel("Amethyst", 4849, 0, 128, "None");
        ores.add(amethyst);
        SafefileResourceModel ruby = new SafefileResourceModel("Ruby", 4851, 0, 128, "None");
        ores.add(ruby);
        SafefileResourceModel sapphire = new SafefileResourceModel("Sapphire", 4850, 0, 128, "None");
        ores.add(sapphire);
        SafefileResourceModel tin = new SafefileResourceModel("Tin", 167, 0, 320, "None");
        ores.add(tin);
        SafefileResourceModel copper = new SafefileResourceModel("Copper", 166, 0, 320, "None");
        ores.add(copper);
        SafefileResourceModel durnor = new SafefileResourceModel("Durnor", 190, 0, 128, "None");
        ores.add(durnor);
        SafefileResourceModel morgulIronMordor = new SafefileResourceModel("Morgul Iron (Mordor)", 191, 1, 320, "None");
        ores.add(morgulIronMordor);
        SafefileResourceModel morgulIron = new SafefileResourceModel("Morgul Iron", 191, 0, 320, "None");
        ores.add(morgulIron);
        SafefileResourceModel guldurilMordor = new SafefileResourceModel("Gulduril (Mordor)", 412, 1, 128, "None");
        ores.add(guldurilMordor);
        SafefileResourceModel gulduril = new SafefileResourceModel("Gulduril", 412, 0, 128, "None");
        ores.add(gulduril);
        SafefileResourceModel edhelvir = new SafefileResourceModel("Edhelvir", 218, 0, 128, "None");
        ores.add(edhelvir);
        SafefileResourceModel iron = new SafefileResourceModel("Iron", 15, 0, 320, "None");
        ores.add(iron);
        SafefileResourceModel glowstone = new SafefileResourceModel("Glowstone", 231, 0, 128, "None");
        ores.add(glowstone);
        SafefileResourceModel emerald = new SafefileResourceModel("Emerald", 4858, 0, 128, "None");
        ores.add(emerald);

        return ores;
    }

    /**
     * This method provides a List with the "default" Resources from a Fishing Lodge
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpFisingLodge() {
        List<SafefileResourceModel> fish = new ArrayList<>();
        //ToDo: Define the Resources and add them to the List
        return fish;
    }

    /**
     * This method provides a List with the "default" Resources from a Dye House
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpDyeHouse() {
        List<SafefileResourceModel> dies = new ArrayList<>();
        //ToDo: Define the Resources and add them to the List
        return dies;
    }

    /**
     * This method provides a List with the "default" Resources from a House of Lore
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpHouseOfLore() {
        List<SafefileResourceModel> scrolls = new ArrayList<>();
        //ToDo: Define the Resources and add them to the List
        return scrolls;
    }

    /**
     * This method provides a List with the "default" Taxes
     * @return A list of SafefileResourceModels with the default resources
     */
    private static List<SafefileResourceModel> setUpIncome() {
        List<SafefileResourceModel> income = new ArrayList<>();
        //ToDo: Define the Resources and add them to the List
        return income;
    }

    private static List<SafefileResourceModel> setUpPearlFisher() {
        List<SafefileResourceModel> pearls = new ArrayList<>();
        //ToDo: Define the Resources and add them to the List
        return pearls;
    }
}
