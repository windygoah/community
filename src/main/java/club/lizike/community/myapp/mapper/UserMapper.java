package club.lizike.community.myapp.mapper;

import club.lizike.community.myapp.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,password) values (#{name},#{password})")
    Integer insert(@Param("name") String name,@Param("password") String password);
    /**
     * 验证用户名是否存在
     * 返回值为用户id
     */
    @Select("SELECT id FROM USER WHERE `name` = #{name}")
    Integer selectByName(@Param("name") String name);
    /**
     * 通过用户名和密码查找用户（验证登录信息是否正确）
     */
    @Select("SELECT * FROM USER WHERE `name`=#{name} AND `password`=#{password}")
    Integer findByNameAndPassword(@Param("name") String name,@Param("password") String password);
    /**
     * 通过用户名查找用户信息
     */
    @Select("SELECT * FROM USER WHERE `name`=#{name}")
    User findUserInfoByName(@Param("name") String name);
}
