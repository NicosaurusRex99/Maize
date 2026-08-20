package nicusha.maize.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import nicusha.maize.registry.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "maize");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.CROPS).add(BlockRegistry.maize_crop.get().builtInRegistryHolder().getKey());
        tag(BlockTags.MAINTAINS_FARMLAND).add(BlockRegistry.maize_crop.get().builtInRegistryHolder().getKey());
    }

    private TagKey<Block> commonTag(String name){
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", name));
    }

    private TagKey<Block> minecraftTag(String name){
        return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
    }

    private TagKey<Block> customTag(String name){
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("maize", name));
    }
}