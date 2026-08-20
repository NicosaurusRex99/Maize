package nicusha.maize.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

import static nicusha.maize.MaizeMod.MODID;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> corn_flour = register("corn_flour");
    public static final DeferredItem<Item> corn_seed = registerSeed("corn_seed", ()-> BlockRegistry.maize_crop.get());
    public static final DeferredItem<Item> corn = register("corn", properties -> new Item(properties.food(new FoodProperties((int) 2.0F, 1.5F, false))));
    public static final DeferredItem<Item> corn_bread = register("corn_bread", properties -> new Item(properties.food(new FoodProperties((int) 5.0F, 3.5F, false))));


    private static DeferredItem<Item> register(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
    }

    private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> factory) {
        return ITEMS.register(name, () -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
    }

    private static DeferredItem<Item> registerSeed(String name, Supplier<Block> block) {
        return register(name, properties -> new BlockItem(block.get(), properties.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
    }
    public static void load() {}
}
