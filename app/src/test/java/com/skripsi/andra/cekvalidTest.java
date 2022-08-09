package com.skripsi.andra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class cekvalidTest {
    cekvalid cekvalid = new cekvalid();


    @Test
    public void cekphonekosong() {
        String phone = "";
        String expected = "No telepon harus diisi";
        String result = cekvalid.valphone(phone);
        assertEquals(expected, result);
    }

    @Test
    public void cekpasswordkurangdari6() {
        String expected = "Password Minimal 6 Karakter";
        String actual = cekvalid.valpass("12345");
        assertEquals(expected, actual);
    }


    @Test
    public void cekemailkosong() {
        String email = "";
        String expected = "Email wajib diisi";
        String result = cekvalid.valemail(email);
        assertEquals(expected, result);
    }

    @Test
    public void cekemailsalah() {
        String email = "Test@test";
        String expected = "Alamat Email tidak valid";
        String result = cekvalid.valemail(email);
        assertEquals(expected, result);
    }
}