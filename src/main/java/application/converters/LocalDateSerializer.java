package application.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sara.so
 */
@FacesConverter(value = "localDateConverter")
public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
        generator.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
