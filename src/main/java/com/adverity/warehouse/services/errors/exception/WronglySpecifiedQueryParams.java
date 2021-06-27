package com.adverity.warehouse.services.errors.exception;

import static com.adverity.warehouse.services.errors.ErrorStatus.WRONG_QUERY_PARAMS;

public class WronglySpecifiedQueryParams extends RuntimeException {
    public WronglySpecifiedQueryParams() {
        super(WRONG_QUERY_PARAMS);
    }
}
