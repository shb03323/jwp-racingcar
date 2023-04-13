package racingcar.dto.request;

import java.time.LocalDateTime;
import java.util.Objects;

public class GameSaveDto {
    private final String winners;
    private final int trialCount;
    private final LocalDateTime createdAt;

    public GameSaveDto(final String winners, final int trialCount) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.createdAt = LocalDateTime.now();
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
