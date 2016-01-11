package net.dragora.mttweather.data.schematicProvider;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by nietzsche on 10/01/16.
 */
public interface JsonKeyColumns {
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String ID = "id";
    @DataType(DataType.Type.TEXT)
    String JSON = "json";
}
