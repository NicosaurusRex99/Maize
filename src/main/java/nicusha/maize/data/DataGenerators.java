package nicusha.maize.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static nicusha.maize.MaizeMod.MODID;

public class DataGenerators {

    public static void genData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        DataGenerator generator = event.getGenerator();
        String[] popularLanguages = {"es_es", "fr_fr", "de_de", "zh_cn", "ja_jp", "ru_ru", "hi_in", "ar_sa", "th_th", "tk_ph", "sv_se", "pt_pt", "it_it", "af_za", "ast_es", "az_az", "ba_ru", "bar", "be_by"};

        for (String lang : popularLanguages) {event.getGenerator().addProvider(true, new ModLangProvider(output, lang));}

        generator.addProvider(true, new ModLangProvider(output, "en_us"));

        event.createProvider(ModModelProvider::new);
        event.createBlockAndItemTags(ModBlockTagsProvider::new, (packOutput, lookup, blockTags) -> new ModItemTagsProvider(packOutput, lookup));
        event.createProvider(ModLootTableProvider::create);
        generator.addProvider(true, new ModLootModifierProvider(output, lookupProvider, MODID));
        generator.addProvider(true, new RecipeProvider.Runner(output, lookupProvider) {@Override public String getName() { return "Maize Recipes"; } @Override protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {return new ModRecipeProvider(registries, recipeOutput);}});

    }
}