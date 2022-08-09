package com.skripsi.andra;

public class Model {
    public String jenis;
    public String lokasi;
    public String tamu;
    public String tanggal;
    public String jam;
    public String status;
    public String uuid;
    public String nope;

    public Model() {

    }

    public Model(String jenis, String lokasi, String tamu, String tanggal, String jam, String status, String uuid, String nope) {
        this.jenis = jenis;
        this.lokasi = lokasi;
        this.tamu = tamu;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
        this.uuid = uuid;
        this.nope = nope;
    }


    public String getJenis() {
        return jenis;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getTamu() {
        return tamu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJam() {
        return jam;
    }

    public String getStatus() {
        return status;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNope() {
        return nope;
    }


}