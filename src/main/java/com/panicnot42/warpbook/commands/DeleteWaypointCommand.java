package com.panicnot42.warpbook.commands;

import com.panicnot42.warpbook.WarpWorldStorage;
import com.panicnot42.warpbook.util.CommandUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.StatCollector;

public class DeleteWaypointCommand extends CommandBase
{

    @Override
    public String getCommandName() {
        return "waypointdelete";
    }

    @Override
  public String getCommandUsage(ICommandSender var1)
  {
    return "/waypointdelete name";
  }

    @Override
    public void processCommand(ICommandSender var1, String[] var2) throws CommandException {
    WarpWorldStorage storage = WarpWorldStorage.get(var1.getEntityWorld());
    if (var2.length != 1)
    {
      CommandUtils.printUsage(var1, this);
      return;
    }
    if (storage.deleteWaypoint(var2[0]))
      CommandUtils.info(var1, StatCollector.translateToLocal("help.waypointdelete").trim());
    else
      CommandUtils.showError(var1, I18n.format(StatCollector.translateToLocal("help.notawaypoint").trim(), var2[0]));
    storage.save(var1.getEntityWorld());
  }

  public int compareTo(ICommand command)
  {
    return this.getCommandName().compareTo(command.getCommandName());
  }
}
