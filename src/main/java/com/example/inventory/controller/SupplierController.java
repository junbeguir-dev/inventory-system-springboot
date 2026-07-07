package com.example.inventory.controller;

import com.example.inventory.model.Supplier;
import com.example.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final InventoryService inventoryService;

    public SupplierController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("suppliers", inventoryService.suppliers());
        model.addAttribute("supplier", new Supplier());
        return "suppliers";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Supplier supplier, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("suppliers", inventoryService.suppliers());
            return "suppliers";
        }
        inventoryService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("success", "Supplier saved.");
        return "redirect:/suppliers";
    }
}
