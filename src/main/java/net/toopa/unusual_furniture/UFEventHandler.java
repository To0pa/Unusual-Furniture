package net.toopa.unusual_furniture;

import net.minecraft.world.item.Items;

import net.toopa.unusual_furniture.common.block.ISittableBlock;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

//? fabric {
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//?} neoforge {
/*import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
*///?}

public class UFEventHandler {

	public static InteractionResult onBlockClick(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
		var blockPos = hitResult.getBlockPos();
		var block = level.getBlockState(blockPos);
		if (block.is(UFBlockTags.SITTABLE_BLOCKS)) {
			if (ISittableBlock.sitOn(level, blockPos, player))
				return InteractionResult.CONSUME;
		}
		return InteractionResult.PASS;
	}

	public static void addToCreativeTab(
			//? fabric {
			FabricItemGroupEntries creativeTabEdit
			//?} else {
			//BuildCreativeModeTabContentsEvent creativeTabEdit
			//?}
	) {
		//? neoforge {
		/*if (creativeTabEdit.getTabKey() != CreativeModeTabs.INGREDIENTS) {
			return;
		}
		*///?}
		//~ if fabric 'insertBefore' -> 'addBefore' {
		creativeTabEdit.addBefore(Items.BRICK.getDefaultInstance(), UFObjects.SCREW.getDefaultInstance()
				//? neoforge {
				//, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
				//?}
		);
		//~}
	}
}
