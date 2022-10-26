package me.aakrylov.sandbox.api;

@FunctionalInterface
public interface Test {

    default int testInt(int i) {
        return i * 10;
    }

    default int testInt2(int i) {
        return i * 20;
    }

    int test(int x, int y);
}
