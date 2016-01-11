package net.dragora.mttweather.data.schematicProvider;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;


public interface SearchCityColumns {
    @DataType(DataType.Type.TEXT) @PrimaryKey
    String SEARCH = "id";
    @DataType(DataType.Type.TEXT) String JSON = "json";
}
