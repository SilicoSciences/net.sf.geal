package net.sf.ga;

public class ExceptionRuntimeGA extends RuntimeException {

    public ExceptionRuntimeGA() {
        super();

    }

    public ExceptionRuntimeGA(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public ExceptionRuntimeGA(final String message, final Throwable cause) {
        super(message, cause);

    }

    public ExceptionRuntimeGA(final String message) {
        super(message);

    }

    public ExceptionRuntimeGA(final Throwable cause) {
        super(cause);

    }

}
