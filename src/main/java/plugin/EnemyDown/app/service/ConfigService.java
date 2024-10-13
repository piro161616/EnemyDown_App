package plugin.EnemyDown.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import plugin.EnemyDown.app.DuplicateConfigException;
import plugin.EnemyDown.app.mapper.GameConfigMapper;
import plugin.EnemyDown.app.mapper.data.GameConfig;
import plugin.EnemyDown.app.mapper.data.SpawnEnemy;

@Service
public class ConfigService {

  public final GameConfigMapper mapper;

  public ConfigService(GameConfigMapper mapper) {
      this.mapper = mapper;
  }

  public List<GameConfig> searchConfigList() {
      return mapper.selectConfigList();
  }

  public GameConfig searchConfig(String difficulty) {
    return mapper.selectConfig(difficulty);
  }

  public List<SpawnEnemy> searchSpawnEnemyList(String difficulty) {
    return mapper.selectSpawnEnemyList(difficulty);
  }

  // 登録処理＝POST　→Mapperへ
  public GameConfig registerConfig(GameConfig config) throws Exception {
    if (searchConfig(config.getDifficulty()) != null) {
      throw new DuplicateConfigException("Duplicate Config Error!");
    }
    mapper.insertConfig(config);
    return mapper.selectConfig(config.getDifficulty());
  }

  // 更新処理＝UPDATE　→Mapperへ
  public List<SpawnEnemy> updateEnemyScore(SpawnEnemy enemy) {
    mapper.updateEnemyScore(enemy);
    return mapper.selectSpawnEnemyList(enemy.getDifficulty());
  }
}
