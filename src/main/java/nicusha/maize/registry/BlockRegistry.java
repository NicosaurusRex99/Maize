package nicusha.maize.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import static nicusha.maize.MaizeMod.MODID;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);


    public static final DeferredBlock<Block> maize_crop = registerBlockNoItem("maize_crop", CropBlock::new, BlockBehaviour.Properties.of().mapColor((statex) -> {return (Integer)statex.getValue(CropBlock.AGE) >= 7 ? MapColor.COLOR_YELLOW : MapColor.PLANT;}).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> blockFactory, Block.Properties properties) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, name));
        properties.setId(blockKey);
        DeferredBlock<T> block = BLOCKS.register(name, () -> blockFactory.apply(properties));
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
        return block;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, Function<BlockBehaviour.Properties, T> blockFactory, Block.Properties properties) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, name));
        properties.setId(blockKey);
        DeferredBlock<T> block = BLOCKS.register(name, () -> blockFactory.apply(properties));
        return block;
    }

    public static void load() {}
}
