package wtf.gameplay;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockStem;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import squeek.applecore.api.plants.PlantGrowthEvent;
import wtf.config.GameplayConfig;

public class AppleCoreEvents {

	static HashMap<Block, Type> plantmods = new HashMap<Block, Type>();
	
	static Random random = new Random();
	
	public static void initGrowthMap(){
		plantmods.put(Blocks.BEETROOTS, Type.COLD);
		plantmods.put(Blocks.CACTUS, Type.DRY);
		plantmods.put(Blocks.CARROTS, Type.HILLS);
		plantmods.put(Blocks.CACTUS, Type.DRY);
		plantmods.put(Blocks.COCOA, Type.JUNGLE);
		plantmods.put(Blocks.MELON_STEM, Type.LUSH);
		plantmods.put(Blocks.BROWN_MUSHROOM, Type.SWAMP);
		plantmods.put(Blocks.RED_MUSHROOM, Type.CONIFEROUS);
		plantmods.put(Blocks.NETHER_WART, Type.NETHER);
		
		plantmods.put(Blocks.POTATOES, Type.PLAINS);
		plantmods.put(Blocks.PUMPKIN_STEM, Type.FOREST);
		plantmods.put(Blocks.REEDS, Type.RIVER);
		plantmods.put(Blocks.WHEAT, Type.SAVANNA);
		
		
		
	}
	
	@SubscribeEvent
	public void growthTickAllowed(PlantGrowthEvent.AllowGrowthTick event)
	{	
		//System.out.println(event.block.getLocalizedName());
		double growthPercent = GameplayConfig.appleCoreConstant*2;
		float chance = random.nextFloat();
		if (chance > growthPercent){
			event.setResult(Result.DENY);
			return;
		}
		
		Type type = plantmods.get(event.block);
		if (type != null){
			Biome biome = event.world.getBiomeGenForCoords(event.pos);
			if (BiomeDictionary.isBiomeOfType(biome, type)){
				if (chance > GameplayConfig.appleCoreConstant){
					event.setResult(Result.DENY);
					return;
				}
			}
		}
		event.setResult(Result.ALLOW);
	}

}
