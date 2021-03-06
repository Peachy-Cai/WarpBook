package com.panicnot42.warpbook.tileentity;

import com.panicnot42.warpbook.item.WarpBookItem;
import com.panicnot42.warpbook.item.WarpPrintingPlateItem;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBookCloner extends TileEntity
{
  private ItemStack pages;
  private ItemStack books;
  private ItemStack result;

  public TileEntityBookCloner()
  {
  }

  @Override
  public void readFromNBT(NBTTagCompound tag)
  {
    super.readFromNBT(tag);
    pages  = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("pages"));
    books  = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("books"));
    result = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("result"));
  }
    
  @Override
  public void writeToNBT(NBTTagCompound tag)
  {
    super.writeToNBT(tag);
    if (pages != null)
      tag.setTag("pages",   pages.writeToNBT(new NBTTagCompound()));
    if (books != null)
      tag.setTag("books",   books.writeToNBT(new NBTTagCompound()));
    if (result != null)
      tag.setTag("result", result.writeToNBT(new NBTTagCompound()));
  }

  public ItemStack getPages()
  {
    return pages;
  }

  public ItemStack getBooks()
  {
    return books;
  }

  public ItemStack getResult()
  {
    return result;
  }

  public void setPages(ItemStack pages)
  {
    this.pages = pages;
    this.markDirty();
  }

  public void setBooks(ItemStack books)
  {
    this.books = books;
    this.markDirty();
  }

  public void setResult(ItemStack result)
  {
    this.result = result;
    this.markDirty();
  }

  public boolean performOperation()
  {
    if (pages != null && books != null && result == null && books.hasTagCompound())
    {
      int cost = WarpBookItem.getCopyCost(books);
      if (pages.stackSize >= cost)
      {
        pages.stackSize -= cost;
        if (pages.stackSize == 0)
          pages = null;
        result = WarpPrintingPlateItem.print(books);
      }
    }
    return true;
  }
}
