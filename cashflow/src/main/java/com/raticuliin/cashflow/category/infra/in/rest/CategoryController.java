package com.raticuliin.cashflow.category.infra.in.rest;

import com.raticuliin.cashflow.category.app.in.CategoryService;
import com.raticuliin.cashflow.category.app.in.usecase.*;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryRequest;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryResponse;
import com.raticuliin.cashflow.category.infra.in.rest.mapper.CategoryRestMapper;
import com.raticuliin.cashflow.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse;

        try {
            categoryResponse = CategoryRestMapper.domainToResponse(
                    createCategoryUseCase.createCategory(
                            CategoryRestMapper.requestToDomain(categoryRequest)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(categoryResponse);

    }

}
