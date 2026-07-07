package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final InventoryService inventoryService;

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("products", inventoryService.listProducts(keyword));
        model.addAttribute("keyword", keyword);
        return "products/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        loadLookups(model);
        return "products/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            loadLookups(model);
            return "products/form";
        }
        inventoryService.saveProduct(product);
        redirectAttributes.addFlashAttribute("success", "Product saved successfully.");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", inventoryService.getProduct(id));
        loadLookups(model);
        return "products/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        inventoryService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("success", "Product deleted.");
        return "redirect:/products";
    }

    @PostMapping("/{id}/stock")
    public String adjustStock(@PathVariable Long id, @RequestParam int change, RedirectAttributes redirectAttributes) {
        try {
            inventoryService.adjustStock(id, change);
            redirectAttributes.addFlashAttribute("success", "Stock updated.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/products";
    }

    private void loadLookups(Model model) {
        model.addAttribute("categories", inventoryService.categories());
        model.addAttribute("suppliers", inventoryService.suppliers());
    }
}
