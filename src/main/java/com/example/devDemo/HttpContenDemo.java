package com.example.devDemo;

import com.example.devDemo.consts.JsonConst;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpContenDemo {

    public static void main(String[] args) {
        String url = "http://example.com/api/v1/resource";
        String requestBody = JsonConst.jsonString;

        System.out.println("requestBody = " + requestBody);
        
        // Create HTTP client and post request
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = null;
        try {
            entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");

            // Calculate the request line size 计算请求行
            String requestLine = httpPost.getMethod() + " " + httpPost.getURI() + " HTTP/1.1\r\n";
            int requestLineSize = requestLine.getBytes("UTF-8").length;


            // Calculate the headers size 计算请求头
            StringBuilder headersBuilder = new StringBuilder();
            Header[] allHeaders = httpPost.getAllHeaders();
            for (Header header : allHeaders) {
                System.out.println("allHeaders = " + header.getName());
                headersBuilder.append(header.getName()).append(": ").append(header.getValue()).append("\r\n");
            }

            // Empty line after headers 在请求头后面添加空行
            headersBuilder.append("\r\n");
            int headersSize = headersBuilder.toString().getBytes("UTF-8").length;

            // Calculate the body size 请求体
            int bodySize = requestBody.getBytes("UTF-8").length;

            // Total request size
            int totalSize = requestLineSize + headersSize + bodySize;

            System.out.println("Request Line Size: " + requestLineSize + " bytes");
            System.out.println("Headers Size: " + headersSize + " bytes");
            System.out.println("Body Size: " + bodySize + " bytes");
            System.out.println("Total HTTP Request Size: " + totalSize + " bytes");

            // Send the request
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("Response code: " + response.getStatusLine().getStatusCode());

            // Close the client
            httpClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
