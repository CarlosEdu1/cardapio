package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodResponseDTO;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping()
    public ResponseEntity<List<FoodResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Food> saveFood(@RequestBody FoodRequestDTO foodRequestDTO) {
        boolean body = foodRequestDTO.validateBody();
        if (!body) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        foodService.saveFood(foodRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> putFood(@RequestBody FoodRequestDTO foodRequestDTO, @PathVariable Long id) {
        boolean body = foodRequestDTO.validateBody();
        if (!body) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        foodService.putFood(foodRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Food> deleteFoood(@PathVariable Long id) {
        if (!foodService.deleteFood(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        foodService.deleteFood(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
