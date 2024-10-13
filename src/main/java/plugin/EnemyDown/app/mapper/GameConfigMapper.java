package plugin.EnemyDown.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import plugin.EnemyDown.app.mapper.data.GameConfig;
import plugin.EnemyDown.app.mapper.data.SpawnEnemy;

@Mapper
public interface GameConfigMapper {

  @Select("select * from game_config order by id")
  List<GameConfig> selectConfigList();

  @Select("select * from game_config where difficulty = #{difficulty} order by id asc")
  GameConfig selectConfig(String difficulty);

  @Select("select * from spawn_enemy inner join game_config on spawn_enemy.difficulty = game_config.difficulty where spawn_enemy.difficulty = #{difficulty} order by spawn_enemy.id asc")
  List<SpawnEnemy> selectSpawnEnemyList(String difficulty);

  // 登録処理＝POST　MYSQLへ
  @Insert("insert game_config(game_time, difficulty) values(#{gameTime}, #{difficulty})")
  int insertConfig(GameConfig config);

  // 更新処理＝UPDATE　MYSQLへ
  @Update("update spawn_enemy set score = #{score} where enemy_name = #{enemyName} and difficulty = #{difficulty}")
  int updateEnemyScore(SpawnEnemy enemy);
}
