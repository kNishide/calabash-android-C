package sh.calaba.org.codehaus.jackson.map.deser.std;

import java.io.IOException;

import sh.calaba.org.codehaus.jackson.JsonParser;
import sh.calaba.org.codehaus.jackson.JsonProcessingException;
import sh.calaba.org.codehaus.jackson.map.DeserializationContext;
import sh.calaba.org.codehaus.jackson.map.TypeDeserializer;
import sh.calaba.org.codehaus.jackson.type.JavaType;

/**
 * Base class for deserializers that handle types that are serialized
 * as JSON scalars (non-structured, i.e. non-Object, non-Array, values).
 * 
 * @since 1.9 (moved from higher-level package)
 */
public abstract class StdScalarDeserializer<T> extends StdDeserializer<T>
{
    protected StdScalarDeserializer(Class<?> vc) {
        super(vc);
    }

    protected StdScalarDeserializer(JavaType valueType) {
        super(valueType);
    }
    
    @Override
    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt,
            TypeDeserializer typeDeserializer)
        throws IOException, JsonProcessingException
    {
        return typeDeserializer.deserializeTypedFromScalar(jp, ctxt);
    }
}
