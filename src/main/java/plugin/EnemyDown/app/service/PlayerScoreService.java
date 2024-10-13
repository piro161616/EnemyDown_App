package plugin.EnemyDown.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import plugin.EnemyDown.app.mapper.PlayerScoreMapper;
import plugin.EnemyDown.app.mapper.data.PlayerScore;

@Service
public class PlayerScoreService {

  private final PlayerScoreMapper mapper;

  public PlayerScoreService(PlayerScoreMapper mapper) {
    this.mapper = mapper;
  }

  public List<PlayerScore> searchPlayerScoreLst() {
    return mapper.selectPlayerScoreList();
  }

}
