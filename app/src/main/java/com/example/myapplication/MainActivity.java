package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ipPart1;
    private EditText ipPart2;
    private EditText ipPart3;
    private EditText ipPart4;

    private TextView ipType;

    private TextView ipMaskPart1;
    private TextView ipMaskPart2;
    private TextView ipMaskPart3;
    private TextView ipMaskPart4;

    private TextView ipNetworkPart1;
    private TextView ipNetworkPart2;
    private TextView ipNetworkPart3;
    private TextView ipNetworkPart4;

    private TextView ipFirstPart1;
    private TextView ipFirstPart2;
    private TextView ipFirstPart3;
    private TextView ipFirstPart4;

    private TextView ipLastPart1;
    private TextView ipLastPart2;
    private TextView ipLastPart3;
    private TextView ipLastPart4;

    private TextView ipBroadcastPart1;
    private TextView ipBroadcastPart2;
    private TextView ipBroadcastPart3;
    private TextView ipBroadcastPart4;

    private EditText ipMask;

    private TextWatcher ipMaskWatcher;
    private TextWatcher ipAddressWatcher;

    int currentIp = 0;
    int currentMask = 0;

    protected void SolveBroadcast() {
        int broadcast = IpCalc.GetBroadcast(currentIp, currentMask);

        ipBroadcastPart1.setText(Integer.toString((broadcast >> 24 & 0xff)) + "");
        ipBroadcastPart2.setText(Integer.toString((broadcast >> 16 & 0xff)) + "");
        ipBroadcastPart3.setText(Integer.toString((broadcast >> 8 & 0xff)) + "");
        ipBroadcastPart4.setText(Integer.toString((broadcast >> 0 & 0xff)) + "");
    }

    protected void SolveNetwork() {
        int network = IpCalc.GetNetwork(currentIp, currentMask);

        ipNetworkPart1.setText(Integer.toString((network >> 24 & 0xff)) + "");
        ipNetworkPart2.setText(Integer.toString((network >> 16 & 0xff)) + "");
        ipNetworkPart3.setText(Integer.toString((network >> 8 & 0xff)) + "");
        ipNetworkPart4.setText(Integer.toString((network >> 0 & 0xff)) + "");
    }

    protected void SolveRange() {
        int first = IpCalc.GetFirstAddress(currentIp, currentMask);
        int last = IpCalc.GetLastAddress(currentIp, currentMask);

        ipFirstPart1.setText(Integer.toString((first >> 24 & 0xff)) + "");
        ipFirstPart2.setText(Integer.toString((first >> 16 & 0xff)) + "");
        ipFirstPart3.setText(Integer.toString((first >> 8 & 0xff)) + "");
        ipFirstPart4.setText(Integer.toString((first >> 0 & 0xff)) + "");

        ipLastPart1.setText(Integer.toString((last >> 24 & 0xff)) + "");
        ipLastPart2.setText(Integer.toString((last >> 16 & 0xff)) + "");
        ipLastPart3.setText(Integer.toString((last >> 8 & 0xff)) + "");
        ipLastPart4.setText(Integer.toString((last >> 0 & 0xff)) + "");
    }

    protected void SolveMask() {
        int wildcard = IpCalc.GetWildcard(currentMask);

        ipMaskPart1.setText(Integer.toString((wildcard >> 24 & 0xff)) + "");
        ipMaskPart2.setText(Integer.toString((wildcard >> 16 & 0xff)) + "");
        ipMaskPart3.setText(Integer.toString((wildcard >> 8 & 0xff)) + "");
        ipMaskPart4.setText(Integer.toString((wildcard >> 0 & 0xff)) + "");
    }

    protected void SolveType() {
        ipType.setText(IpCalc.GetType(Integer.parseInt(ipPart1.getText().toString()),
                Integer.parseInt(ipPart2.getText().toString()),
                Integer.parseInt(ipPart3.getText().toString()),
                Integer.parseInt(ipPart4.getText().toString())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipMaskWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if(Integer.parseInt(ipMask.getText().toString()) > 32) {
                        Toast.makeText(getApplicationContext(), "Range is 0-32", Toast.LENGTH_LONG).show();
                        ipMask.setText("32");
                    }
                    if(Integer.parseInt(ipMask.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Range is 0-32", Toast.LENGTH_LONG).show();
                        ipMask.setText("0");
                    }
                    currentMask = Integer.parseInt(ipMask.getText().toString());
                    SolveMask();
                    SolveNetwork();
                    SolveBroadcast();
                    SolveRange();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        ipAddressWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if(Integer.parseInt(ipPart1.getText().toString()) > 255) {
                         Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart1.setText("255");
                    }
                    if(Integer.parseInt(ipPart2.getText().toString()) > 255) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart2.setText("255");
                    }
                    if(Integer.parseInt(ipPart3.getText().toString()) > 255) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart3.setText("255");
                    }
                    if(Integer.parseInt(ipPart4.getText().toString()) > 255) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart4.setText("255");
                    }

                    if(Integer.parseInt(ipPart1.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart1.setText("0");
                    }
                    if(Integer.parseInt(ipPart2.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart2.setText("0");
                    }
                    if(Integer.parseInt(ipPart3.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart3.setText("0");
                    }
                    if(Integer.parseInt(ipPart4.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Range is 0-255", Toast.LENGTH_LONG).show();
                        ipPart4.setText("0");
                    }

                    currentIp = IpCalc.ParseAddress(Integer.parseInt(ipPart1.getText().toString()),
                            Integer.parseInt(ipPart2.getText().toString()),
                            Integer.parseInt(ipPart3.getText().toString()),
                            Integer.parseInt(ipPart4.getText().toString()));
                    System.out.println(currentIp);

                    SolveNetwork();
                    SolveBroadcast();
                    SolveRange();
                    SolveType();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        ipType = (TextView) findViewById(R.id.ipType);

        ipPart1 = (EditText) findViewById(R.id.ipPart1);
        ipPart2 = (EditText) findViewById(R.id.ipPart2);
        ipPart3 = (EditText) findViewById(R.id.ipPart3);
        ipPart4 = (EditText) findViewById(R.id.ipPart4);

        ipMaskPart1 = (TextView) findViewById(R.id.ipMaskPart1);
        ipMaskPart2 = (TextView) findViewById(R.id.ipMaskPart2);
        ipMaskPart3 = (TextView) findViewById(R.id.ipMaskPart3);
        ipMaskPart4 = (TextView) findViewById(R.id.ipMaskPart4);

        ipNetworkPart1 = (TextView) findViewById(R.id.ipNetworkPart1);
        ipNetworkPart2 = (TextView) findViewById(R.id.ipNetworkPart2);
        ipNetworkPart3 = (TextView) findViewById(R.id.ipNetworkPart3);
        ipNetworkPart4 = (TextView) findViewById(R.id.ipNetworkPart4);

        ipFirstPart1 = (TextView) findViewById(R.id.ipFirstPart1);
        ipFirstPart2 = (TextView) findViewById(R.id.ipFirstPart2);
        ipFirstPart3 = (TextView) findViewById(R.id.ipFirstPart3);
        ipFirstPart4 = (TextView) findViewById(R.id.ipFirstPart4);

        ipLastPart1 = (TextView) findViewById(R.id.ipLastPart1);
        ipLastPart2 = (TextView) findViewById(R.id.ipLastPart2);
        ipLastPart3 = (TextView) findViewById(R.id.ipLastPart3);
        ipLastPart4 = (TextView) findViewById(R.id.ipLastPart4);

        ipBroadcastPart1 = (TextView) findViewById(R.id.ipBroadcastPart1);
        ipBroadcastPart2 = (TextView) findViewById(R.id.ipBroadcastPart2);
        ipBroadcastPart3 = (TextView) findViewById(R.id.ipBroadcastPart3);
        ipBroadcastPart4 = (TextView) findViewById(R.id.ipBroadcastPart4);

        ipMask = (EditText) findViewById(R.id.ipMask);

        ipMask.addTextChangedListener(ipMaskWatcher);

        ipPart1.addTextChangedListener(ipAddressWatcher);
        ipPart2.addTextChangedListener(ipAddressWatcher);
        ipPart3.addTextChangedListener(ipAddressWatcher);
        ipPart4.addTextChangedListener(ipAddressWatcher);
    }
}