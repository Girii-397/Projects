package com.healthcare.metaverse;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/metaverse")
public class MetaverseController {

    @Autowired
    private MetaverseService metaverseService;

    @PostMapping("/session/start")
    public MetaverseSession startSession(@RequestBody SessionRequest request) {
        return metaverseService.startSession(request);
    }

    @GetMapping("/session/{sessionId}")
    public MetaverseSession getSession(@PathVariable UUID sessionId) {
        return metaverseService.getSessionData(sessionId);
    }
}