package nicusha.maize.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import nicusha.maize.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modid) {
        super(output, registries, modid);
    }

    @Override
    protected void start() {
        LootItemCondition[] grassConditions = new LootItemCondition[] {LootTableIdCondition.builder(Identifier.withDefaultNamespace("blocks/short_grass")).or(LootTableIdCondition.builder(Identifier.withDefaultNamespace("blocks/tall_grass"))).build()};
        add("grass_corn_drop", new AddItemModifier(new LootItemCondition[] {LootTableIdCondition.builder(Identifier.withDefaultNamespace("blocks/short_grass")).build(), LootItemRandomChanceCondition.randomChance(0.125f).build()}, 1, ItemRegistry.corn_seed.get()));
    }
}