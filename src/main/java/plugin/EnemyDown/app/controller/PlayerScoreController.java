package plugin.EnemyDown.app.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plugin.EnemyDown.app.mapper.data.PlayerScore;
import plugin.EnemyDown.app.service.PlayerScoreService;

@RestController
public class PlayerScoreController {

  private final PlayerScoreService service;

  public PlayerScoreController(PlayerScoreService service) {
    this.service = service;
  }

  @RequestMapping(value = "/playerScoreList", method = RequestMethod.GET)
  public List<PlayerScore>playerScoreList() {
    return service.searchPlayerScoreLst();
  }
}
