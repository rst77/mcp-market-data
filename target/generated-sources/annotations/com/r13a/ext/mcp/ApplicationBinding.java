// This is a generated file (powered by Helidon). Do not edit or extend from this artifact as it is subject to change at any time!

package com.r13a.ext.mcp;

import io.helidon.common.Generated;
import io.helidon.service.registry.EmptyBinding;
import io.helidon.service.registry.ServiceRegistryConfig;

@Generated(value = "io.helidon.service.codegen.ServiceBindingCodegenProvider.ServiceBindingCodegen", trigger = "com.r13a.ext.mcp.Main")
class ApplicationBinding extends EmptyBinding {

    private ApplicationBinding() {
        super("The Unnamed Module/com.r13a.ext.mcp");
    }

    static ApplicationBinding create() {
        return new ApplicationBinding();
    }

    @Override
    public void configure(ServiceRegistryConfig.Builder builder) {
        warnEmpty();
        builder.discoverServices(true);
        builder.discoverServicesFromServiceLoader(true);
    }

}
