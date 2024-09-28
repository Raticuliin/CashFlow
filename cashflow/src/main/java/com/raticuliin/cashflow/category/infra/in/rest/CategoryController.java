package com.raticuliin.cashflow.category.infra.in.rest;

import com.raticuliin.cashflow.category.app.in.usecase.*;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryRequest;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryResponse;
import com.raticuliin.cashflow.category.infra.in.rest.mapper.CategoryRestMapper;
import com.raticuliin.cashflow.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {

        List<CategoryResponse> categoryResponseList;

        try {
            categoryResponseList = getAllCategoriesUseCase.getAllCategories()
                    .stream()
                    .map(CategoryRestMapper::domainToResponse)
                    .toList();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Utils.getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        return ResponseEntity.ok(categoryResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {

        CategoryResponse categoryResponse;

        try {

            categoryResponse = CategoryRestMapper.domainToResponse(
                    getCategoryByIdUseCase.getCategoryById(id));

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));

        }

        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse;

        try {
            categoryResponse = CategoryRestMapper.domainToResponse(
                    updateCategoryUseCase.updateCategory(
                            id,
                            CategoryRestMapper.requestToDomain(categoryRequest)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {

        CategoryResponse categoryResponse;

        try {
            categoryResponse = CategoryRestMapper.domainToResponse(
                    deleteCategoryUseCase.deleteCategory(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity.ok(categoryResponse);
    }

}
