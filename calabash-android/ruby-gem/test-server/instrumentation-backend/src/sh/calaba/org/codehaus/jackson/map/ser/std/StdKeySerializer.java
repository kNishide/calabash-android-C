package sh.calaba.org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import sh.calaba.org.codehaus.jackson.JsonGenerationException;
import sh.calaba.org.codehaus.jackson.JsonGenerator;
import sh.calaba.org.codehaus.jackson.JsonNode;
import sh.calaba.org.codehaus.jackson.map.JsonMappingException;
import sh.calaba.org.codehaus.jackson.map.SerializerProvider;
import sh.calaba.org.codehaus.jackson.map.ser.std.SerializerBase;

/**
 * Specialized serializer that can be used as the generic key
 * serializer, when serializing {@link java.util.Map}s to JSON
 * Objects.
 */
public class StdKeySerializer
    extends SerializerBase<Object>
{
    final static StdKeySerializer instace = new StdKeySerializer();

    public StdKeySerializer() { super(Object.class); }
    
    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value instanceof Date) {
            provider.defaultSerializeDateKey((Date) value, jgen);
        } else {
            jgen.writeFieldName(value.toString());
        }
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint)
        throws JsonMappingException
    {
        return createSchemaNode("string");
    }
}
