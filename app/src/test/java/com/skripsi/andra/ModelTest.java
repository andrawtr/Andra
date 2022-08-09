package com.skripsi.andra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ModelTest {

    /**
     * Should return the jenis
     */
    @Test
    public void getJenisShouldReturnJenis() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Jenis";
        String actual = model.getJenis();
        assertEquals(expected, actual);
    }
    @Test
    public void getLokasiShouldReturnLokasi() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Lokasi";
        String actual = model.getLokasi();
        assertEquals(expected, actual);
    }

    @Test
    public void getTamuShouldReturnTamu() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Tamu";
        String actual = model.getTamu();
        assertEquals(expected, actual);
    }
    @Test
    public void getTanggalShouldReturnTanggal() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Tanggal";
        String actual = model.getTanggal();
        assertEquals(expected, actual);
    }
    @Test
    public void getJamShouldReturnJam() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Jam";
        String actual = model.getJam();
        assertEquals(expected, actual);
    }
    @Test
    public void getStatusShouldReturnStatus() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Status";
        String actual = model.getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void getUUIDShouldReturnUUID() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "UUID";
        String actual = model.getUuid();
        assertEquals(expected, actual);
    }
    @Test
    public void getNopeShouldReturnNope() {
        Model model =
                new Model("Jenis", "Lokasi", "Tamu", "Tanggal", "Jam", "Status", "UUID", "Nope");
        String expected = "Nope";
        String actual = model.getNope();
        assertEquals(expected, actual);
    }
}