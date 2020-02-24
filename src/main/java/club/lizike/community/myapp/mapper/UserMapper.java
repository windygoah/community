package club.lizike.community.myapp.mapper;

import club.lizike.community.myapp.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name) values (#{name})")
    void insert(User user);
}
