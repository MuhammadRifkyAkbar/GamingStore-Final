/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import Model.Transaksi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransaksiService {
    private List<Transaksi> transaksiList;

    public TransaksiService() {
        // Inisialisasi daftar transaksi
        transaksiList = new ArrayList<>();
    }

    // Menambah transaksi baru
    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
    }

    // Menghapus transaksi berdasarkan ID transaksi
    public void hapusTransaksi(Long idTransaksi) {
        transaksiList.removeIf(transaksi -> transaksi.getIdTransaksi().equals(idTransaksi));
    }

    // Mendapatkan semua transaksi
    public List<Transaksi> semuaTransaksi() {
        return transaksiList;
    }

    // Menemukan transaksi berdasarkan ID transaksi
    public Transaksi temukanTransaksi(Long idTransaksi) {
        return transaksiList.stream()
                .filter(transaksi -> transaksi.getIdTransaksi().equals(idTransaksi))
                .findFirst()
                .orElse(null);
    }
}