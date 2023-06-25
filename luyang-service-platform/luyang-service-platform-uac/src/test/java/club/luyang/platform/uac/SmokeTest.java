package club.luyang.platform.uac;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 冒烟测试
 *
 * @author lu_yang
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = UacApplication.class)
class SmokeTest {

    @Resource
    private MockMvc mockMvc;

    /**
     * 健康检查<br/>
     * 当请求健康端点时应该返回 ok(200)
     *
     * @author lu_yang
     */
    @Test
    void should_return_ok_when_request_endpoint_of_health() throws Exception {
        mockMvc
            .perform(get("/actuator/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"));
    }
}
