package club.luyang.platform.uac;

import club.luyang.boot.i18n.I18nUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lu_yang
 */
@SpringBootApplication
public class UacApplication {

    public static void main(String[] args) {
        SpringApplication.run(UacApplication.class, args);
        System.out.println(I18nUtil.get("SERVER.EXCEPTION"));
    }
}
