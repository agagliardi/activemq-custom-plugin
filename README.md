# activemq-custom-plugin

Install with `osgi:install  mvn:com.redhat.gss/custom-broker-plugin/1.0-SNAPSHOT`


Include in activemq.xml#plugins

`        <bean xmlns="http://www.springframework.org/schema/beans" 
            id="customPlugin" 
            class="com.redhat.gss.amq6.plugin.custom.CustomBrokerPlugin"/>
`