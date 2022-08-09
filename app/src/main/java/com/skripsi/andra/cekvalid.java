package com.skripsi.andra;

public class cekvalid {

    public static String valemail(String email) {
        String buatcekemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.length() < 1) {
            return "Email wajib diisi";
        } else {
            if (email.trim().matches(buatcekemail)) {
                return "true";
            } else {
                return "Alamat Email tidak valid";
            }
        }
    }

    public static String valpass(String pass) {
        if (pass.length() < 6) {
            return "Password Minimal 6 Karakter";
        } else {
            return "true";
        }
    }

    public static String valnama(String nama) {
        if (nama.length() < 1) {
            return "Nama harus diisi";
        } else {
            return "true";
        }
    }

    public static String valphone(String phone) {
        if (phone.length() < 1) {
            return "No telepon harus diisi";
        } else {
            return "true";
        }
    }


}
