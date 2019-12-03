package com.arfanet.nuvo.belajarti.latihanSoal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arfanet.nuvo.belajarti.R;
import com.arfanet.nuvo.belajarti.modul_soal_latihan.ImageLoader;
import com.arfanet.nuvo.belajarti.modul_soal_latihan.ServiceHandler;
import com.arfanet.nuvo.belajarti.modul_soal_latihan.Soal;


public class SoalPenggunaanMenuIkonMsExcel extends Activity {

    TextView txtNama, txtNo, txtWaktu, txtSoal;
    Button btnPrev, btnSelesai, btnNext;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4;
    ImageView img;
    EditText inputNama;
    int jawabanYgDiPilih[] = null;
    int jawabanYgBenar[] = null;
    boolean cekPertanyaan = false;
    int urutanPertanyaan = 0;
    List<Soal> listSoal;
    JSONArray soal = null;
    CounterClass mCountDownTimer;
    private ProgressDialog pDialog;
    private static String url = "http://sinauit.000webhostapp.com/modulkuis/SoalPenggunaanMenuIkonMsExcel.php";
    private static final String TAG_DAFTAR = "daftar_soal";
    private static final String TAG_ID = "soal_id";
    private static final String TAG_SOAL = "soal";
    private static final String TAG_A = "a";
    private static final String TAG_B = "b";
    private static final String TAG_C = "c";
    private static final String TAG_D = "d";
    private static final String TAG_JWB = "jawaban";
    private static final String TAG_GAMBAR = "gambar";
    public ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz );
        listSoal = new ArrayList<Soal>();
        imageLoader = new ImageLoader(getApplicationContext());
        img         = (ImageView) findViewById(R.id.imageView1);
        txtNama     = (TextView) findViewById(R.id.textViewNama);
        txtNo       = (TextView) findViewById(R.id.textViewNo);
        txtWaktu    = (TextView) findViewById(R.id.textViewWaktu);
        txtSoal     = (TextView) findViewById(R.id.textViewSoal);
        btnPrev     = (Button) findViewById(R.id.buttonPrev);
        btnSelesai  = (Button) findViewById(R.id.buttonSelesai);
        btnNext     = (Button) findViewById(R.id.buttonNext);
        rg          = (RadioGroup) findViewById(R.id.radioGroup1);
        rb1         = (RadioButton) findViewById(R.id.radio0);
        rb2         = (RadioButton) findViewById(R.id.radio1);
        rb3         = (RadioButton) findViewById(R.id.radio2);
        rb4         = (RadioButton) findViewById(R.id.radio3);

        btnSelesai.setOnClickListener(klikSelesai);
        btnPrev.setOnClickListener(klikSebelum);
        btnNext.setOnClickListener(klikBerikut);
        showInputUser();
    }

    private void showInputUser() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View v = mInflater.inflate(R.layout.input_user, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(v);
        dialog.setTitle("Isikan Nama Anda");
        dialog.setIcon(R.drawable.ic_person_black_24dp);
        dialog.setCancelable(false);
        final Button btnOk = (Button) v.findViewById(R.id.btnOk);
        inputNama = (EditText) v.findViewById(R.id.inputID);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (inputNama.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Isikan Nama Anda",
                            Toast.LENGTH_LONG).show();
                } else {
                    new GetSoal().execute();
                    txtNama.setText(inputNama.getText().toString());
                    dialog.dismiss();
                }

            }
        });

        dialog.show();

    }

    private class GetSoal extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SoalPenggunaanMenuIkonMsExcel.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    soal = jsonObj.getJSONArray(TAG_DAFTAR);
                    Soal s = null;
                    // looping through All Contacts
                    for (int i = 0; i < soal.length(); i++) {
                        JSONObject c = soal.getJSONObject(i);
                        s = new Soal();

                        String id = c.getString(TAG_ID);
                        String soal = c.getString(TAG_SOAL);
                        String a = c.getString(TAG_A);
                        String b = c.getString(TAG_B);
                        String cc = c.getString(TAG_C);
                        String d = c.getString(TAG_D);
                        String jwb = c.getString(TAG_JWB);
                        String gambar = c.getString(TAG_GAMBAR);

                        s.setId(id);
                        s.setSoal(soal);
                        s.setA(a);
                        s.setB(b);
                        s.setC(cc);
                        s.setD(d);
                        s.setJawban(jwb);
                        s.setGambar(gambar);
                        listSoal.add(s);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            jawabanYgDiPilih = new int[listSoal.size()];
            java.util.Arrays.fill(jawabanYgDiPilih, -1);
            jawabanYgBenar = new int[listSoal.size()];
            java.util.Arrays.fill(jawabanYgBenar, -1);
            setUpSoal();
        }
    }

    private void setUpSoal() {
        Collections.shuffle(listSoal);
        tunjukanPertanyaan(0, cekPertanyaan);
    }

    private void tunjukanPertanyaan(int urutan_soal_soal, boolean review) {
        btnSelesai.setEnabled(false);
        if(urutan_soal_soal == 0)
            setUpWaktu();

        try {
            rg.clearCheck();
            Soal soal = new Soal();
            soal = listSoal.get(urutan_soal_soal);
            if (jawabanYgBenar[urutan_soal_soal] == -1) {
                jawabanYgBenar[urutan_soal_soal] = Integer.parseInt(soal
                        .getJawban());
            }

            String soalnya = soal.getSoal();
            txtSoal.setText(soalnya);
            rg.check(-1);
            rb1.setTextColor(Color.WHITE);
            rb2.setTextColor(Color.WHITE);
            rb3.setTextColor(Color.WHITE);
            rb4.setTextColor(Color.WHITE);
            imageLoader.DisplayImage(soal.getGambar(), img);
            rb1.setText("a. " + soal.getA());
            rb2.setText("b. " + soal.getB());
            rb3.setText("c. " + soal.getC());
            rb4.setText("d. " + soal.getD());

            Log.d("", jawabanYgDiPilih[urutan_soal_soal] + "");
            if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                rg.check(R.id.radio0);
            if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                rg.check(R.id.radio1);
            if (jawabanYgDiPilih[urutan_soal_soal] == 3)
                rg.check(R.id.radio2);
            if (jawabanYgDiPilih[urutan_soal_soal] == 4)
                rg.check(R.id.radio3);

            pasangLabelDanNomorUrut();

            if (urutan_soal_soal == (listSoal.size() - 1)) {
                btnNext.setEnabled(false);
                btnSelesai.setEnabled(true);
            }

            if (urutan_soal_soal == 0)
                btnPrev.setEnabled(false);

            if (urutan_soal_soal > 0)
                btnPrev.setEnabled(true);

            if (urutan_soal_soal < (listSoal.size() - 1))
                btnNext.setEnabled(true);

            if (review) {
                mCountDownTimer.cancel();
                Log.d("Koreksi", jawabanYgDiPilih[urutan_soal_soal] + ""
                        + jawabanYgBenar[urutan_soal_soal]);
                if (jawabanYgDiPilih[urutan_soal_soal] != jawabanYgBenar[urutan_soal_soal]) {
                    if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                        rb1.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                        rb2.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 3)
                        rb3.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 4)
                        rb4.setTextColor(Color.RED);
                }
                if (jawabanYgBenar[urutan_soal_soal] == 1)
                    rb1.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 2)
                    rb2.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 3)
                    rb3.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 4)
                    rb4.setTextColor(Color.GREEN);
            }

        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }

    private OnClickListener klikSelesai = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            // hitung berapa yg benar
            int jumlahJawabanYgBenar = 0;
            for (int i = 0; i < jawabanYgBenar.length; i++) {
                if ((jawabanYgBenar[i] != -1)
                        && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
                    jumlahJawabanYgBenar++;
            }
            AlertDialog tampilKotakAlert;
            tampilKotakAlert = new AlertDialog.Builder(SoalPenggunaanMenuIkonMsExcel.this)
                    .create();
            tampilKotakAlert.setTitle("Nilai");
            tampilKotakAlert.setIcon(R.drawable.ic_person_black_24dp);
            tampilKotakAlert.setMessage("Nialai anda " + jumlahJawabanYgBenar * 10);

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Ulangi",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            mCountDownTimer.cancel();
                            java.util.Arrays.fill(jawabanYgDiPilih, -1);
                            cekPertanyaan = false;
                            urutanPertanyaan = 0;
                            tunjukanPertanyaan(0, cekPertanyaan);
                        }
                    });

            tampilKotakAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Koreksi",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            mCountDownTimer.cancel();
                            cekPertanyaan = true;
                            urutanPertanyaan = 0;
                            tunjukanPertanyaan(0, cekPertanyaan);
                        }
                    });

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Keluar",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            mCountDownTimer.cancel();
                            cekPertanyaan = false;
                            finish();
                        }
                    });

            tampilKotakAlert.show();

        }
    };

    private void aturJawaban_nya() {
        if (rb1.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 1;
        if (rb2.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 2;
        if (rb3.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 3;
        if (rb4.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 4;

        Log.d("", Arrays.toString(jawabanYgDiPilih));
        Log.d("", Arrays.toString(jawabanYgBenar));

    }

    private OnClickListener klikBerikut = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan++;
            if (urutanPertanyaan >= listSoal.size())
                urutanPertanyaan = listSoal.size() - 1;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private OnClickListener klikSebelum = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan--;
            if (urutanPertanyaan < 0)
                urutanPertanyaan = 0;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private void pasangLabelDanNomorUrut() {
        txtNo.setText("No. " + (urutanPertanyaan + 1)+ " dari "
                + listSoal.size() + " soal");
    }

    private void setUpWaktu() {
        mCountDownTimer = new CounterClass(240000, 1000);
        mCountDownTimer.start();
    }

    @SuppressLint("DefaultLocale")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            finish();
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            txtWaktu.setText(hms);
        }
    }
}
