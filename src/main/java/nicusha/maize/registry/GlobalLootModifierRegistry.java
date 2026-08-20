package nicusha.maize.registry;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import nicusha.maize.MaizeMod;
import nicusha.maize.data.AddItemModifier;

import java.util.function.Supplier;

public class GlobalLootModifierRegistry {
    public static final DeferredRegister<MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier>> GLM_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MaizeMod.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM = GLM_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

}