package com.example.utsapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BookingFragment extends Fragment {
    EditText edtNama, edtEmail, edtPhone;
    Spinner spinnerKamar;
    RadioGroup rgSarapan;
    CheckBox cbExtraBed;
    Button btnSimpan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking, container, false);

        // Inisialisasi Widget
        edtNama = v.findViewById(R.id.edtNama);
        edtEmail = v.findViewById(R.id.edtEmail);
        edtPhone = v.findViewById(R.id.edtPhone);
        spinnerKamar = v.findViewById(R.id.spinnerKamar);
        rgSarapan = v.findViewById(R.id.rgSarapan);
        cbExtraBed = v.findViewById(R.id.cbExtraBed);
        btnSimpan = v.findViewById(R.id.btnSimpan);

        // Isi data Spinner secara manual (atau pakai strings.xml)
        String[] tipeKamar = {"Standard Room", "Deluxe Room", "Suite Room"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tipeKamar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKamar.setAdapter(adapter);

        btnSimpan.setOnClickListener(view -> simpanData());

        return v;
    }

    private void simpanData() {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        // VALIDASI
        if (nama.isEmpty()) { edtNama.setError("Nama tidak boleh kosong"); return; }
        if (!email.contains("@")) { edtEmail.setError("Email harus mengandung @"); return; }
        if (phone.isEmpty()) { edtPhone.setError("Nomor telepon wajib diisi"); return; }

        // AMBIL DATA LAIN
        String tipe = spinnerKamar.getSelectedItem().toString();
        int selectedRb = rgSarapan.getCheckedRadioButtonId();
        String sarapan = (selectedRb == R.id.rbYa) ? "Ya" : "Tidak";
        String extraBed = cbExtraBed.isChecked() ? "Tambah Extra Bed" : "Tanpa Extra Bed";

        // GABUNGKAN DATA UNTUK DISIMPAN
        String hasilBooking = "Nama: " + nama + "\nEmail: " + email + "\nHP: " + phone +
                "\nKamar: " + tipe + "\nSarapan: " + sarapan + "\nCatatan: " + extraBed;

        // SIMPAN KE SHAREDPREFERENCES
        SharedPreferences sp = getActivity().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data_terakhir", hasilBooking);
        editor.putBoolean("ada_data", true);
        editor.apply();

        Toast.makeText(getActivity(), "Booking Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

        // Bersihkan form
        edtNama.setText(""); edtEmail.setText(""); edtPhone.setText("");
    }
}
