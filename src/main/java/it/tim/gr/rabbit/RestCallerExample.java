package it.tim.gr.rabbit;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestCallerExample {
	public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            //HttpGet httpGet = new HttpGet("http://10.13.206.210:7020/serv/fatture/sintesi/DBSDDC80D01E472F");
        	HttpGet httpGet = new HttpGet("http://localhost:7001/serv/fatture/sintesi/DBSDDC80D01E472F");
            httpGet.addHeader("accept", "application/json");
            httpGet.addHeader("sourceSystem", "application/json");
            httpGet.addHeader("interactionDate-Date", "1234234");
            httpGet.addHeader("interactionDate-Time", "34645645");
            httpGet.addHeader("transactionID", "5345345");
            
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                if (response1.getStatusLine().getStatusCode() != 200) {
        			throw new RuntimeException("Failed : HTTP error code : "
        				+ response1.getStatusLine().getStatusCode());
        		}

        		BufferedReader br = new BufferedReader(
                                new InputStreamReader((response1.getEntity().getContent())));

        		String output;
        		System.out.println("Output from Server .... \n");
        		while ((output = br.readLine()) != null) {
        			System.out.println(output);
        		}
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }
            
            /*
            
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            httpPost.addHeader("name", "value");
            StringEntity input = new StringEntity("{\"qty\":100,\"name\":\"iPad 4\"}", "UTF-8");
             input.setContentType("application/json");
            //StringEntity srt=new StringEntity("{}", "UTF-8");
            httpPost.setEntity(input);
            
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                
                if (response2.getStatusLine().getStatusCode() != 201) {
        			throw new RuntimeException("Failed : HTTP error code : "
        				+ response2.getStatusLine().getStatusCode());
        		}

        		BufferedReader br = new BufferedReader(
                                new InputStreamReader((response2.getEntity().getContent())));

        		String output;
        		System.out.println("Output from Server .... \n");
        		while ((output = br.readLine()) != null) {
        			System.out.println(output);
        		}

        		//httpClient.getConnectionManager().shutdown();
        		
        		
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
			*/
            /*
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            //httpPost.addHeader(name, value);
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
            
            */
            
            
            
        } finally {
            httpclient.close();
        }
    }
}
