package nicusha.maize;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import nicusha.maize.data.DataGenerators;
import nicusha.maize.registry.BlockRegistry;
import nicusha.maize.registry.GlobalLootModifierRegistry;
import nicusha.maize.registry.ItemRegistry;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MaizeMod.MODID)
public class MaizeMod {
    public static final String MODID = "maizemod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> tab = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = tab.register("maize", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.maize")).icon(() -> new ItemStack(ItemRegistry.corn.get())).build());


    public MaizeMod(IEventBus bus, ModContainer container) {
        bus.addListener(DataGenerators::genData);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::creativeTab);
        ItemRegistry.ITEMS.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        GlobalLootModifierRegistry.GLM_SERIALIZERS.register(bus);
        tab.register(bus);
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


        ItemRegistry.load();
        BlockRegistry.load();
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    public void creativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ITEMS.get()) {
            for (var entry : ItemRegistry.ITEMS.getEntries()) {
                Item item = entry.get().asItem();
                    event.accept(item.getDefaultInstance());
            }
        }
    }

}
