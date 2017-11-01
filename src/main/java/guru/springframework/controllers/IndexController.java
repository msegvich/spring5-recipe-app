package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");
        Iterable<Recipe> recipes = recipeService.getRecipes();

        System.out.println("Category is " + categoryOptional.get().getId());
        System.out.println("UOM is " + unitOfMeasure.get().getId());
        for( Recipe recipe : recipes){
            System.out.println("Recipe description = " + recipe.getDescription());
            System.out.println("Number of ingredients = " + recipe.getIngredients().size());
        }
        return "index";
    }

    @RequestMapping("/all-recipes")
    public String getAllRecipes(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "all-recipes";
    }
}
