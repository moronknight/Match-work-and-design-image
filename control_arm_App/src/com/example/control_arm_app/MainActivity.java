package com.example.control_arm_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText edit1 = (EditText)findViewById(R.id.edit1);
		final EditText edit2 = (EditText)findViewById(R.id.edit2);
		final EditText edit3 = (EditText)findViewById(R.id.edit3);
		final EditText edit4 = (EditText)findViewById(R.id.edit4);
		Button button = (Button)findViewById(R.id.button1);
		try {
	        Class strictModeClass=Class.forName("android.os.StrictMode");
	        Class strictModeThreadPolicyClass=Class.forName("android.os.StrictMode$ThreadPolicy");
	        Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(null);
	        Method method_setThreadPolicy = strictModeClass.getMethod(
	                "setThreadPolicy", strictModeThreadPolicyClass );
	        method_setThreadPolicy.invoke(null,laxPolicy);
	    } catch (Exception e) {

	    }
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					connect(edit1,edit2,edit3,edit4);
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	public void connect(EditText edit1,EditText edit2,EditText edit3,EditText edit4){
		Socket socket;
		try {
			socket = new Socket(edit1.getText().toString(), 23);
			socket.setKeepAlive(true);
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final PrintWriter w = new PrintWriter(socket.getOutputStream(),true);
			int c=0;
			while ((c = r.read()) != ':') {
				//System.out.println(c + "=" + (char)c);				
			}
			c=0;
			w.print(edit2.getText().toString()+"\r\n");
			w.flush();
			
			while ((c = r.read()) != ':') {
				//System.out.println(c + "=" + (char)c);				
			}
			
			w.print(edit3.getText().toString()+"\r\n");
			w.flush();
			
			while ((c = r.read()) != '#') {
				System.out.println(c + "=" + (char)c);				
			}
			
			w.print(edit4.getText().toString()+"\r\n");
			w.flush();			
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}

}
