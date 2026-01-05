package com.example.gameRecommender.mapper;

import com.example.gameRecommender.model.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("replace into games(gameName,gameTime,steamId) values(#{gameName},${gameTime},#{steamId})")
        public void insert(Game game);

    @Select("select * from games where steamId = #{steamid}")
        public List<Game> select(String steamid);

    @Select("SELECT COUNT(*) FROM games")
    public int count();

    @Select("SELECT * FROM games limit #{start},#{pageSize}")
    public List<Game> page(@Param("start")Integer start, @Param("pageSize")Integer pageSize);
}
