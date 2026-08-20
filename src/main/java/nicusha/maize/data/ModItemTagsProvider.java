package nicusha.maize.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import nicusha.maize.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "maize");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(commonTag("foods")).add(ItemRegistry.corn_bread.getKey());
        tag(commonTag("crops")).addTag(commonTag("crops/corn"));
        tag(commonTag("crops/corn")).add(ItemRegistry.corn.get().builtInRegistryHolder().getKey());

        tag(commonTag("flour")).addTag(commonTag("flour/corn"));
        tag(commonTag("flour/corn")).add(ItemRegistry.corn_flour.getKey());

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(ItemRegistry.corn_seed.getKey());
        tag(ItemTags.BEE_FOOD).add(ItemRegistry.corn_seed.getKey());
        tag(commonTag("seeds")).add(ItemRegistry.corn_seed.getKey());
        tag(commonTag("seeds/corn")).add(ItemRegistry.corn_seed.getKey());
        tag(commonTag("seeds/maize")).add(ItemRegistry.corn_seed.getKey());
        tag(commonTag("animal_foods")).add(ItemRegistry.corn_seed.getKey());

    }

    private static TagKey<Item> commonTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", name));
    }

    private TagKey<Item> minecraftTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }

    private TagKey<Item> customTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("maize", name));
    }
}