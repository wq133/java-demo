package com.base.interview;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFIleChannel {
          public static void main(String[] args) throws IOException {
                    RandomAccessFile reader = new RandomAccessFile("/Users/guide/Documents/test_read.in", "r");
                    FileChannel channel = reader.getChannel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);

          }
}
