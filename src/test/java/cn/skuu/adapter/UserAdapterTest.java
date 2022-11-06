package cn.skuu.adapter;

import cn.skuu.pojo.vo.UserVo;
import cn.skuu.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAdapterTest {

    @Autowired
    private UserAdapter userAdapter;

    @Test
    void userToUserVo() {
        User build = User.builder()
                .userId("212")
                .status(1)
                .roomId("222")
                .build();
        UserVo userVo = userAdapter.userToUserVo(build);
        System.out.println(userVo);
    }
}