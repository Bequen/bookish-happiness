package com.example.myapplication;

public class IpCalc {
    public static int GetMask(IpAddress address) {
        return 0;
    }

    public static int ParseAddress(String address) {
        String[] splits = address.split(".");

        int result = 0;
        for(int i = 0; i < 4; i++) {
            result |= Integer.getInteger(splits[i]) << ((4 - i) * 8);
        }

        return result;
    }

    public static int ParseAddress(int a, int b, int c, int d) {
        int result = (a << 24) | (b << 16) | (c << 8) | (d << 0);

        return result;
    }

    public static int GetWildcard(int mask) {
        int maskNum = 0;
        for(int i = 0; i < mask; i++) {
            maskNum |= 1 << (32 - i - 1);
        }

        return maskNum;
    }

    public static int GetBroadcast(int address, int mask) {
        return address | (~GetWildcard(mask));
    }

    public static int GetNetwork(int address, int mask) {
        return address & (0xffffffff << (32 - mask));
    }

    public static int GetFirstAddress(int address, int mask) {
        return GetNetwork(address, mask) + 1;
    }

    public static int GetLastAddress(int address, int mask) {
        return GetBroadcast(address, mask) - 1;
    }

    public static Boolean IsPrivate(int a, int b, int c, int d) {
        if(a == 192 && b == 168) {
            return true;
        } return false;
    }

    public static String GetType(int a, int b, int c, int d) {
        if(a == 192 && b == 168) {
            return "Private";
        }
        else if(a == 0) {
            return "Software";
        }
        else if(a == 10) {
            return "Private";
        }
        else if(a == 100 && (b >= 64 && b <= 127)) {
            return "Private";
        }
        else if(a == 127) {
            return "Host";
        }
        else if(a == 169 && b == 254) {
            return "Subnet";
        }
        else if(a == 172 && (b >= 16 && b <=31)) {
            return "Private";
        }
        else if(a == 192 && b == 0 && c == 0) {
            return "Private";
        }
        else if(a == 192 && b == 0 && c == 2) {
            return "Documentation";
        }
        else if(a == 192 && b == 88 && c == 99) {
            return "Internet";
        }
        else if(a == 198 && (b >= 18 && b <=19)) {
            return "Private";
        }
        else if(a == 198 && b == 51 && c == 100) {
            return "Documentation";
        }
        else if(a == 203 && b == 0 && c == 113) {
            return "Documentation";
        }
        else if((a >= 224 && a <= 239)) {
            return "Internet";
        }
        else if((a >= 240 && a <= 255) && d <= 254) {
            return "Internet";
        }
        else if(a == 255 && b == 255 && c == 255 && d == 255) {
            return "Subnet";
        }
        else {
            return "Public";
        }
    }

    /*public static IpAddress GetMaskAddress(int mask) {
        int maskNum = 0;

        for(int i = 0; i < mask; i++) {
            maskNum |= 1 << i;
        }

        return new IpAddress(maskNum >> 0 & 0xff, maskNum >> 8 & 0xff, maskNum >> 16 & 0xff, maskNum >> 24 & 0xff);
    }

    public static IpAddress GetBroadcast(IpAddress address, int mask) {
        IpAddress result = address;

        int ip[] = {address.a, address.b, address.c, address.d};
        for(int i = mask; i < 32; i++) {
            ip[i / 8] |= 1 << (i - (i / 8) * 8);
        }

        return new IpAddress(ip[0], ip[1], ip[2], ip[3]);
    }*/
}
