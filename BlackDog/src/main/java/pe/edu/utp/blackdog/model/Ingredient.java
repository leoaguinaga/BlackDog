package pe.edu.utp.blackdog.model;

import java.time.LocalDateTime;

public class Ingredient {
    private int ingredient_id;
    private String name;

    public Ingredient(Builder builder) {
        this.ingredient_id = builder.ingredient_id;
        this.name = builder.name;
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private int ingredient_id;
        private String name;

        public Builder(int ingredient_id, String name) {
            this.ingredient_id = ingredient_id;
            this.name = name;
        }

        public Ingredient build() {
            return new Ingredient(this);
        }
    }

    // GETTERS
    public int getIngredient_id() {
        return ingredient_id;
    }
    public String getName() {
        return name;
    }

    // CREATE INGREDIENT
    public static Ingredient createOrder(int ingredient_id, String name){
        return new Ingredient.Builder(ingredient_id, name).build();
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id=" + ingredient_id +
                ", name='" + name + '\'' +
                '}';
    }
}
