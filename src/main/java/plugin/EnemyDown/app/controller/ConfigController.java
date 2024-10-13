package plugin.EnemyDown.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plugin.EnemyDown.app.DuplicateConfigException;
import plugin.EnemyDown.app.mapper.data.GameConfig;
import plugin.EnemyDown.app.mapper.data.SpawnEnemy;
import plugin.EnemyDown.app.service.ConfigService;

@RestController
public class ConfigController {

  private final ConfigService service;

  public ConfigController(ConfigService service) {
    this.service = service;
  }

  @GetMapping(value = "/configList")
  public List<GameConfig> searchConfigList() {
    return service.searchConfigList();
  }

  @GetMapping(value = "/config")
  public GameConfig searchConfig(@RequestParam String difficulty) {
    return service.searchConfig(difficulty);
  }

  @GetMapping(value = "/spawnEnemyList")
  public List<SpawnEnemy> spawnEnemyList(@RequestParam String difficulty) {
    return service.searchSpawnEnemyList(difficulty);
  }

  // 登録処理＝POST　→service→Mapperへ
  @PostMapping(value = "/config")
  public ResponseEntity<GameConfig> registerConfig(@RequestBody GameConfig config)
      throws Exception {
    GameConfig registerConfig = service.registerConfig(config);
    return new ResponseEntity<>(config, HttpStatus.OK);
  }

  // 更新処理＝UPDATE　→service→Mapperへ
  @PostMapping(value = "/updateEnemyScore")
  public ResponseEntity<List<SpawnEnemy>> updateEnemyScore(@RequestBody SpawnEnemy enemy) {
    List<SpawnEnemy> updatedSpawnEnemyList = service.updateEnemyScore(enemy);
    return new ResponseEntity<>(updatedSpawnEnemyList, HttpStatus.OK);
  }

  // 例外処理の出力内容
  @ExceptionHandler(value = DuplicateConfigException.class)
  public ResponseEntity<Map<String, String>> handleDuplicateConfig(
      DuplicateConfigException e, HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
        "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        "message", e.getMessage(),
        "path", request.getRequestURI());
    return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
