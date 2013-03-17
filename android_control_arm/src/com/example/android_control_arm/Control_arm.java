package com.example.android_control_arm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class Control_arm extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_arm);
		final EditText edit1 = (EditText)findViewById(R.id.edit1);
		final EditText edit2 = (EditText)findViewById(R.id.edit2);
		final EditText edit3 = (EditText)findViewById(R.id.edit3);
		final String onled1="led 0 1";
		final String offled1="led 0 0";
		final String onled2="led 1 1";
		final String offled2="led 1 0";
		final String onled3="led 2 1";
		final String offled3="led 2 0";
		final String onled4="led 3 1";
		final String offled4="led 3 0";
		final String con="echo 2 1000 > /tmp/led-control";
	
		
		Button button = (Button)findViewById(R.id.button);
		Button button1 = (Button)findViewById(R.id.button1);
		Button button2 = (Button)findViewById(R.id.button2);
		Button button3 = (Button)findViewById(R.id.button3);
		Button button4 = (Button)findViewById(R.id.button4);
		Button button5 = (Button)findViewById(R.id.button5);
		Button button6 = (Button)findViewById(R.id.button6);
		Button button7 = (Button)findViewById(R.id.button7);
		Button button8 = (Button)findViewById(R.id.button8);
		Button button9 = (Button)findViewById(R.id.button9);
		Button button10 = (Button)findViewById(R.id.button10);
		
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,onled1);
			}
			
		});
		button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,offled1);
			}
			
		});
		button2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,onled2);
			}
			
		});
		button3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,offled2);
			}
			
		});
		button4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,onled3);
			}
			
		});
		button5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,offled3);
			}
			
		});
		button6.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,onled4);
			}
			
		});
		button7.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,offled4);
			}
			
		});
		button8.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,con);
			}
			
		});
		button9.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,"/home/ledon.bash");
			}
			
		});
		button10.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				conect(edit1,edit2,edit3,"/home/ledoff.bash");
			}
			
		});
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	public void conect(EditText edit1,EditText edit2,EditText edit3,String led){
		Socket socket;
		try {
	        Class strictModeClass=Class.forName("android.os.StrictMode");
	        Class strictModeThreadPolicyClass=Class.forName("android.os.StrictMode$ThreadPolicy");
	        Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(null);
	        Method method_setThreadPolicy = strictModeClass.getMethod(
	                "setThreadPolicy", strictModeThreadPolicyClass );
	        method_setThreadPolicy.invoke(null,laxPolicy);
	    } catch (Exception e) {

	    }
		try {
			InetAddress serverAddr = InetAddress.getByName(edit1.getText().toString());
			socket = new Socket(edit1.getText().toString(), 23);
			socket.setKeepAlive(true);
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter w = new PrintWriter(socket.getOutputStream(),true);
			int c=0;
			
			while ((c = r.read()) != ':') {
				System.out.println(c + ":" + (char)c);						
			}
			
			System.out.println(c + ":" + (char)c);
			c = r.read();
			System.out.println(c + ":" + (char)c);
			
			
			w.print(edit2.getText().toString()+"\r\n");
			w.flush();
			
			c=0;
			

			while ((c = r.read()) != ':'){
				System.out.println(c + ":" + (char)c);
			}
				
		
			System.out.println(c + ":" + (char)c);
			c = r.read();
			System.out.println(c + ":" + (char)c);
			
			
			w.print(edit3.getText().toString()+"\r\n");
			w.flush();
			
			
			c=0;

			while ((c = r.read()) != '#'){
				System.out.println(c + ":" + (char)c);
			}
				
			
			System.out.println(c + ":" + (char)c);
			r.read();
			System.out.println(c + ":" + (char)c);
			
			
			w.print(led+"\r\n");
			w.flush();
			
			c=0;
			while ((c = r.read()) != '#'){
				System.out.print(c + ":" + (char)c);
			}
				
			
			w.print("exit\r\n");
			w.flush();
			socket.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}