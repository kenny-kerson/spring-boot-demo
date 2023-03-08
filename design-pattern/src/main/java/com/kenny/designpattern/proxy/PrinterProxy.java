package com.kenny.designpattern.proxy;

// Proxy
public class PrinterProxy implements Printable {

    private String name;
    // 본인 클래스를 프로퍼티로 갖는다!!!
    // 대리인이 처리할 수 있는 메서드는 이 클래스에서 처리하고, 그렇지 않은 것은 본인 클래스를 생성 & 호출한다!!!
    private Printer real;

    public PrinterProxy() {
        this.name = "No Name";
        this.real = null;
    }

    public PrinterProxy(final String name) {
        this.name = name;
        this.real = null;
    }

    @Override
    public synchronized void setPrinterName(final String name) {
        if (real != null) {
            real.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(final String string) {
        realize();
        real.print(string);
    }

    // 본인 클래스의 인스턴스를 만든다!!!
    private synchronized void realize() {
        if (real == null) {
            real = new Printer(name);
        }
    }
}
