package nicusha.maize.data;

import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import nicusha.maize.registry.BlockRegistry;
import nicusha.maize.registry.ItemRegistry;

import java.util.Collections;
import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        LootItemBlockStatePropertyCondition.Builder cropFullyGrownCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockRegistry.maize_crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_7, 7));

        this.add(BlockRegistry.maize_crop.get(), applyExplosionDecay(BlockRegistry.maize_crop.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(AlternativesEntry.alternatives(LootItem.lootTableItem(ItemRegistry.corn.get()).when(cropFullyGrownCondition).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f))), LootItem.lootTableItem(ItemRegistry.corn_seed.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Set.of(BlockRegistry.maize_crop.get());
    }
}