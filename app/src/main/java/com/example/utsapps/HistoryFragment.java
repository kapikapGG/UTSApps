package com.example.utsapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.utsapps.R;

public class HistoryFragment extends Fragment {
    TextView tvRiwayat;
    Button btnHapus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        tvRiwayat = v.findViewById(R.id.tv_history_data); // Ganti id sesuai XML History kamu
        btnHapus = v.findViewById(R.id.btn_hapus_booking);

        SharedPreferences sp = getActivity().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);

        // Cek apakah ada data
        if (sp.getBoolean("ada_data", false)) {
            String data = sp.getString("data_terakhir", "");
            tvRiwayat.setText(data);
            btnHapus.setVisibility(View.VISIBLE);
        } else {
            tvRiwayat.setText("Belum ada riwayat booking.");
            btnHapus.setVisibility(View.GONE);
        }

        // FUNGSI HAPUS
        btnHapus.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear(); // Menghapus semua data booking
            editor.apply();

            tvRiwayat.setText("Booking telah dibatalkan/dihapus.");
            btnHapus.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
        });

        return v;
    }
}
