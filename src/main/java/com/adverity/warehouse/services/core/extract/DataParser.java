package com.adverity.warehouse.services.core.extract;

import java.io.InputStream;
import java.util.List;

public interface DataParser {

    List<String[]> parse(InputStream inputStream);
}
