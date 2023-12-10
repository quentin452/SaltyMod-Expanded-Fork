package darkbum.saltymod.world.biome;

import java.util.Random;

import darkbum.saltymod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NewWorldGenClay extends WorldGenerator {
    private final Block clay;
    private final int numberOfBlocks;

    public NewWorldGenClay(int number) {
        this.clay = ModBlocks.mineral_mud;
        this.numberOfBlocks = number;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial() != Material.water) {
            return false;
        } else {
            int radius = random.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;
            int radiusSquared = radius * radius;

            int startX = x - radius;
            int endX = x + radius;
            int startZ = z - radius;
            int endZ = z + radius;

            for (int posX = startX; posX <= endX; ++posX) {
                for (int posZ = startZ; posZ <= endZ; ++posZ) {
                    int deltaX = posX - x;
                    int deltaZ = posZ - z;

                    if (deltaX * deltaX + deltaZ * deltaZ <= radiusSquared) {
                        int startY = y + b0;
                        int endY = y - b0;

                        for (int posY = startY; posY >= endY; --posY) {
                            if (posY < 0 || posY > 255) {
                                break;
                            }

                            Block block = world.getBlock(x, posY, z);
                            if (block instanceof BlockDirt || block == Blocks.clay || block == ModBlocks.salt_lake_dirt || block == ModBlocks.salt_dirt) {
                                world.setBlock(x, posY, z, this.clay, 0, 2);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
