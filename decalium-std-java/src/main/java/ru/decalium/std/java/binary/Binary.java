package ru.decalium.std.java.binary;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

public final class Binary {

    private Binary() {}

    public static void writeUUID(DataOutput output, UUID uuid) throws IOException {
        output.writeLong(uuid.getMostSignificantBits());
        output.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUUID(DataInput input) throws IOException {
        long most = input.readLong();
        long least = input.readLong();
        return new UUID(most, least);
    }

    public static void writeInstant(DataOutput output, Instant instant) throws IOException {
        output.writeLong(instant.getEpochSecond());
        output.writeInt(instant.getNano());
    }

    public static Instant readInstant(DataInput input) throws IOException {
        long second = input.readLong();
        int nanos = input.readInt();
        return Instant.ofEpochSecond(second, nanos);
    }
}
