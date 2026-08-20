package nicusha.maize.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import nicusha.maize.registry.ItemRegistry;

import static nicusha.maize.MaizeMod.MODID;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemRegistry.corn_flour), RecipeCategory.FOOD, CookingBookCategory.FOOD, ItemRegistry.corn_bread.get(), 0.2f, 200).unlockedBy("has_maizeflour", has(ItemRegistry.corn_flour)).save(output, MODID + ":bread_maize_from_smelting");
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, ItemRegistry.corn_flour.get()).requires(ItemRegistry.corn.get(), 2).unlockedBy("has_corn", has(ItemRegistry.corn.get())).save(output, MODID + ":corn_flour_from_shapeless");
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, ItemRegistry.corn_seed.get(), 1).requires(ItemRegistry.corn.get(), 1).unlockedBy("has_corn", has(ItemRegistry.corn.get())).save(output, MODID + ":corn_seed_from_corn");
    }
}
