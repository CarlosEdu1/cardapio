package com.example.cardapio.food;

public record FoodRequestDTO(String title, String image, Integer price){
    public boolean validateBody(){
        if (this.title == null || this.title.isEmpty()){
            return false;
        }
        if (this.image == null || this.image.isEmpty()){
            return false;
        }
        return this.price != null;
    }
}
