package plugin.EnemyDown.app.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class GameConfig {
  private int id;
  private int gameTime;
  private String difficulty;
}
