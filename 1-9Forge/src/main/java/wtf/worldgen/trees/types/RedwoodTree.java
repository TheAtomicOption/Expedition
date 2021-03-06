package wtf.worldgen.trees.types;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import wtf.worldgen.trees.TreeVars;
import wtf.worldgen.trees.TreeVars.LeafStyle;

public class RedwoodTree extends TreeVars{
	public RedwoodTree(World world) {
		super(world, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), 
				Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), 
				Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE));
		leaftype = LeafStyle.SPRUCE;
		this.topLimitDown = 2;
		this.topLimitUp = 0;
		this.topLimitIncrement = Math.PI/8;
	}

	@Override
	public int getBranchesPerNode(double scale) {
		return (int) (15+15*scale);
	}

	@Override
	public double getBranchRotation(double scale, double numBranches) {
		return Math.PI/(numBranches+1);
	}

	@Override
	public double getBranchSeperation(double scale) {
		return 1;
	}

	@Override
	public double getBranchPitch(double scale) {
		return -0.2;
	}

	@Override
	public double getBranchLength(double scale, double trunkHeight, double nodeHeight) {
		double taper = 1-nodeHeight/trunkHeight;
		return  (trunkHeight/3)*taper*(2+random.nextFloat());
	}

	@Override
	public double getTrunkHeight(double scale) {
		return 33 + random.nextInt(33) + 33*scale;
	}

	@Override
	public double getRootLength(double trunkHeight) {
		return trunkHeight/4;
	}

	@Override
	public double getTrunkDiameter(double scale) {
		return random.nextInt(4)+4+scale*4;
	}



	@Override
	public int getTrunkColumnHeight(double trunkHeight, double currentRadius, double maxRadius) {
		if (currentRadius > 1){
			double thirdHeight = trunkHeight/3;
			double rad = 1-(currentRadius/maxRadius);
			return (int) (thirdHeight + 2*(thirdHeight*rad) + random.nextInt(5)-2);
		}
		else {
			return MathHelper.ceiling_double_int(trunkHeight);
		}
	}
	@Override
	public double getLowestBranchRatio() {
		return 0.8;
	}

	@Override
	public int getNumRoots(double trunkDiameter) {
		return random.nextInt(4)+4;
	}

}
