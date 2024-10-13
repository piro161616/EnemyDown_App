package plugin.EnemyDown.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plugin.EnemyDown.app.mapper.data.PlayerScore;

@Mapper
public interface PlayerScoreMapper {

  @Select("select * from player_score order by id")
  List<PlayerScore> selectPlayerScoreList();

}
