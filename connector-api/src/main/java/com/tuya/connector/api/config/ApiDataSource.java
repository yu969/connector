package com.tuya.connector.api.config;

import com.tuya.connector.api.context.ContextManager;
import com.tuya.connector.api.error.ErrorProcessorRegister;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.api.token.TokenManager;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/1/18 8:51 下午
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiDataSource {
    public static ApiDataSourceBuilder DEFAULT_BUILDER;

    static {
        DEFAULT_BUILDER  = ApiDataSource.builder()
            .timeout(Timeout.DEFAULT_BUILDER.build())
            .connectionPool(ConnectionPool.DEFAULT_BUILDER.build())
            .loggingLevel(Logging.Level.INFO)
            .loggingStrategy(Logging.Strategy.NONE);
        ErrorProcessorRegister errorProcessorRegister = new ErrorProcessorRegister();
        DEFAULT_BUILDER.errorProcessorRegister(errorProcessorRegister);
    }

    String baseUrl;
    String ak;
    String sk;

    static ThreadLocal<String> currentAk = new InheritableThreadLocal<>();
    static ThreadLocal<String> currentSk = new InheritableThreadLocal<>();

    public String getAk() {
        if (Objects.nonNull(currentAk) && currentAk.get() != null) {
            return currentAk.get();
        }
        return ak;
    }

    public String getSk() {
        if (Objects.nonNull(currentSk) && currentSk.get() != null) {
            return currentSk.get();
        }
        return sk;
    }

    public void clear() {
        currentAk.remove();
        currentSk.remove();
    }

    public void setAk(String ak) {
        this.ak = ak;
        currentAk.set(ak);
    }

    public void setSk(String sk) {
        this.sk = sk;
        currentSk.set(sk);
    }

    public ApiDataSource(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ApiDataSource(String baseUrl, String ak, String sk) {
        this.baseUrl = baseUrl;
        this.ak = ak;
        this.sk = sk;
    }

    Timeout timeout;

    ConnectionPool connectionPool;

    Logging.Level loggingLevel;

    Logging.Strategy loggingStrategy;

    boolean autoSetHeader;

    boolean autoRefreshToken;

    HeaderProcessor headerProcessor;

    TokenManager<?> tokenManager;

    ContextManager contextManager;

    ErrorProcessorRegister errorProcessorRegister;
}
