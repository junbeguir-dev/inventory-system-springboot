package com.example.inventory.controller;

import com.example.inventory.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final InventoryService inventoryService;

    public DashboardController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("productCount", inventoryService.productCount());
        model.addAttribute("totalQuantity", inventoryService.totalQuantity());
        model.addAttribute("totalValue", inventoryService.totalInventoryValue());
        model.addAttribute("lowStockProducts", inventoryService.lowStockProducts());
        return "dashboard";
    }
}
