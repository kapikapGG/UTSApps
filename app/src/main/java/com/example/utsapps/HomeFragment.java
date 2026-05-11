package com.example.utsapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Hubungkan ke file XML yang Anda berikan
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. Inisialisasi tombol-tombol dari XML
        Button btnStandard = v.findViewById(R.id.btnBookingStandard);
        Button btnDeluxe = v.findViewById(R.id.btnBookingDeluxe);
        Button btnSuite = v.findViewById(R.id.btnBookingSuite);

        // 2. Pasang aksi klik untuk pindah ke BookingFragment
        btnStandard.setOnClickListener(view -> pindahKeBooking("Standard"));
        btnDeluxe.setOnClickListener(view -> pindahKeBooking("Deluxe"));
        btnSuite.setOnClickListener(view -> pindahKeBooking("Suite"));

        return v;
    }

    // 3. Fungsi bantuan untuk proses perpindahan fragment
    private void pindahKeBooking(String tipeKamar) {
        BookingFragment fragmentBaru = new BookingFragment();

        // Mengirim data tipe kamar agar otomatis terisi di halaman booking
        Bundle data = new Bundle();
        data.putString("KUNCI_KAMAR", tipeKamar);
        fragmentBaru.setArguments(data);

        // Melakukan transaksi pindah halaman (replace)
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentBaru) // Pastikan ID container sesuai di activity_main.xml
                .addToBackStack(null) // Agar bisa klik tombol 'back' HP
                .commit();
    }


}
