package sh.calaba.org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import sh.calaba.org.codehaus.jackson.JsonGenerationException;
import sh.calaba.org.codehaus.jackson.JsonGenerator;
import sh.calaba.org.codehaus.jackson.JsonNode;
import sh.calaba.org.codehaus.jackson.map.SerializationConfig;
import sh.calaba.org.codehaus.jackson.map.SerializerProvider;
import sh.calaba.org.codehaus.jackson.map.annotate.JacksonStdImpl;

/**
 * For efficiency, we will serialize Dates as longs, instead of
 * potentially more readable Strings.
 *<p>
 * @since 1.9 (moved from 'sh.calaba.org.codehaus.jackson.map.ser.StdSerializers#UtilDateSerializer}
 */
@JacksonStdImpl
public class DateSerializer
    extends ScalarSerializerBase<java.util.Date>
{
    public static DateSerializer instance = new DateSerializer();
    
    public DateSerializer() { super(Date.class); }

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        provider.defaultSerializeDateValue(value, jgen);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint)
    {
        //todo: (ryan) add a format for the date in the schema?
        return createSchemaNode(provider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)
                ? "number" : "string", true);
    }
}
