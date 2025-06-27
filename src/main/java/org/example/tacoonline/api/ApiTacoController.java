package org.example.tacoonline.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tacoonline.data.TacoRepository;
import org.example.tacoonline.model.Taco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/taco")
public class ApiTacoController {

    private final TacoRepository tacoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Taco> postTaco(Taco taco) {
        Taco saved  = tacoRepository.save(taco);
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
