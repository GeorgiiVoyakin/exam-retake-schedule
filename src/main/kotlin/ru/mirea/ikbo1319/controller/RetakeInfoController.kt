package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.service.RetakeInfoService

@RestController("/api/schedule/retake_info")
class RetakeInfoController(private val retakeInfoService: RetakeInfoService) {
}