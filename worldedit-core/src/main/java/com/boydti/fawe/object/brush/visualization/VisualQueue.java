package com.boydti.fawe.object.brush.visualization;

import com.boydti.fawe.object.task.SingleThreadIntervalQueue;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.command.tool.BrushTool;
import com.sk89q.worldedit.command.tool.Tool;
import com.sk89q.worldedit.entity.Player;

public class VisualQueue extends SingleThreadIntervalQueue<Player> {

    public VisualQueue(int interval) {
        super(interval);
    }

    @Override
    public void operate(Player fp) {
        LocalSession session = fp.getSession();
        Tool tool = session.getTool(fp);
        if (tool instanceof BrushTool) {
            BrushTool brushTool = (BrushTool) tool;
            if (brushTool.getVisualMode() != VisualMode.NONE) {
                try {
                    brushTool.visualize(BrushTool.BrushAction.PRIMARY, fp);
                } catch (Throwable e) {
                    WorldEdit.getInstance().getPlatformManager().handleThrowable(e, fp);
                }
            }
        }
    }
}
