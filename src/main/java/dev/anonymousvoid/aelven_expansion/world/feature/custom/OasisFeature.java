package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;
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
            if (level.getBlockState(treePos).is(Blocks.AIR)) {
                treePos = treePos.below();
            } else if (level.getFluidState(treePos).is(Fluids.EMPTY)) {
                // ROOTS
                BlockPos rootX = treePos.relative(Direction.fromAxisAndDirection(Direction.Axis.X,
                        rand.nextBoolean() ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE));
                BlockPos rootZ = treePos.relative(Direction.fromAxisAndDirection(Direction.Axis.Z,
                        rand.nextBoolean() ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE));
                if (rand.nextBoolean()) {
                    rootX = rootX.relative(Direction.fromAxisAndDirection(Direction.Axis.Z,
                            rand.nextBoolean() ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE))
                            .offset(0, -1, 0);
                }
                if (rand.nextBoolean()) {
                    rootZ = rootZ.relative(Direction.fromAxisAndDirection(Direction.Axis.X,
                            rand.nextBoolean() ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE))
                            .offset(0, -1, 0);
                }
                for (double j = 1; j >= -rand.nextInt(3) - 1; j --) {
                    this.setBlock(level, rootX.offset(0, j, 0), logState);
                    this.setBlock(level, rootZ.offset(0, j, 0), logState);
                }

                // TRUNK WITH BRANCHES
                Direction branchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                System.out.println(branchDirection.getName());
                for (int j = 0; j <= height; j ++) {
                    if (j != height) {
                        this.setBlock(level, treePos.offset(0, j, 0), logState);

                        if (j > height / 3 && j < height * 0.8) {
                            BlockPos branchPos = treePos.relative(branchDirection).offset(0, j, 0);
                            BlockState branchState = logState;
                            if (logState.hasProperty(BlockStateProperties.AXIS)) {
                                branchState = logState.setValue(BlockStateProperties.AXIS, branchDirection.getAxis());
                            }
                            if (rand.nextInt(3) != 0) {
                                placeBranch(level, branchPos, rand, branchState, leavesState, hangingLeavesState, branchDirection, rand.nextInt(2) + 2);
                                branchDirection = branchDirection.getClockWise(Direction.Axis.Y);
                                if (rand.nextInt(4) == 0) {
                                    branchDirection = branchDirection.getClockWise(Direction.Axis.Y);
                                }
                            } else if (rand.nextBoolean()) {
                                placeBranch(level, branchPos, rand, leavesState, null, hangingLeavesState, branchDirection, rand.nextInt(2) + 1);
                                branchDirection = branchDirection.getClockWise(Direction.Axis.Y);
                                if (rand.nextInt(4) == 0) {
                                    branchDirection = branchDirection.getClockWise(Direction.Axis.Y);
                                }
                            }
                        }
                    } else {
                        if (rand.nextBoolean()) {
                            Direction tipOffset = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                            this.setBlock(level, treePos.relative(tipOffset).offset(0, height, 0), logState);
                        }
                        Direction tipLeaves = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                        placeLeaf(level, treePos.relative(tipLeaves).offset(0, height - rand.nextInt(2), 0),
                                logState, leavesState, hangingLeavesState);
                        for (int k = 0; k < rand.nextInt(2) + 1; k ++) {
                            tipLeaves = tipLeaves.getClockWise(Direction.Axis.Y);
                        }
                        placeLeaf(level, treePos.relative(tipLeaves).offset(0, height - rand.nextInt(2), 0),
                                logState, leavesState, hangingLeavesState);
                    }
                }

                return true;
            }
        }
        return false;
    }

    protected void placeBranch(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState branchState,
                                BlockState leavesState, BlockState hangingLeavesState, Direction direction, int length) {
        BlockPos blockPos = pos;
        for (int i = 0; i < length; i ++) {
            this.setBlock(level, blockPos, branchState);
            blockPos = blockPos.relative(direction);
            Direction leafDirection = direction;
            for (int j = 0; j < 4; j ++) {
                if (rand.nextInt(5) == 0) {
                    placeLeaf(level, blockPos.relative(leafDirection), branchState, leavesState, hangingLeavesState);
                }
                leafDirection = leafDirection.getClockWise(Direction.Axis.Y);
            }
            if (rand.nextInt(3) == 0) {
                placeLeaf(level, blockPos.offset(0, rand.nextInt(2) * 2 - 1, 0), branchState, leavesState, hangingLeavesState);
            }
        }
    }

    protected void placeLeaf(LevelAccessor level, BlockPos pos, BlockState logState, @Nullable BlockState leafState, @Nullable BlockState hangingLeafState) {
        boolean flag = level.getBlockState(pos).is(logState.getBlock());
        if (leafState != null) {
            if (level.getBlockState(pos).is(Blocks.AIR)) {
                this.setBlock(level, pos, leafState);
            }
            flag = flag || level.getBlockState(pos).is(leafState.getBlock());
        }
        if (hangingLeafState != null) {
            if (flag && level.getBlockState(pos.below()).is(Blocks.AIR)) {
                this.setBlock(level, pos.below(), hangingLeafState);
            }
        }
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
