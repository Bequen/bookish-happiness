package com.example.myapplication;

public class IpAddress {
    int a;
    int b;
    int c;
    int d;

    public IpAddress() {
        this.a = this.b = this.c = this.d = 0;
    }

    public IpAddress(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int Get(int index) {
        switch(index) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            case 3:
                return d;
            default:
                return a;
        }
    }

    @Override
    public String toString() {
        return a + "." +
                b + "." +
                c + "." +
                d;
    }

    public void Add(IpAddress ip) {
        this.d = (this.d + ip.d) % 255;
        this.c = (this.c + ip.c + (this.d / 255)) % 255;
        this.b = (this.b + ip.b + (this.c / 255)) % 255;
        this.a = (this.a + ip.a + (this.b / 255)) % 255;
    }
}
