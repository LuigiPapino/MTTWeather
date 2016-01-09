package net.dragora.mttweather.data.schematicProvider;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by ttuo on 14/07/15.
 */
public interface SearchCityColumns {
    @DataType(DataType.Type.TEXT) @PrimaryKey
    String SEARCH = "id";
    @DataType(DataType.Type.TEXT) String JSON = "json";
}
