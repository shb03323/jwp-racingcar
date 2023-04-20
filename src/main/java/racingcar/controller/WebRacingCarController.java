package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.GameService;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;

import java.net.URI;
import java.util.List;

@RestController
public class WebRacingCarController {

    private final GameService gameService;

    @Autowired
    public WebRacingCarController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto request) {
        final GameResponseDto gameResponseDto = gameService.createGameResult(request);
        return ResponseEntity.created(URI.create("/plays")).body(gameResponseDto);
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameResponseDto>> getAllGames() {
        final List<GameResponseDto> gameResponseDtos = gameService.getAll();
        return ResponseEntity.ok().body(gameResponseDtos);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.warn(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
