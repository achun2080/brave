package com.github.kristofa.brave;

import com.twitter.zipkin.gen.Span;

/**
 * Used for submitting server event information. Keeps state for each thread. </p> Depending on the implementation it can be
 * that each request is traced or it can be that only some requests are traced to avoid too much overhead. This is all
 * managed in the {@link ServerTracer} implementation. The user should not be aware.
 * 
 * @author kristof
 */
public interface ServerTracer extends AnnotationSubmitter {

    /**
     * Clears current span. When a thread pool is used this can be used to avoid you re-use previous information.
     */
    void clearCurrentSpan();

    /**
     * Sets the span we are part of.
     * 
     * @param traceId Trace id.
     * @param spanId Span id.
     * @param parentSpanId Parent span id. Can be <code>null</code> if not parent span is available.
     * @param name Span name.
     */
    void setSpan(final long traceId, final long spanId, final Long parentSpanId, final String name);

    /**
     * Sets server side span. This should be used if you have a span with annotations. For example when using
     * {@link ServerSpanThreadBinder}.
     * 
     * @param span Span.
     */
    void setSpan(final Span span);

    /**
     * Sets indication if we should trace/sample the current request.
     * 
     * @param sample <code>true</code> in case we should trace current request. <code>false</code> in case we should not
     *            trace current request, or <code>null</code> in case we did not get any information from client.
     */
    void setSample(final Boolean sample);

    /**
     * Sets server received event for current thread.
     */
    void setServerReceived();

    /**
     * Sets the server sent event for current thread.
     */
    void setServerSend();

}
