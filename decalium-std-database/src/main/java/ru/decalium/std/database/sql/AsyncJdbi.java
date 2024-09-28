package ru.decalium.std.database.sql;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.HandleConsumer;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class AsyncJdbi {

    private final ExecutorService executor;
    private final Jdbi jdbi;
    private final Logger logger;

    public AsyncJdbi(ExecutorService executor, Jdbi jdbi, Logger logger) {

        this.executor = executor;
        this.jdbi = jdbi;
        this.logger = logger;
    }

    @CanIgnoreReturnValue
    public <X extends Exception> CompletableFuture<?> useHandle(final HandleConsumer<X> consumer) {
        return CompletableFuture.runAsync(() -> {
            try {
                jdbi.useHandle(consumer);
            } catch(Exception e) {
                exception(e);
                throw new RuntimeException(e);
            }
        }, executor);
    }
    @CanIgnoreReturnValue
    public <X extends Exception> CompletableFuture<?> useTransaction(final HandleConsumer<X> consumer) {
        return CompletableFuture.runAsync(() -> {
            try {
                jdbi.useHandle(consumer);
            } catch(Exception e) {
                exception(e);
                throw new RuntimeException(e);
            }
        }, executor);
    }
    @CanIgnoreReturnValue
    public <R, X extends Exception> CompletableFuture<R> withHandle(HandleCallback<R, X> callback) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return jdbi.withHandle(callback);
            } catch (Exception e) {
                exception(e);
                throw new RuntimeException(e);
            }
        }, executor);
    }

    private void exception(Exception e) {
        logger.error("Exception caught during execution", e);
    }

    public void close() {
        executor.shutdown();
        boolean didShutdown;
        try {
            didShutdown = executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (final InterruptedException ignore) {
            didShutdown = false;
        }
        if (!didShutdown) {
            executor.shutdownNow();
        }
    }

    public Jdbi jdbi() {
        return this.jdbi;
    }


}
