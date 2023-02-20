package org.laba.exception;

/**
 * The Error stores all custom errors for you to find the source of the error easily.
 * You can use this code to create predefined localized error messages and error codes.
 *
 *
 */
public enum Error {
        SAVE_ERROR(1L, "An error has occurred while saving record."),
        UPDATE_ERROR(2L, "An error has occurred while updating record."),
        REMOVE_BY_ID_ERROR(3L, "An error has occurred while removing record."),
        MAPPER_ERROR(4L, "An error has occurred while finding mappers.");

    private final long errorCode;
        private final String description;

        private Error(long errorCode, String description) {
            this.errorCode = errorCode;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public long getErrorCode() {
            return errorCode;
        }

        @Override
        public String toString() {
            return errorCode + ": " + description;
        }
}