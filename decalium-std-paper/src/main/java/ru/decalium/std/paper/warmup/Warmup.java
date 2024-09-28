package ru.decalium.std.paper.warmup;


import java.util.Objects;
import java.util.function.IntConsumer;

public interface Warmup {

    void prepare(int second);

    void cancelled();

    void success();

    int seconds();

    static Builder builder() {
        return new Builder();
    }

    class Builder {
        private IntConsumer prepare;
        private Runnable cancelled;
        private int seconds;

        protected Builder() {
        }

        public Builder prepare(IntConsumer prepare) {
            this.prepare = prepare;
            return this;
        }

        public Builder cancelled(Runnable cancelled) {
            this.cancelled = cancelled;
            return this;
        }

        public Builder seconds(int seconds) {
            this.seconds = seconds;
            return this;
        }

        public Warmup build(Runnable success) {
            return new Impl(Objects.requireNonNull(prepare), Objects.requireNonNull(cancelled), success, seconds);
        }
    }


    record Impl(IntConsumer prepare,
                Runnable cancelledRunnable,
                Runnable successRunnable, int seconds) implements Warmup {


        @Override
        public void prepare(int second) {
            this.prepare.accept(second);
        }

        @Override
        public void cancelled() {
            this.cancelledRunnable.run();
        }

        @Override
        public void success() {
            this.successRunnable.run();
        }
    }
}
