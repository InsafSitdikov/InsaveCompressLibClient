package com.insaf.compreslib;

import com.insaf.compreslib.utils.InsafCompressLib;

public class Main {

    public static void main(String[] args) {
        InsafCompressLib insafCompressLib = new InsafCompressLib();
        String zipPath = insafCompressLib.inImage("C:\\Users\\DemaFayz\\Pictures\\GmJ5yjebqoU.jpg");
        insafCompressLib.unZipIt(zipPath);
    }
}
