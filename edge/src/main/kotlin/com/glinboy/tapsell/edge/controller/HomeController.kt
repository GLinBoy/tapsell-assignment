package com.glinboy.tapsell.edge.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/")
class HomeController {

    @GetMapping
    fun getHome(response: HttpServletResponse) {
        response.sendRedirect("/swagger-ui.html")
    }
}