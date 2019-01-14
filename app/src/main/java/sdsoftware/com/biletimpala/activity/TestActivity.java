package sdsoftware.com.biletimpala.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import sdsoftware.com.biletimpala.R;

public class TestActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private static final String TAG = TestActivity.class.getSimpleName();
    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {
        // single barcode scanned
        Log.wtf(TAG, "barcode.toString() : " + barcode.toString());
        Log.wtf(TAG, "barcode.displayValue : " + barcode.displayValue);
        Log.wtf(TAG, "barcode.rawValue : " + barcode.rawValue);

        // playing barcode reader beep sound
        barcodeReader.playBeep();

        // ticket details activity by passing barcode
        /*Intent intent = new Intent(ScanActivity.this, TicketActivity.class);
        intent.putExtra("code", barcode.displayValue);
        startActivity(intent);*/
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {
        // multiple barcodes scanned
        Log.wtf(TAG, list.toString());
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        // barcode scanned from bitmap image
        Log.wtf(TAG, sparseArray.toString());
    }

    @Override
    public void onScanError(String s) {
        Log.wtf(TAG, s);
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        Log.wtf(TAG, "onCameraPermissionDenied");
        finish();
    }

}
