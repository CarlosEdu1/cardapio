package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodResponseDTO;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodRepositories = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodRepositories;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping()
    public void saveFood(@RequestBody FoodRequestDTO foodRequestDTO){
        Food foodData = new Food(foodRequestDTO);
        foodRepository.save(foodData);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping("{id}")
//    public void putFood(@RequestBody FoodRequestDTO foodRequestDTO, @PathVariable Long id){
//        Food foodData = new Food();
//        foodData.setId(id);
//        foodData.setPrice(foodRequestDTO.get);
//        foodRepository.saveAll();
//    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("{id}")
    public void deleteFoood(@PathVariable Long id){
        foodRepository.deleteById(id);
    }
}
