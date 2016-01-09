package com.tw.xqcao.old;

interface Printer {
    void print(String message);
}

public class OldUsage {

    private final String message;

    public OldUsage(String message) {
        this.message = message;
    }

    public void showMessage(Printer printer) {
        printer.print(message);
    }

    public static void main(String args[]) {
        OldUsage oldUsage = new OldUsage("World");

        //旧的实现方式。
        oldUsage.showMessage(new Printer() {
            @Override
            public void print(String message) {
                System.out.println("Hello: " + message);
            }
        });
    }
}