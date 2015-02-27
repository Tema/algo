package com.tema.memory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMap {

    static final long N = 1000_000_000;
    static final int CHUNK = 1000;

    public static void main(String[] args) throws IOException, InterruptedException {

        File file = new File("/tmp/example.dat");

        //http://hoytech.com/vmtouch/

        //readFile(file); // 4246 ms per 1 GB (1K chunk)
        //readNIO(file); // 950 ms  (1K chunk)
        //readMMap(file); // 1090 ms  (1K chunk)

        Thread.sleep(10000000L);
    }

    private static void readNIO(File file) throws IOException { // 7,400 ms per 100MB  (10 byte chnk)
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        //channel.position(2*N);
        ByteBuffer buf = ByteBuffer.allocateDirect(CHUNK);
        long t = System.nanoTime();
        for (int i = 0; i < N/CHUNK; i++) {
            channel.position(i * CHUNK);
            channel.read(buf);
        }
        System.out.println((System.nanoTime() - t) / 1_000_000 + " ms " + channel.position() + " bytes");

        channel.close();
    }

    private static void readMMap(File file) throws IOException { // 900 ms per 100MB (10 byte chnk)
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer mmap = channel.map(FileChannel.MapMode.READ_WRITE, N, N);
        //mmap.load();
        byte[] buf = new byte[CHUNK];
        long t = System.nanoTime();
        for (int i = 0; i < N/CHUNK; i++) {
            mmap.get(buf);
        }
        System.out.println((System.nanoTime() - t) / 1_000_000 + " ms " + mmap.position() + " bytes");

        channel.close();
    }

    private static void readFile(File file) throws IOException { // 12,000 ms per 100MB  (10 byte chnk)
        try (FileInputStream is = new FileInputStream(file)) {
            byte[] buf = new byte[CHUNK];
            int count = 0;
            long t = System.nanoTime();
            for (int i = 0; i < N/CHUNK; i++) {
                int c = is.read(buf);
                if (c == -1) {
                    System.out.println("EOF");
                    break;
                } else if (c < 10) {
                    System.out.println("PARTIAL");
                    break;
                }
                count += c;
            }
            System.out.println((System.nanoTime() - t) / 1_000_000 + " ms " + count + " bytes");
        }
    }

    private static void loadMMap() throws IOException {
        File file = new File("/tmp/example.dat");
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE, 0, N);
        buf.load();
        System.out.println(buf.isLoaded());
    }

    private static void createFile() throws IOException {
        File file = new File("/tmp/example.dat");
        FileOutputStream os = new FileOutputStream(file);
        byte[] b = new byte[1000*1000];
        for (int i = 0; i < 1000; i++) {
            os.write(b);
        }
        os.close();
    }

}
