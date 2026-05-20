package com.warehouse.product.controller;

import com.warehouse.product.entity.Product;
import com.warehouse.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/notifications")
//@CrossOrigin(origins = "*")
public class NotificationController {

    private static final int LOW_STOCK = 10;
    private static final String PRODUCT_ID =
        "productId";
    private static final String PRODUCT_NAME =
        "productName";
    private static final String MESSAGE =
        "message";
    private static final String SEVERITY =
        "severity";
    private static final String TYPE = "type";
    private static final String EXPIRY_DATE =
        "expiryDate";
    private static final String DAYS_LEFT =
        "daysLeft";
    private static final String QUANTITY =
        "quantity";
    private static final String DANGER =
        "DANGER";
    private static final String WARNING =
        "WARNING";

    private final ProductService productService;

    public NotificationController(
            ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>>
            getNotifications() {

        List<Product> all =
            productService.getAllProducts();

        List<Map<String, Object>> notifications =
            new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (Product p : all) {
            checkLowStock(p, notifications);
            checkExpiry(p, notifications, today);
        }

        notifications.sort((a, b) -> {
            String sevA =
                (String) a.get(SEVERITY);
            String sevB =
                (String) b.get(SEVERITY);
            return sevB.compareTo(sevA);
        });

        return ResponseEntity.ok(notifications);
    }

    private void checkLowStock(
            Product p,
            List<Map<String, Object>>
                notifications) {
        if (p.getProductQuantity() <= LOW_STOCK) {
            Map<String, Object> notif =
                new HashMap<>();
            notif.put(TYPE, "LOW_STOCK");
            notif.put(SEVERITY, WARNING);
            notif.put(PRODUCT_ID,
                p.getProductId());
            notif.put(PRODUCT_NAME,
                p.getProductName());
            notif.put(MESSAGE,
                "Low Stock Alert: " +
                p.getProductName() +
                " has only " +
                p.getProductQuantity() +
                " units left!");
            notif.put(QUANTITY,
                p.getProductQuantity());
            notifications.add(notif);
        }
    }

    private void checkExpiry(
            Product p,
            List<Map<String, Object>>
                notifications,
            LocalDate today) {

        if (p.getProductExpiry() == null) {
            return;
        }

        LocalDate expiryDate =
            p.getProductExpiry()
             .toInstant()
             .atZone(ZoneId.systemDefault())
             .toLocalDate();

        long daysUntilExpiry =
            ChronoUnit.DAYS.between(
                today, expiryDate);

        int notifyDaysBefore =
            p.getNotifyDaysBefore() != null
            ? p.getNotifyDaysBefore() : 3;

        if (daysUntilExpiry < 0) {
            notifications.add(
                buildExpiredNotif(
                    p, expiryDate,
                    daysUntilExpiry));
        } else if (daysUntilExpiry
                   <= notifyDaysBefore) {
            notifications.add(
                buildExpiringSoonNotif(
                    p, expiryDate,
                    daysUntilExpiry));
        }
    }

    private Map<String, Object>
            buildExpiredNotif(
                Product p,
                LocalDate expiryDate,
                long daysUntilExpiry) {

        Map<String, Object> notif =
            new HashMap<>();
        notif.put(TYPE, "EXPIRED");
        notif.put(SEVERITY, DANGER);
        notif.put(PRODUCT_ID,
            p.getProductId());
        notif.put(PRODUCT_NAME,
            p.getProductName());
        notif.put(MESSAGE,
            "EXPIRED: " +
            p.getProductName() +
            " expired on " +
            expiryDate + "!");
        notif.put(EXPIRY_DATE,
            expiryDate.toString());
        notif.put("daysOverdue",
            Math.abs(daysUntilExpiry));
        return notif;
    }

    private Map<String, Object>
            buildExpiringSoonNotif(
                Product p,
                LocalDate expiryDate,
                long daysUntilExpiry) {

        Map<String, Object> notif =
            new HashMap<>();
        notif.put(TYPE, "EXPIRING_SOON");
        notif.put(SEVERITY,
            daysUntilExpiry == 0
            ? DANGER : WARNING);
        notif.put(PRODUCT_ID,
            p.getProductId());
        notif.put(PRODUCT_NAME,
            p.getProductName());
        notif.put(MESSAGE,
            "Expiring Soon: " +
            p.getProductName() +
            " expires on " +
            expiryDate +
            " (" + daysUntilExpiry +
            " days left)!");
        notif.put(EXPIRY_DATE,
            expiryDate.toString());
        notif.put(DAYS_LEFT, daysUntilExpiry);
        return notif;
    }
}