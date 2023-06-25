package club.luyang.framework.web.i18n;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Arrays;

/**
 * 重写语言变更拦截器
 *
 * @author lu_yang
 */
@SuppressWarnings("all")
public class RedefineLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        String newLocale = request.getHeader(this.getParamName());
        if (CharSequenceUtil.isNotBlank(newLocale) && this.checkHttpMethod(request.getMethod())) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            Assert.notNull(localeResolver, () -> new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?"));

            try {
                localeResolver.setLocale(request, response, super.parseLocaleValue(newLocale));
            } catch (IllegalArgumentException e) {
                Assert.isTrue(super.isIgnoreInvalidLocale(), () -> e);
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + e.getMessage());
                }
            }
        }

        return true;
    }

    private boolean checkHttpMethod(String currentMethod) {
        String[] httpMethods = super.getHttpMethods();
        if (ArrayUtil.isEmpty(httpMethods)) {
            return true;
        }

        return Arrays.stream(httpMethods).anyMatch(v -> v.equalsIgnoreCase(currentMethod));
    }
}
