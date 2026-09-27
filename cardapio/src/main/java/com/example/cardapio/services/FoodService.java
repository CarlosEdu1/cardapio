package com.example.cardapio.services;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<FoodResponseDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<FoodResponseDTO> foodRepositories =
                foodRepository.findAll(pageable).stream().map(FoodResponseDTO::new).toList();
        return foodRepositories;
    }

    public void saveFood(FoodRequestDTO foodRequestDTO) {
        Food foodData = new Food(foodRequestDTO);
        foodRepository.save(foodData);
    }

    public void putFood(FoodRequestDTO foodRequestDTO, Long id) {
        boolean idExist = existsById(id);
        if (!idExist) {

        } else {
            Food food = foodRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Não existe um produto com este ID"));
            food.setImage(foodRequestDTO.image());
            food.setTitle(foodRequestDTO.title());
            food.setPrice(foodRequestDTO.price());
            foodRepository.save(food);
        }
    }

    public boolean deleteFood(Long id) {
         if (!existsById(id)){
             return false;
         }
          foodRepository.deleteById(id);
         return true;
    }

    private boolean existsById(Long id) {
        return foodRepository.existsById(id);
    }
}
