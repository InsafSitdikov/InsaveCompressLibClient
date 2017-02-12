package com.insaf.compreslib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class InsafCompressLib {


    public String inImage(String inputImage) {
        String outZip = getFileNameForZip(inputImage);
        try {

            FileInputStream fisI = new FileInputStream(inputImage);
            ZipOutputStream zosI = new ZipOutputStream(new FileOutputStream(outZip));
            zosI.putNextEntry(new ZipEntry(getFileName(inputImage)));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fisI.read(buffer)) > 0) {
                zosI.write(buffer, 0, bytesRead);
            }
            zosI.closeEntry();
            zosI.close();
            fisI.close();

        } catch (Exception e) {
            System.out.println("inImage error");
        }
        return outZip;
    }

    public String unZipIt(String zipFile) {
        String fileNameOnReturne = getFileNameForImageFromZip(zipFile);
        String patch = getPachName(zipFile);
        byte[] buffer = new byte[1024];
        try {
            File folder = new File(patch);
            if (!folder.exists()) {
                folder.mkdir();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(patch + File.separator + fileName);

                System.out.println("File unzip : " + newFile.getAbsolutePath());

                new File(newFile.getParent()).mkdir();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;

                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

            System.out.println("Done");
        } catch (Exception e) {
        }
        return fileNameOnReturne;
    }

    private static String getPachName(String filePatch) {
        int lastSlash = filePatch.lastIndexOf("/");
        String patch = filePatch.substring(0, lastSlash + 1);
        return patch;
        //System.out.println(fileName);
        ///storage/emulated/0/Pictures/CameraSample/IMG_20150513_1304962_-1072392343.jpg
    }

    private static String getFileName(String patch) {
        int lastSlash = patch.lastIndexOf("/");
        String fileName = patch.substring(lastSlash + 1);
        return fileName;
        //System.out.println(fileName);
        ///storage/emulated/0/Pictures/CameraSample/IMG_20150513_1304962_-1072392343.jpg
    }

    private static String getFileNameForZip(String fileName) {
        int name = fileName.lastIndexOf(".jpg");
        String zipName = fileName.substring(0, name) + ".zip";
        return zipName;
    }

    private static String getFileNameForImageFromZip(String zipName) {
        int name = zipName.lastIndexOf(".zip");
        String fileName = zipName.substring(0, name) + ".jpg";
        return fileName;
    }

}