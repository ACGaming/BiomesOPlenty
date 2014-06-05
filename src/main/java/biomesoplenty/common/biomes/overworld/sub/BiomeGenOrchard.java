package biomesoplenty.common.biomes.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenOrchard extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenOrchard(int biomeID) 
	{
		super(biomeID);
		
        this.zoom = 0.25D;
		this.threshold = 0.25D;
		
		this.setHeight(biomeHeight);
        this.setColor(14024557);
        this.setTemperatureRainfall(0.8F, 0.4F);
        
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
		this.theBiomeDecorator.treesPerChunk = 2;
		
        this.bopWorldFeatures.setFeature("portobellosPerChunk", 2);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 3);
        this.bopWorldFeatures.setFeature("wildCarrotsPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 15);
		
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 20);
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 15);
        
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 0), 20);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 20);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(0, 3), 2);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
	}
	
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
    	return random.nextInt(3) == 0 ? new WorldGenOriginalTree(Blocks.log, BOPCBlocks.appleLeaves, 0, true) : this.worldGeneratorTrees;
    }

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 4, 2);
			}
		}
	}
	
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 14024557;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14024557;
    }
}