package org.example.tacoonline.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tacoonline.data.OrderRepository;
import org.example.tacoonline.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

    final OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm(Model model) {
        //model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus status) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        Order saved = orderRepository.save(order);
        status.setComplete();
        log.info("Orden: " + saved);
        return "redirect:/";
    }
}
