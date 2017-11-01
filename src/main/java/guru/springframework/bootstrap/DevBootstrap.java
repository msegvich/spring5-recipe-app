package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sun.tools.jconsole.Plotter;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Optional<Category> american = categoryRepository.findByDescription("American");
        if(!american.isPresent()){
            throw new RuntimeException("Expected category not found!");
        }

        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        if(!mexican.isPresent()){
            throw new RuntimeException("Expected category not found!");
        }

        Category americanCategory = american.get();
        Category mexicanCategory = mexican.get();

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> teasponOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teasponOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> tabespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tabespoonOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintOptional.isPresent()){
            throw new RuntimeException("Expedted UOM not found!");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure teaspoonUom = teasponOptional.get();
        UnitOfMeasure tablespoonUom = tabespoonOptional.get();
        UnitOfMeasure cupUom = cupOptional.get();
        UnitOfMeasure pinchUom = pinchOptional.get();
        UnitOfMeasure dashUom = dashOptional.get();
        UnitOfMeasure ounceUom = ounceOptional.get();
        UnitOfMeasure pintUom = pintOptional.get();

        Recipe guac = new Recipe();
        guac.getCategories().add(mexicanCategory);
        guac.setDescription("Tasty Guacamole");
        guac.setDifficulty(Difficulty.EASY);
        guac.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "Variations\n");
        guac.setNotes(new Notes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n")
        );
        guac.setCookTime(0);
        guac.setPrepTime(10);
        // Add ingredients
        guac.getIngredients().add(new Ingredient("Ripe avocados", new BigDecimal(2), eachUom, guac));
        guac.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(.5), teaspoonUom, guac));
        guac.getIngredients().add(new Ingredient("Lime or Lemon juice", new BigDecimal(1), tablespoonUom, guac));
        guac.getIngredients().add(new Ingredient("Minced Red onion", new BigDecimal(2), tablespoonUom, guac));
        guac.getIngredients().add(new Ingredient("Minced Serrano chiles", new BigDecimal(1), eachUom, guac));
        guac.getIngredients().add(new Ingredient("Black pepper", new BigDecimal(1), dashUom, guac));
        guac.getIngredients().add(new Ingredient("Chopped ripe tomato with seeds removed", new BigDecimal(.5), eachUom, guac));

        recipeRepository.save(guac);

        Recipe tacos = new Recipe();
        tacos.getCategories().add(mexicanCategory);
        tacos.setDescription("Spicy Grilled Tacos");
        tacos.setDifficulty(Difficulty.EASY);
        tacos.setPrepTime(20);
        tacos.setCookTime(10);
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
        );
        tacos.setNotes(new Notes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)\n")
        );
        tacos.getIngredients().add(new Ingredient("Ancho chile powder", new BigDecimal(2), tablespoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teaspoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Dried cumin", new BigDecimal(1), teaspoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Salt", new BigDecimal(.5), teaspoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Finely chopped clove of garlic", new BigDecimal(1), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Finely grated orange zest", new BigDecimal(1), tablespoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Freshly squeezed orange juices", new BigDecimal(3), tablespoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Olive oil", new BigDecimal(2), tablespoonUom, tacos));
        tacos.getIngredients().add(new Ingredient("Boneless chicken thighs", new BigDecimal(6), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Corn tortillas", new BigDecimal(8), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Baby Arugula", new BigDecimal(3), cupUom, tacos));
        tacos.getIngredients().add(new Ingredient("Medium ripe avocados", new BigDecimal(2), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Thinly sliced radishes", new BigDecimal(4), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Halved cherry tomatoes", new BigDecimal(.5), pintUom, tacos));
        tacos.getIngredients().add(new Ingredient("Thinly sliced red onion", new BigDecimal(25), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUom, tacos));
        tacos.getIngredients().add(new Ingredient("Sour Cream thinned with milk", new BigDecimal(.5), cupUom, tacos));
        tacos.getIngredients().add(new Ingredient("Lime, cut into wedges", new BigDecimal(1), eachUom, tacos));

        recipeRepository.save(tacos);


//        try {
////            InputStream is = new FileInputStream("static/guacamole.jpg");
//            File file = FileUtils.getFile(getClass().getClassLoader()
//                    .getResource("fileTest.txt")
//                    .getPath());
////            guac.setImage
//        }
//        catch (FileNotFoundException ex)
//        {
//            throw ex;
//        }
    }
}
