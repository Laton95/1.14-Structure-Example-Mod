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
	 * Structures should be added as features to any biome that they might overlap into, to avoid generation being cut off at chunk edges
	 * Structures can be added as structures to any biome they should start in
	 */
	private static void addOverworldStructures(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	}
	
	/**
	 * Add structures and features to biomes
	 * Should be called after the registry events have run, so that all biomes and features already exist
	 * Using categories like this is just one way of choosing which biome to place a feature in
	 * It is important that each structure be added both as a feature and as a structure
	 */
	@SubscribeEvent
	public static void applyFeatures(FMLCommonSetupEvent event) {
		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
			switch(biome.getCategory()) {
				case NONE:
					addOverworldStructures(biome);
					break;
				case TAIGA:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case EXTREME_HILLS:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case JUNGLE:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case MESA:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case PLAINS:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case SAVANNA:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case ICY:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case THEEND:
					break;
				case BEACH:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case FOREST:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case OCEAN:
					addOverworldStructures(biome);
					break;
				case DESERT:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case RIVER:
					addOverworldStructures(biome);
					break;
				case SWAMP:
					addOverworldStructures(biome);
					break;
				case MUSHROOM:
					biome.addStructure(LITTLE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
					addOverworldStructures(biome);
					break;
				case NETHER:
					break;
			}
		}
	}
}
