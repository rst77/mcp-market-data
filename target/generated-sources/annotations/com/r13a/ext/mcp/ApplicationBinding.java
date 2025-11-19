// This is a generated file (powered by Helidon). Do not edit or extend from this artifact as it is subject to change at any time!

package com.r13a.ext.mcp;

import java.util.List;

import io.helidon.common.Generated;
import io.helidon.common.config.ConfigFactory__ServiceDescriptor;
import io.helidon.common.mapper.DefaultMapperProvider__ServiceDescriptor;
import io.helidon.common.mapper.DefaultResolverService__ServiceDescriptor;
import io.helidon.common.mapper.MappersFactory__ServiceDescriptor;
import io.helidon.common.mapper.MappersImpl__ServiceDescriptor;
import io.helidon.common.types.TypeName;
import io.helidon.config.ConfigProvider__ServiceDescriptor;
import io.helidon.config.ConfigurationValueFactory__ServiceDescriptor;
import io.helidon.config.MetaConfigFactory__ServiceDescriptor;
import io.helidon.config.PropertiesConfigParser;
import io.helidon.config.yaml.YamlConfigParser;
import io.helidon.http.Headers__ServiceDescriptor;
import io.helidon.http.HttpPrologue__ServiceDescriptor;
import io.helidon.http.ServerRequestHeaders__ServiceDescriptor;
import io.helidon.service.registry.Binding;
import io.helidon.service.registry.DependencyPlanBinder;
import io.helidon.service.registry.EventManagerImpl__ServiceDescriptor;
import io.helidon.service.registry.InterceptionMetadata__ServiceDescriptor;
import io.helidon.service.registry.PerRequestScopeHandler__ServiceDescriptor;
import io.helidon.service.registry.Scopes__ServiceDescriptor;
import io.helidon.service.registry.Service;
import io.helidon.service.registry.ServiceLoader__ServiceDescriptor;
import io.helidon.service.registry.ServiceRegistryConfig;
import io.helidon.service.registry.ServiceRegistry__ServiceDescriptor;
import io.helidon.webclient.api.DefaultErrorHandling__ServiceDescriptor;
import io.helidon.webserver.LoomServer__ServiceDescriptor;
import io.helidon.webserver.RequestScopeFeatureProvider__ServiceDescriptor;
import io.helidon.webserver.WebServerService__ServiceDescriptor;
import io.helidon.webserver.WebServerStarterService__ServiceDescriptor;
import io.helidon.webserver.http.HttpEntryPointsImpl__ServiceDescriptor;
import io.helidon.webserver.http.ServerRequest__ServiceDescriptor;
import io.helidon.webserver.http.ServerResponse__ServiceDescriptor;

/**
 * Generated Binding to provide explicit bindings for known services.
 */
@Generated(value = "io.helidon.service.maven.plugin.BindingGenerator", trigger = "io.helidon.service.maven.plugin.BindingGenerator")
class ApplicationBinding implements Binding {

    private static final List<Double> RUN_LEVELS = List.of(Service.RunLevel.SERVER
    );
    private static final TypeName PROVIDER_0 = TypeName.create("io.helidon.config.spi.ConfigParser");

    private ApplicationBinding() {
    }

    /**
     * Create a new application binding instance.
     */
    static ApplicationBinding create() {
        return new ApplicationBinding();
    }

    @Override
    public String name() {
        return "unnamed/com.r13a.ext.mcp";
    }

    @Override
    @SuppressWarnings("deprecation")
    public void binding(DependencyPlanBinder binder) {
        binder.interceptors();
        
        binder.service(DefaultResolverService__ServiceDescriptor.INSTANCE)
            .bind(DefaultResolverService__ServiceDescriptor.DEP_0, MappersFactory__ServiceDescriptor.INSTANCE, MappersImpl__ServiceDescriptor.INSTANCE);
        
        binder.service(MappersFactory__ServiceDescriptor.INSTANCE)
            .bind(MappersFactory__ServiceDescriptor.DEP_0, DefaultMapperProvider__ServiceDescriptor.INSTANCE)
            .bind(MappersFactory__ServiceDescriptor.DEP_1);
        
        binder.service(ConfigProvider__ServiceDescriptor.INSTANCE)
            .bind(ConfigProvider__ServiceDescriptor.DEP_0, MetaConfigFactory__ServiceDescriptor.INSTANCE)
            .bind(ConfigProvider__ServiceDescriptor.DEP_1)
            .bind(ConfigProvider__ServiceDescriptor.DEP_2, ServiceLoader__ServiceDescriptor.create(TypeName.create("io.helidon.config.spi.ConfigParser"), PropertiesConfigParser.class, PropertiesConfigParser::new, 90.0), ServiceLoader__ServiceDescriptor.create(TypeName.create("io.helidon.config.spi.ConfigParser"), YamlConfigParser.class, YamlConfigParser::new, 90.0))
            .bind(ConfigProvider__ServiceDescriptor.DEP_3)
            .bind(ConfigProvider__ServiceDescriptor.DEP_4);
        
        binder.service(ConfigurationValueFactory__ServiceDescriptor.INSTANCE)
            .bind(ConfigurationValueFactory__ServiceDescriptor.DEP_0, DefaultResolverService__ServiceDescriptor.INSTANCE)
            .bind(ConfigurationValueFactory__ServiceDescriptor.DEP_1, ConfigProvider__ServiceDescriptor.INSTANCE, ConfigFactory__ServiceDescriptor.INSTANCE);
        
        binder.service(EventManagerImpl__ServiceDescriptor.INSTANCE)
            .bind(EventManagerImpl__ServiceDescriptor.DEP_0)
            .bind(EventManagerImpl__ServiceDescriptor.DEP_1);
        
        binder.service(DefaultErrorHandling__ServiceDescriptor.INSTANCE)
            .bind(DefaultErrorHandling__ServiceDescriptor.DEP_0);
        
        binder.service(LoomServer__ServiceDescriptor.INSTANCE)
            .bind(LoomServer__ServiceDescriptor.DEP_0, WebServerService__ServiceDescriptor.INSTANCE);
        
        binder.service(RequestScopeFeatureProvider__ServiceDescriptor.INSTANCE)
            .bind(RequestScopeFeatureProvider__ServiceDescriptor.DEP_0, Scopes__ServiceDescriptor.INSTANCE);
        
        binder.service(WebServerService__ServiceDescriptor.INSTANCE)
            .bind(WebServerService__ServiceDescriptor.DEP_0, ConfigProvider__ServiceDescriptor.INSTANCE, ConfigFactory__ServiceDescriptor.INSTANCE)
            .bind(WebServerService__ServiceDescriptor.DEP_1)
            .bind(WebServerService__ServiceDescriptor.DEP_2, McpServer__McpServer__ServiceDescriptor.INSTANCE)
            .bind(WebServerService__ServiceDescriptor.DEP_3)
            .bind(WebServerService__ServiceDescriptor.DEP_4)
            .bind(WebServerService__ServiceDescriptor.DEP_5)
            .bind(WebServerService__ServiceDescriptor.DEP_6)
            .bind(WebServerService__ServiceDescriptor.DEP_7)
            .bind(WebServerService__ServiceDescriptor.DEP_8)
            .bind(WebServerService__ServiceDescriptor.DEP_9);
        
        binder.service(WebServerStarterService__ServiceDescriptor.INSTANCE)
            .bind(WebServerStarterService__ServiceDescriptor.DEP_0, LoomServer__ServiceDescriptor.INSTANCE)
            .bind(WebServerStarterService__ServiceDescriptor.DEP_1, ConfigurationValueFactory__ServiceDescriptor.INSTANCE);
        
        binder.service(HttpEntryPointsImpl__ServiceDescriptor.INSTANCE)
            .bind(HttpEntryPointsImpl__ServiceDescriptor.DEP_0)
            .bind(HttpEntryPointsImpl__ServiceDescriptor.DEP_1);
    }

    @Override
    public void configure(ServiceRegistryConfig.Builder builder) {
        builder.runLevels(RUN_LEVELS);
        builder.addServiceDescriptor(ServiceLoader__ServiceDescriptor.create(PROVIDER_0,
                    PropertiesConfigParser.class,
                    () -> new PropertiesConfigParser(),
                    90.0));
        builder.addServiceDescriptor(ServiceLoader__ServiceDescriptor.create(PROVIDER_0,
                    YamlConfigParser.class,
                    () -> new YamlConfigParser(),
                    90.0));
        
        builder.addServiceDescriptor(McpServer__McpServer__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ConfigFactory__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(DefaultMapperProvider__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(DefaultResolverService__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(MappersFactory__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(MappersImpl__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ConfigProvider__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ConfigurationValueFactory__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(MetaConfigFactory__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(Headers__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(HttpPrologue__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ServerRequestHeaders__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(EventManagerImpl__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(InterceptionMetadata__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(PerRequestScopeHandler__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(Scopes__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ServiceRegistry__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(DefaultErrorHandling__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(LoomServer__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(RequestScopeFeatureProvider__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(WebServerService__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(WebServerStarterService__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(HttpEntryPointsImpl__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ServerRequest__ServiceDescriptor.INSTANCE);
        builder.addServiceDescriptor(ServerResponse__ServiceDescriptor.INSTANCE);
    }

}
