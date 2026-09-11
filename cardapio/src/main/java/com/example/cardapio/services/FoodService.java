package com.example.cardapio.services;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<FoodResponseDTO> getAll() {
        List<FoodResponseDTO> foodRepositories =
                foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodRepositories;
    }

    public void saveFood(FoodRequestDTO foodRequestDTO) {
        Food foodData = new Food(foodRequestDTO);
        foodRepository.save(foodData);
    }

    public void putFood(FoodRequestDTO foodRequestDTO, Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um produto com este ID"));
        food.setImage(foodRequestDTO.image());
        food.setTitle(foodRequestDTO.title());
        food.setPrice(foodRequestDTO.price());
        foodRepository.save(food);
    }

    public boolean deleteFood(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
