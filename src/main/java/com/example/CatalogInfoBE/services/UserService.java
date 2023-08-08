package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.dto.responses.UserResponse;
import com.example.CatalogInfoBE.mappers.CategoryMapper;
import com.example.CatalogInfoBE.mappers.UserMapper;
import com.example.CatalogInfoBE.models.table_entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserResponse getUserInfo(User user) {
        return UserMapper.INSTANCE.toDto(user);
    }

    public List<CategoryResponse> getCategories(User user) {
        List<CategoryResponse> responses = CategoryMapper.INSTANCE.toDtos(user.getCategories());
        formatAndExcludeChildren(responses);
        return responses;
    }

    private void setChildren(CategoryResponse responseParent, CategoryResponse response) {
        responseParent.setHasChildren(true);
        if(responseParent.getChildren() == null) {
            responseParent.setChildren(new ArrayList<>());
            responseParent.getChildren().add(response);
        } else {
            responseParent.getChildren().add(response);
        }
    }

    private boolean hasParent(CategoryResponse responseParent, CategoryResponse response) {
        return response.getParent() != -1 && responseParent.getId().equals(response.getParent());
    }

    private void getFormattedCategories(List<CategoryResponse> responses) {
        for(CategoryResponse response : responses) {
            for(CategoryResponse responseParent : responses) {
                if (hasParent(responseParent, response)) {
                    setChildren(responseParent, response);
                }
            }
        }
    }

    private void formatAndExcludeChildren(List<CategoryResponse> responses) {
        getFormattedCategories(responses);
        List<CategoryResponse> responseList = new ArrayList<>(responses);
        for (CategoryResponse response : responseList) {
            if (response.getParent() != -1) {

                responses.remove(response);
            }
        }
    }

}
