package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodResponseDTO;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping()
    public List<FoodResponseDTO> getAll() {
        List<FoodResponseDTO> foodRepositories =
                foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodRepositories;
    }

    @PostMapping()
    public ResponseEntity<Food> saveFood(@RequestBody FoodRequestDTO foodRequestDTO) {
        Food foodData = new Food(foodRequestDTO);
        foodRepository.save(foodData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> putFood(@RequestBody FoodRequestDTO foodRequestDTO, @PathVariable Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um produto com este ID"));
        food.setImage(foodRequestDTO.image());
        food.setTitle(foodRequestDTO.title());
        food.setPrice(foodRequestDTO.price());
        foodRepository.save(food);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Food> deleteFoood(@PathVariable Long id) {
        foodRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
