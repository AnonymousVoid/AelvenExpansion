package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec2;

import javax.annotation.Nullable;

public class OasisFeature extends Feature<OasisConfiguration> {
    public OasisFeature(Codec<OasisConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OasisConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        RandomSource randomsource = context.random();
        BlockPos blockpos = context.origin();
        return this.placeFeature(worldgenlevel, randomsource, blockpos, context.config());
    }

    protected boolean placeFeature(LevelAccessor level, RandomSource rand, BlockPos pos, OasisConfiguration config) {
        BlockState ground = config.ground;
        BlockState underground = config.underground;
        BlockState plants = config.plants;
        BlockState rarePlants = config.rare_plants;
        BlockState log = config.log;
        BlockState leaves = config.leaves;
        BlockState hangingLeaves = config.hanging_leaves;
        BlockState boulder = config.boulder;

        IntProvider groundSize = config.ground_size;
        IntProvider treeCount = config.tree_count;
        IntProvider treeHeight = config.tree_height;
        IntProvider boulderCount = config.boulder_count;
        IntProvider boulderSize = config.boulder_size;

        int realGroundSize = groundSize.sample(rand);
        int realTreeCount = treeCount.sample(rand);
        int realBoulderCount = boulderCount.sample(rand);

        if (!level.isOutsideBuildHeight(pos)) {
            return placeOasis(level, pos, rand, ground, underground, plants, rarePlants, log, leaves, hangingLeaves,
                    boulder, realGroundSize, realTreeCount, treeHeight, realBoulderCount, boulderSize);
        }

        return false;
    }

    protected boolean placeOasis(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState groundState,
                                 BlockState undergroundState, BlockState plantsState,
                                 BlockState rarePlantsState, BlockState logState, BlockState leavesState,
                                 BlockState hangingLeavesState, BlockState boulderState, int groundSize, int treeCount,
                                 IntProvider treeHeight, int boulderCount, IntProvider boulderSize) {

        for (int x = -groundSize; x <= groundSize; x++) {
            for (int z = -groundSize; z <= groundSize; z++) {
                double a = Math.pow(Math.abs(x), 2);
                double b = Math.pow(Math.abs(z), 2);
                double distance = Math.sqrt(a + b);

                if (distance <= groundSize) {
                    for (int y = -groundSize; y <= groundSize / 2; y++) {
                        BlockPos blockPos = pos.offset(x, y, z);
                        if (!isAirOrWater(level, blockPos)) {
                            if (rand.nextInt(groundSize) + groundSize > distance * 2) {
                                this.setBlock(level, blockPos,
                                        isAirOrWater(level, blockPos.below()) ||
                                                !isAirOrWater(level, blockPos.above()) ? undergroundState : groundState);
                            }
                        } else if (plantsState != null) {
                            if (!level.getBlockState(blockPos).is(Blocks.WATER) &&
                                    rand.nextInt(groundSize) + groundSize > distance * 2) {
                                this.setBlock(level, blockPos, rand.nextInt(5) == 0 ? rarePlantsState : plantsState);
                            }
                            break;
                        }
                    }
                }
            }
        }

        for (int boulders = 0; boulders < boulderCount; boulders ++) {
            int sectionSize = (int) Math.floor(360 / boulderCount);
            double horizontal = (sectionSize * boulders + rand.nextInt(sectionSize)) * (Math.PI / 180);
            double length = ((rand.nextInt(180) * (Math.PI / 180)) + (rand.nextInt(180) * (Math.PI / 180))) / 2;

            double x = groundSize * Math.sin(length) * Math.sin(horizontal);
            double z = groundSize * Math.sin(length) * Math.cos(horizontal);

            placeBoulder(level, pos.offset(x, 10, z), rand, boulderState, boulderSize.sample(rand));
        }
        for (int trees = 0; trees < treeCount; trees ++) {
            int sectionSize = (int) Math.floor(360 / treeCount);
            double horizontal = (sectionSize * trees + rand.nextInt(sectionSize)) * (Math.PI / 180);
            double length = ((rand.nextInt(180) * (Math.PI / 180)) + (rand.nextInt(180) * (Math.PI / 180))) / 2;

            double x = groundSize * Math.sin(length) * Math.sin(horizontal);
            double z = groundSize * Math.sin(length) * Math.cos(horizontal);

            placeTree(level, pos.offset(x, 10, z), rand, logState, leavesState, hangingLeavesState, treeHeight.sample(rand));
        }

        return true;

    }

    protected boolean placeBoulder(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState boulderState, int size) {
        BlockPos boulderPos = pos;
        for (int i = 0; i < 20; i ++) {
            if (!isAirOrWater(level, boulderPos)) {
                for (int x = 0; x < size; x ++) {
                    for (int y = 0; y < size; y ++) {
                        for (int z = 0; z < size; z ++) {
                            int X = x - size/2;
                            int Y = y - size/2;
                            int Z = z - size/2;
                            if (Math.sqrt((X*X)+(Y*Y)+(Z*Z)) < size/2 - rand.nextInt(2)*0.75) {
                                this.setBlock(level, boulderPos.offset(X, Y, Z), boulderState);
                            }
                        }
                    }
                }
                return true;
            } else {
                boulderPos = boulderPos.below();
            }
        }
        return false;
    }

    protected boolean placeTree(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState logState,
                             BlockState leavesState, BlockState hangingLeavesState, int height) {
        BlockPos treePos = pos;

        for (int i = 0; i < 20; i ++) {
            if (isAirOrWater(level, treePos)) {
                treePos = treePos.below();
            } else {
                int rootsNE = rand.nextInt(height / 4);
                int rootsSE = rand.nextInt(height / 4);
                int rootsSW = rand.nextInt(height / 4);
                int rootsNW = rand.nextInt(height / 4);
                for (double j = -2; j <= height; j ++) {
                    if (j < rootsNE) this.setBlock(level, treePos.offset(1, j, 1), logState);
                    if (j < rootsSE) this.setBlock(level, treePos.offset(1, j, -1), logState);
                    if (j < rootsSW) this.setBlock(level, treePos.offset(-1, j, -1), logState);
                    if (j < rootsNW) this.setBlock(level, treePos.offset(-1, j, 1), logState);

                    this.setBlock(level, treePos.offset(0, j, 0), logState);
                }
                for (double j = -2; j <= height / (rand.nextInt(2) + 2) * 1.5; j ++) {
                    this.setBlock(level, treePos.offset(1, j, 0), logState);
                }
                for (double j = -2; j <= height / (rand.nextInt(2) + 2) * 1.5; j ++) {
                    this.setBlock(level, treePos.offset(0, j, 1), logState);
                }
                for (double j = -2; j <= height / (rand.nextInt(2) + 2) * 1.5; j ++) {
                    this.setBlock(level, treePos.offset(-1, j, 0), logState);
                }
                for (double j = -2; j <= height / (rand.nextInt(2) + 2) * 1.5; j ++) {
                    this.setBlock(level, treePos.offset(0, j, -1), logState);
                }

                float min = 0.2588F + (rand.nextInt(5) - 2) / 60;
                float mid = 0.7071F + (rand.nextInt(5) - 2) / 60;
                float max = 0.9659F + (rand.nextInt(5) - 2) / 60;
                placeBranches(level, treePos, rand, logState, leavesState, hangingLeavesState, height * 2 / 3,
                        new Vec2(min, max), new Vec2(mid, mid), new Vec2(max, min));
                placeBranches(level, treePos, rand, logState, leavesState, hangingLeavesState, height * 2 / 3,
                        new Vec2(min, -max), new Vec2(mid, -mid), new Vec2(max, -min));
                placeBranches(level, treePos, rand, logState, leavesState, hangingLeavesState, height * 2 / 3,
                        new Vec2(-min, -max), new Vec2(-mid, -mid), new Vec2(-max, -min));
                placeBranches(level, treePos, rand, logState, leavesState, hangingLeavesState, height * 2 / 3,
                        new Vec2(-min, max), new Vec2(-mid, mid), new Vec2(-max, min));

                return true;
            }
        }
        return false;
    }

    protected boolean placeBranches(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState logState,
                                BlockState leavesState, BlockState hangingLeavesState, int length, Vec2... offsets) {
        int branchHeight = length / 3;
        int branchCount = 0;
        boolean skippedBranch = false;

        for (Vec2 v : offsets) {
            branchCount ++;
            BlockPos branchPos = pos.offset(0, branchHeight, 0);
            branchHeight = (length/2) + (length / offsets.length * (branchCount + rand.nextInt(2)));
            if (rand.nextInt(5) == 0 && !skippedBranch) {
                break;
            }

            for (int i = 0; i < length; i ++) {
                BlockPos currentPos = branchPos.offset(v.x * i, i/2, v.y * i);
                this.setBlock(level, currentPos, logState);
                if (rand.nextInt(3) != 0) {
                    this.setBlock(level, currentPos.above(), leavesState);
                }
                if (rand.nextBoolean()) {
                    boolean direction = rand.nextBoolean();
                    int place = rand.nextInt(3) - 1;
                    BlockPos leavesPos = currentPos.offset(direction ? place : 0, 0, !direction ? place : 0);
                    for (int k = 0; k < rand.nextInt(length / 3) + length / 3; k++) {
                        BlockPos leafPos = leavesPos.offset(0, -k, 0);
                        if (!isNotBlocks(level, leafPos, Blocks.AIR, Blocks.WATER, leavesState.getBlock(), hangingLeavesState.getBlock())) {
                            this.setBlock(level, leafPos, leavesState);
                            if (!isNotBlocks(level, leafPos.below(), Blocks.AIR, Blocks.WATER, leavesState.getBlock())) {
                                this.setBlock(level, leafPos.below(), hangingLeavesState);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    protected double branchCurve(int x, int height) {
        return (Math.pow(-0.08 * x, 2) + (height / 40 * 5) * x);
    }

    protected boolean isAirOrWater(LevelAccessor level, BlockPos pos) {
        return !isNotBlocks(level, pos, Blocks.AIR, Blocks.WATER);
    }

    protected boolean isNotBlocks(LevelAccessor level, BlockPos pos, Block... blocks) {
        for (Block block : blocks) {
            if (level.getBlockState(pos).is(block)) {
                return false;
            }
        }
        return true;
    }

}
