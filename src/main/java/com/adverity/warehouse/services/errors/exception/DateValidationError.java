package com.adverity.warehouse.services.errors.exception;

import static com.adverity.warehouse.services.errors.ErrorStatus.WRONG_DATES;

public class DateValidationError extends RuntimeException {
    public DateValidationError() {
        super(WRONG_DATES);
    }
}
