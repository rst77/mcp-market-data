// This is a generated file (powered by Helidon). Do not edit or extend from this artifact as it is subject to change at any time!

package com.r13a.ext.mcp;

import java.util.List;
import java.util.Set;

import io.helidon.common.Generated;
import io.helidon.common.types.Annotation;
import io.helidon.common.types.ResolvedType;
import io.helidon.common.types.TypeName;
import io.helidon.service.registry.Dependency;
import io.helidon.service.registry.DependencyContext;
import io.helidon.service.registry.InterceptionMetadata;
import io.helidon.service.registry.Qualifier;
import io.helidon.service.registry.Service;
import io.helidon.service.registry.ServiceDescriptor;

/**
 * Service descriptor for {@link com.r13a.ext.mcp.McpServer__McpServer}.
 *
 * @param <T> type of the service, for extensibility
 */
@Generated(value = "io.helidon.service.codegen.ServiceExtension", trigger = "com.r13a.ext.mcp.McpServer__McpServer")
public class McpServer__McpServer__ServiceDescriptor<T extends com.r13a.ext.mcp.McpServer__McpServer> implements ServiceDescriptor<T> {

    /**
     * Global singleton instance for this descriptor.
     */
    public static final McpServer__McpServer__ServiceDescriptor<McpServer__McpServer> INSTANCE = new McpServer__McpServer__ServiceDescriptor<>();
    private static final System.Logger LOGGER = System.getLogger("com.r13a.ext.mcp.McpServer__McpServer");
    private static final TypeName SERVICE_TYPE = TypeName.create("com.r13a.ext.mcp.McpServer__McpServer");
    private static final TypeName TYPE = TypeName.create("com.r13a.ext.mcp.McpServer__McpServer__ServiceDescriptor");
    private static final Set<ResolvedType> CONTRACTS = Set.of(ResolvedType.create("java.util.function.Supplier<?>"), ResolvedType.create("io.helidon.webserver.http.HttpFeature"), ResolvedType.create("java.util.function.Supplier<io.helidon.webserver.http.HttpFeature>"), ResolvedType.create("io.helidon.webserver.ServerLifecycle"));
    private static final Set<Qualifier> QUALIFIERS = Set.of();
    private static final List<Dependency> DEPENDENCIES = List.of();
    static final List<Annotation> ANNOTATIONS = List.of(Annotation.builder()
            .typeName(TypeName.create("io.helidon.service.registry.Service.Singleton"))
            .addMetaAnnotation(Annotation.create(TypeName.create("io.helidon.service.registry.Service.Scope")))
            .build()
    );

    /**
     * Constructor with no side effects
     */
    protected McpServer__McpServer__ServiceDescriptor() {
    }

    @Override
    public TypeName serviceType() {
        return SERVICE_TYPE;
    }

    @Override
    public TypeName descriptorType() {
        return TYPE;
    }

    @Override
    public TypeName scope() {
        return Service.Singleton.TYPE;
    }

    @Override
    public Set<ResolvedType> contracts() {
        return CONTRACTS;
    }

    @Override
    public McpServer__McpServer instantiate(DependencyContext ctx__helidonInject, InterceptionMetadata interceptMeta__helidonInject) {
        if (LOGGER.isLoggable(System.Logger.Level.DEBUG)) {
                LOGGER.log(System.Logger.Level.DEBUG, "instantiate (weight = " + weight() + ", run level = " + runLevel().orElse(null) + ")");
        }
        return new McpServer__McpServer();
    }

}
