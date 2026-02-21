package net.gecko.prettypigeon.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.block.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class ChamberCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = Identifier.of(PrettyPigeon.MOD_ID, "textures/gui/chamber_gui_rei.png");

    public static final CategoryIdentifier<ChamberDisplay> CHAMBER = CategoryIdentifier.of(PrettyPigeon.MOD_ID, "chamber");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CHAMBER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.pretpig.radiation_chamber");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.RADIATION_CHAMBER.asItem().getDefaultStack());
    }

    @Override public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startpoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 40);

        List<Widget> widgets = new LinkedList<>(); widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startpoint.x, startpoint.y, 176, 82)));

        widgets.add(Widgets.createSlot(new Point(startpoint.x + 54, startpoint.y + 19))
                .entries(display.getInputEntries().get(0)).disableBackground().markInput());

        widgets.add(Widgets.createSlot(new Point(startpoint.x + 54, startpoint.y + 50))
                .entries(display.getInputEntries().get(1)).disableBackground().markInput());

        widgets.add(Widgets.createSlot(new Point(startpoint.x + 104, startpoint.y + 34))
                .entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
