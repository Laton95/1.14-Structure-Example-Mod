package com.laton95.structureexample;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = StructureExample.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(StructureExample.MOD_ID)
public class StructureRegistry {
	
	public static final Structure<NoFeatureConfig> LITTLE_HUT = null;
	
	public static IStructurePieceType LITTLE_HUT_PIECE;
	
	/**
	 * Register features
	 * Structures are a type of feature so they get registered here too
	 */
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		//Using the registry directly like this is bad, however this is currently the only way to register a structure piece
		//Specifically, this is to avoid the error that occurs in StructurePiece.write()
		//The piece's registry id is saved to nbt, and without the structure will not be saved properly and users will experience errors in the console whenever the chunk is loaded or unloaded
		LITTLE_HUT_PIECE = Registry.register(Registry.STRUCTURE_PIECE, StructureExample.MOD_ID + ":little_hut", LittleHutPiece::new);
		
		LittleHutStructure hut = new LittleHutStructure(NoFeatureConfig::deserialize);
		hut.setRegistryName(StructureExample.MOD_ID, "little_hut");
		event.getRegistry().register(hut);
	}
	
	/**
	 * A helper method to add structures to a biome
	 * It is important that each structure be added both as a feature and as a structure
	 *
	 * @param biome     the biome to add the structure to
	 * @param stage     the generation stage to spawn the structure
	 * @param structure the structure
	 */
	private static void addStructure(Biome biome, GenerationStage.Decoration stage, Structure structure) {
		biome.addFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
	}
	
	/**
	 * Add structures and features to biomes
	 * Should be called after the registry events have run, so that all biomes and features already exist
	 * Using categories like this is just one way of choosing which biome to place a feature in
	 */
	@SubscribeEvent
	public static void applyFeatures(FMLCommonSetupEvent event) {
		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
			switch(biome.getCategory()) {
				case NONE:
					break;
				case TAIGA:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case EXTREME_HILLS:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case JUNGLE:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case MESA:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case PLAINS:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case SAVANNA:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case ICY:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case THEEND:
					break;
				case BEACH:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case FOREST:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case OCEAN:
					break;
				case DESERT:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case RIVER:
					break;
				case SWAMP:
					break;
				case MUSHROOM:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LITTLE_HUT);
					break;
				case NETHER:
					break;
			}
		}
	}
}
