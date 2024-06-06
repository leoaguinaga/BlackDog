package pe.edu.utp.blackdog.model;

public class Ingredient {
    private long ingredient_id;
    private String name;

    public Ingredient(Builder builder) {
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private long ingredient_id;
        private String name;

        public Builder(String name) {
            this.ingredient_id = 0;
            this.name = name;
        }

        public Builder withIngredient_id(long ingredient_id){
            this.ingredient_id = ingredient_id;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(this);
        }
    }

    // GETTERS
    public long getIngredient_id() {
        return ingredient_id;
    }
    public String getName() {
        return name;
    }

    // CREATE INGREDIENT
    public static Ingredient createIngredientWithoutId(String name){
        return new Ingredient.Builder(name).build();
    }
    public static Ingredient createIngredient(long ingredient_id, String name){
        return new Ingredient.Builder(name).withIngredient_id(ingredient_id).build();
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id=" + ingredient_id +
                ", name='" + name + '\'' +
                '}';
    }
}
