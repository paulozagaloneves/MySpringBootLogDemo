package com.example.arch.logger;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;


/**
 * Appends log events to Azure Event Hub.
 * 
 */
@Plugin(name = "EventHubAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class EventHubAppender extends AbstractAppender {

    private String _prefix1;
    private String _prefix2;
    
    EventHubProducerClient producer;


    protected EventHubAppender(
            String name,
            Filter filter,
            Layout<? extends Serializable> layout,
            final boolean ignoreExceptions,
            final Property[] properties,
            String connectionString,
            String prefix1,
            String prefix2) throws Exception {

        super(name, filter, layout, ignoreExceptions, properties);
        producer = new EventHubClientBuilder()
        	    .connectionString(connectionString)
        	    .buildProducerClient();
        _prefix1 = prefix1;
        _prefix2 = prefix2;
    }

    @Override
    public void append(LogEvent event) {
    	byte[] eventBuffer = getLayout().toByteArray(event);
    	String eventString = new String(eventBuffer);
    	EventData data = new EventData(eventString);
    	System.out.println(eventString);
    	this.producer.send(Arrays.asList(data));
    }

    

    /**
     * Create AzureBlobAppender.
     * 
     * @param name The name of the Appender.
     * @param webapps WebApps mode. If this value is true, assume it is running on WebApps.
     * @param accountName Azure storage account name. It becomes effective when WebApps is false.
     * @param accountKey Azure storage account key. It becomes effective when WebApps is false.
     * @param containerName The name of blob container. It becomes effective when WebApps is false.
     * @param prefix1 Specify directory structure. It becomes effective when WebApps is false.
     * @param prefix2 Specify directory structure. It becomes effective when WebApps is false. Can be null, empty or unset.
     * @param layout The layout to format the message.
     * @param filter The filter to filter the message.
     * @return AzureBlobAppender instance.
     */
    @PluginFactory
    public static EventHubAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("connectionString") final String connectionString,
            @PluginAttribute("prefix1") String prefix1,
            @PluginAttribute("prefix2") String prefix2,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        failIfNullOrEmpty(connectionString, "connectionString");
        failIfNullOrEmpty(prefix1, "prefix1");

        try {
			return new EventHubAppender(name, filter, layout, true, Property.EMPTY_ARRAY, connectionString , prefix1, prefix2) {};
		} catch (Exception e) {
			 throw new RuntimeException("Azure Event Hub is invalid.", e);
		}

    }

    private static String getProperty(String key) {
        String value = System.getenv(key);
        if (!isNullOrEmpty(value)) {
            return value;
        }
        value = System.getProperty(key);
        return value;
    }

    private static String getPropertyOrFail(String key) {
        String value = getProperty(key);

        failIfNullOrEmpty(value, key);
        return value;
    }

    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static void failIfNullOrEmpty(String value, String key) {
        boolean result = value == null || value.trim().isEmpty();
        if(result) {
            throw new RuntimeException("Mandatory parameter missing: "+key);
        }
    }
}
