package com.pongsky.cloud.config;


/**
 * hystrix 配置
 *
 * @author pengsenhao
 * @create 2021-02-19
 * @see <a href="https://github.com/Netflix/Hystrix/wiki/Configuration">hystrix configuration</a>
 */
public class HystrixConfigurationConfig {

    /**
     * 此属性设置时间，在此时间之后，调用者将观察到超时并退出命令执行。 Hystrix 将 HystrixCommand 标记为 TIMEOUT，并执行回退逻辑
     */
    public static final String TIMEOUT_IN_MILLISECONDS_PROP = "execution.isolation.thread.timeoutInMilliseconds";

    /**
     * 此属性设置时间，在此时间之后，调用者将观察到超时并退出命令执行。 Hystrix 将 HystrixCommand 标记为 TIMEOUT，并执行回退逻辑
     * 单位:ms
     * 默认:1000
     */
    public static final String TIMEOUT_IN_MILLISECONDS_VALUE = "5000";

    /**
     * 此属性在滚动窗口中设置将使电路跳闸的最小请求数
     */
    public static final String REQUEST_VOLUME_THRESHOLD_PROP = "circuitBreaker.requestVolumeThreshold";

    /**
     * 此属性在滚动窗口中设置将使电路跳闸的最小请求数
     * 单位:个
     * 默认:20
     */
    public static final String REQUEST_VOLUME_THRESHOLD_VALUE = "20";

    /**
     * 此属性设置电路跳闸后拒绝请求的时间，然后允许再次尝试确定是否应再次闭合电路
     */
    public static final String SLEEP_WINDOW_IN_MILLISECONDS_PROP = "circuitBreaker.sleepWindowInMilliseconds";

    /**
     * 此属性设置电路跳闸后拒绝请求的时间，然后允许再次尝试确定是否应再次闭合电路
     * 单位:ms
     * 默认:5000
     */
    public static final String SLEEP_WINDOW_IN_MILLISECONDS_VALUE = "5000";

    /**
     * 此属性设置错误百分比，电路应在该百分比以上触发跳闸并启动对后备逻辑的短路请求
     */
    public static final String ERROR_THRESHOLD_PERCENTAGE_PROP = "circuitBreaker.errorThresholdPercentage";

    /**
     * 此属性设置错误百分比，电路应在该百分比以上触发跳闸并启动对后备逻辑的短路请求
     * 单位:百分比
     * 默认:50
     */
    public static final String ERROR_THRESHOLD_PERCENTAGE_VALUE = "50";

}
