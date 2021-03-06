package com.tz.client2.feign.config;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @author https://github.com/TianPuJun @醉鱼
 * @ClassName KeepErrMsgConfiguration
 * @Description 不保留错误信息，feign 服务异常不进入熔断
 * @Date 22:21 2021/2/8
 **/
public class NotBreakerConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }
    /**
     * 自定义错误
     */
    @Slf4j
    public static class UserErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            try {
                // 获取原始的返回内容
                String json = Util.toString(response.body().asReader());
                exception = new RuntimeException(json);
                log.error("发生异常[{}]",json);
                exception = new HystrixBadRequestException("自定义异常信息:"+json);

            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;
        }
    }
}
