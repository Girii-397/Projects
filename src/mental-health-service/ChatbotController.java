package com.healthcare.mental;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/mental")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatbotService.respond(request);
    }

    @GetMapping("/stress/{patientId}")
    public StressData getStressData(@PathVariable UUID patientId) {
        return chatbotService.getStressData(patientId);
    }
}