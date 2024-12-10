package org.example.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderDTO;
import org.example.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
        notificationService.listen(order);
        return ResponseEntity.ok("Notification is send!!!");
    }
}
