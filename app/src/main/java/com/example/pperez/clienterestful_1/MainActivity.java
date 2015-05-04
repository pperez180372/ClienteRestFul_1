package com.example.pperez.clienterestful_1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;


public class MainActivity extends ActionBarActivity {

    public static class ClientRest {

        private static UriBuilder getBaseURI() {
            return UriBuilder.fromUri("http://pperez-seu-ks.disca.upv.es:8080");
        }

        public static void maini() throws MalformedURLException {
            Client client = ClientBuilder.newClient();



           // WebTarget target = client.target(getBaseURI().path("pdp/v3").build());
         /*   target.queryParam()
            //pascual
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            queryParams.add("json", js); //set parametes for request

           String  appKey = "Bearer " + appKey; // appKey is unique number

            //Get response from RESTful Server get(ClientResponse.class);
            ClientResponse response = null;
            response = webResource.queryParams(queryParams)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Authorization", appKey)
                    .get(ClientResponse.class);

            String jsonStr = response.getEntity(String.class);

            //pascual end
*/
            //String result = target.queryParam("header:9380820382",String.class).queryParam("herwererewr:rrererre",String.class).request(MediaType.TEXT_PLAIN).get(String.class);

            String HeaderAccept="Accept: application/xml";
            String HeaderContent="Content-type: application/xml";
            String HeaderService="Fiware-Service: myTenant";
            String payload="<?xml version=\"1.0\" encoding=\"utf-8\"?><Request xsi:schemaLocation=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17 http://docs.oasis-open.org/xacml/3.0/xacml-core-v3-schema-wd-17.xsd\" ReturnPolicyIdList=\"false\" CombinedDecision=\"false\" xmlns=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Attributes Category=\"urn:oasis:names:tc:xacml:1.0:subject-category:access-subject\"><Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:subject:subject-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">role12345</AttributeValue></Attribute></Attributes><Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:resource\"> <Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:resource:resource-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">fiware:orion:tenant1234:us-west-1:res9876</AttributeValue></Attribute></Attributes><Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:action\"><Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:action:action-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">read</AttributeValue></Attribute></Attributes></Request>";

            HttpURLConnection httpcon = null;
            try {
                httpcon = (HttpURLConnection) ((new URL("http://pperez-seu-ks.disca.upv.es:8080/pdp/v3").openConnection()));
                httpcon.setDoOutput(true);
                httpcon.setRequestProperty("Content-Type", "application/json");
                httpcon.setRequestProperty("Accept", "application/json");
                httpcon.setRequestMethod("POST");
                httpcon.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Response result = target.request(MediaType.APPLICATION_XML_TYPE).header(HeaderAccept,String.class).header(HeaderContent, String.class).header(HeaderService,String.class).post(Entity.xml(payload));
        //    System.out.println("Result 1: "+result);

        }
    }

    // When user clicks button, calls AsyncTask.
    // Before attempting to fetch the URL, makes sure that there is a network connection.
    public void myClickHandler(View view) {
        // Gets the URL from the UI's text field.
        String stringUrl = urlText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute("");
        } else {
            textView.setText("No network connection available.");
        }
    }

    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }


    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        Map<String, List<String>> rr;
        String res="";
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        String HeaderAccept="application/xml";
        String HeaderContent="application/xml";
        String HeaderService="myTenant";
        String payload="<?xml version=\"1.0\" encoding=\"utf-8\"?><Request xsi:schemaLocation=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17 http://docs.oasis-open.org/xacml/3.0/xacml-core-v3-schema-wd-17.xsd\" ReturnPolicyIdList=\"false\" CombinedDecision=\"false\" xmlns=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Attributes Category=\"urn:oasis:names:tc:xacml:1.0:subject-category:access-subject\"><Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:subject:subject-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">role12345</AttributeValue></Attribute></Attributes><Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:resource\"> <Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:resource:resource-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">fiware:orion:tenant1234:us-west-1:res9876</AttributeValue></Attribute></Attributes><Attributes Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:action\"><Attribute IncludeInResult=\"false\" AttributeId=\"urn:oasis:names:tc:xacml:1.0:action:action-id\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">read</AttributeValue></Attribute> </Attributes></Request>";
       String encodedData = URLEncoder.encode(payload, "UTF-8");
       // String encodedData = payload;
    String leng= Integer.toString(payload.getBytes("UTF-8").length);
        OutputStreamWriter wr = null;
        BufferedReader rd  = null;
        StringBuilder sb = null;


            URL url = new URL("http://pperez-seu-ks.disca.upv.es:8080/pdp/v3");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Accept", HeaderAccept);
            conn.setRequestProperty("Content-type", HeaderContent);
            conn.setRequestProperty("Fiware-Service",HeaderService);
             conn.setRequestProperty( "Content-Length", leng );
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(payload.getBytes("UTF-8"));
            os.flush();
        os.close();

            /*URL myURL = new URL(serviceURL);
            HttpURLConnection myURLConnection = (HttpURLConnection)myURL.openConnection();
            String userCredentials = "username:password";
            String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
            myURLConnection.setRequestProperty ("Authorization", basicAuth);
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            myURLConnection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
            myURLConnection.setRequestProperty("Content-Language", "en-US");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            */



            // Starts the query
            //conn.connect();
            //int response = conn.getResponseCode();
        int rc = conn.getResponseCode();
        String resp=conn.getContentEncoding();
        is=conn.getInputStream();

        if(rc==200)
        {
            //read the result from the server
            rd = new BufferedReader(new InputStreamReader(is));
            //res=rd.readLine();
            // cabeceras de recepcion
            rr = conn.getHeaderFields();


        }
        else
        {
             rr=null;
            System.out.println("http response code error: "+rc+"\n");

        }

            is = conn.getInputStream();
            System.out.println("headers: "+rr.toString());

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.

    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private static final String DEBUG_TAG = "HttpExample";
    private EditText urlText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
        Button Botonw = (Button) findViewById(R.id.button);
        Botonw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickHandler(v);

            }
        });
        /*try {
            ClientRest.maini();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
