package com.csii.meter.console.configuration;

import com.csii.meter.console.config.MeterConsoleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 控制台配置类
 */
@Configuration
@EnableConfigurationProperties(MeterConsoleProperties.class)
public class MeterConsoleConfiguration {
}
