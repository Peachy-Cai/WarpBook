package com.panicnot42.warpbook.block;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.tileentity.TileEntityBookCloner;
import com.panicnot42.warpbook.util.WorldUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBookClonerBlock extends BlockContainer
{
  public TileEntityBookClonerBlock()
  {
    super(Material.iron);
    setUnlocalizedName("bookcloner");
    setCreativeTab(WarpBookMod.tabBook);
    setStepSound(soundTypeStone);
    setHardness(10.0f);
    setResistance(20.0f);
    setHarvestLevel("pickaxe", 2);
  }

  @Override
  public TileEntity createNewTileEntity(World world, int meta)
  {
    return new TileEntityBookCloner();
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
  {
    player.openGui(WarpBookMod.instance, WarpBookMod.BookClonerInventoryGuiIndex, world, pos.getX(), pos.getY(), pos.getZ());
    return true;
  }

  @Override
  public int getRenderType()
  {
    return 3;
  }
  
  @Override
  public boolean isFullCube()
  {
    return false;
  }

  @Override
  public boolean isOpaqueCube()
  {
    return false;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
  {
    return new AxisAlignedBB(pos.getX(),
                             pos.getY(),
                             pos.getZ(),
                             pos.getX() + 1,
                             pos.getY() + 0.75f,
                             pos.getZ() + 1);
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
  {
    return new AxisAlignedBB(pos.getX(),
                             pos.getY(),
                             pos.getZ(),
                             pos.getX() + 1,
                             pos.getY() + 0.75f,
                             pos.getZ() + 1);
  }

  @Override
  public void breakBlock(World world, BlockPos pos, IBlockState state)
  {
    if (!world.isRemote)
    {
      ItemStack books = ((TileEntityBookCloner)world.getTileEntity(pos)).getBooks();
      ItemStack pages = ((TileEntityBookCloner)world.getTileEntity(pos)).getPages();
      ItemStack result = ((TileEntityBookCloner)world.getTileEntity(pos)).getResult();
      if (books != null)
        WorldUtils.dropItemStackInWorld(world, pos.getX(), pos.getY(), pos.getZ(), books);
      if (pages != null)
        WorldUtils.dropItemStackInWorld(world, pos.getX(), pos.getY(), pos.getZ(), pages);
      if (result != null)
        WorldUtils.dropItemStackInWorld(world, pos.getX(), pos.getY(), pos.getZ(), result);
    }
  }
}
