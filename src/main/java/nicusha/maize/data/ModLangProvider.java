package nicusha.maize.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import nicusha.maize.MaizeMod;
import nicusha.maize.Utils;
import nicusha.maize.registry.BlockRegistry;
import nicusha.maize.registry.ItemRegistry;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static nicusha.maize.MaizeMod.MODID;

public class ModLangProvider extends LanguageProvider {
    protected final String locale;
    private final Map<String, String> existingTranslations = new HashMap<>();
    private final Set<String> addedKeys = new HashSet<>();
    private static final Gson GSON = new Gson();

    public ModLangProvider(PackOutput output, String locale) {
        super(output, MODID, locale);
        this.locale = locale;
        loadExistingTranslations(output);
    }

    @Override
    protected void addTranslations() {
        addAuto(MODID+".configuration.title", "Maize Mod Configs");
        addAuto(MODID+".configuration.section.maizemod.common.title", "Maize Mod Configs");
        addAuto(MODID+".configuration.section.maizemod.common.toml.title", "Maize Mod Configs");
        addAuto("itemGroup.maize", "Maize Mod");
        addItem(ItemRegistry.corn, "Corn");
        addItem(ItemRegistry.corn_flour, "Corn Flour");
        addItem(ItemRegistry.corn_seed, "Corn Seeds");
        addItem(ItemRegistry.corn_bread, "Corn Bread");
        addBlock(BlockRegistry.maize_crop, "Maize Crop");
    }

    private void addAuto(Object key, String englishValue) {
        if (key instanceof String s) {
            addTranslation(s, englishValue);
            return;
        }
        String path = getPath(key);
        if (path == null) return;

        if (key instanceof Item || (key instanceof Supplier<?> s && s.get() instanceof Item)) {
            addTranslation("item." + MODID + "." + path, englishValue);
        }
        else if (key instanceof Block || (key instanceof Supplier<?> s && s.get() instanceof Block)) {
            addTranslation("block." + MODID + "." + path, englishValue);
            addTranslation("item." + MODID + "." + path, englishValue);
        } else {
            addTranslation(key.toString(), englishValue);
        }
    }

    private void addTranslation(String key, String englishValue) {
        if (!addedKeys.add(key)) {
            if (key.startsWith("subtitles.")) {
                MaizeMod.LOGGER.info("DEBUG: Subtitle key " + key + " was already registered. Checking if values match.");
            }
            return;
        }
        if (existingTranslations.containsKey(key)) {
            add(key, existingTranslations.get(key));
            return;
        }
        String targetText = englishValue;
        if (!locale.equals("en_us")) {
            String langCode = locale.split("_")[0];
            targetText = Utils.translate(langCode, englishValue);
        }
        add(key, targetText);
    }

    private String getPath(Object key) {
        if (key instanceof DeferredHolder<?, ?> holder) return holder.getId().getPath();
        if (key instanceof Block b) return BuiltInRegistries.BLOCK.getKey(b).getPath();
        if (key instanceof Item i) return BuiltInRegistries.ITEM.getKey(i).getPath();

        return null;
    }

    private void loadExistingTranslations(PackOutput output) {
        try {
            Path path = output.getOutputFolder().resolve("assets").resolve(MODID).resolve("lang").resolve(locale + ".json");
            if (Files.exists(path)) {
                try (Reader reader = Files.newBufferedReader(path)) {
                    JsonObject json = GSON.fromJson(reader, JsonObject.class);
                    if (json != null) {
                        json.entrySet().forEach(entry -> existingTranslations.put(entry.getKey(), entry.getValue().getAsString()));
                    }
                }
            }
        } catch (Exception e) {
            MaizeMod.LOGGER.info("Could not load existing translations for {}: {}", locale, e.getMessage());
        }
    }

    private void addPainting(String registryName, String title, String author) {
        addAuto("painting.divinerpg." + registryName + ".title", title);
        add("painting.divinerpg." + registryName + ".author", author);
    }

    private void addAdvancement(String id, String title, String desc) {
        add("advancement."+MODID+"." + id, title);
        add("advancement."+MODID+"." + id + ".desc", desc);
    }

    @Override
    public String getName() {
        return "Languages: " + MODID + " (" + locale + ")";
    }
}
