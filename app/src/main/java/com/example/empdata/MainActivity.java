package com.example.empdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EmployAdapter employAdapter;
    List<Employ> employList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employAdapter = new EmployAdapter(getApplication(),MainActivity.this);
        employList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


//
//        employAdapter.setList(employList);
//
//        recyclerView.setAdapter(employAdapter);


        new Connection().execute();
    }


    class Connection extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            String result ="";
            String host ="http://10.0.2.2/company/employ.php";

            try {

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer stringBuffer = new StringBuffer("");
                String line ="";

                while ((line = reader.readLine()) != null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result = stringBuffer.toString();

            }catch (Exception e){
                return new String("There is exception "+e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//            super.onPostExecute(result);

            try {
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");
                if (success == 1){
//                    Toast.makeText(getApplicationContext(), "there's a emp", Toast.LENGTH_SHORT).show();
                    JSONArray emps = jsonResult.getJSONArray("emp");
                    for (int i =0 ; i < emps.length(); i++){
                        JSONObject emp = emps.getJSONObject(i);
                        int empId = emp.getInt("id");
                        String empName = emp.getString("name");
                        int empSalary = emp.getInt("salary");
                        int empPhone = emp.getInt("phone");
                        int empAge = emp.getInt("age");
                        String line = empId + "--"+ empName + "--" + empAge + "--" + empSalary + "--0" + empPhone;
                        employList.add(new Employ(line));
                        employAdapter.setList(employList);
                        recyclerView.setAdapter(employAdapter);


                    }
                }else {
                    Toast.makeText(getApplicationContext(), "no emp", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
