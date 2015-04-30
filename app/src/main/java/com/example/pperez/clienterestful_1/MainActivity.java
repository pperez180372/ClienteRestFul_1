package com.example.pperez.clienterestful_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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

        public static void maini() {
            Client client = ClientBuilder.newClient();



            WebTarget target = client.target(getBaseURI().path("pdp/v3").build());
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

            Response result = target.request(MediaType.APPLICATION_XML_TYPE).header(HeaderAccept,String.class).header(HeaderContent, String.class).header(HeaderService,String.class).post(Entity.xml(payload));
            System.out.println("Result 1: "+result);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientRest.maini();

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
