package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;

import net.minecraft.item.Item;

public class DeathlyWarpPageItem extends Item
{
  public DeathlyWarpPageItem(String name)
  {
    super.setMaxStackSize(16).setCreativeTab(WarpBookMod.tabBook).setUnlocalizedName(name);
  }
}
