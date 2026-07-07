package com.example.inventory.controller;

import com.example.inventory.model.Category;
import com.example.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final InventoryService inventoryService;

    public CategoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", inventoryService.categories());
        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", inventoryService.categories());
            return "categories";
        }
        inventoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("success", "Category saved.");
        return "redirect:/categories";
    }
}
