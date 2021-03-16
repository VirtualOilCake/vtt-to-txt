package com.oilman.vtttotxt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

     public String getTxt(List<String> vtt) {
           StringBuilder txtString = new StringBuilder();
           List<String> contentList = new ArrayList<String>();
           for (int i = 0; i < vtt.size(); i++) {
                 if (vtt.get(i).contains("align:start position:0%") &&
                             vtt.get(i).contains("-->") &&
                             !vtt.get(i + 1).isEmpty()
                 ) {
                       contentList.add(vtt.get(i + 1));
                 }
           }
           for (int i = 0; i < contentList.size(); i++) {
                 if (i < contentList.size() - 1 && contentList.get(i).equals(contentList.get(i + 1)) &&
                             !contentList.get(i).equals(" ") && !contentList.get(i).isEmpty()
                 ) {
                       txtString.append(" \n").append(contentList.get(i));
                 }
           }


           return txtString.toString();
     }


     public void fileWriter(String content, String path) throws IOException {
           File file = new File(path);
           FileOutputStream fileOutputStream = new FileOutputStream(file);
           fileOutputStream.write(content.getBytes());
           fileOutputStream.close();
     }

     public List<String> fileReader(String path) throws IOException {
           List<String> output = new ArrayList<String>();
           FileInputStream fileInputStream = new FileInputStream(path);
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
           String tempString = null;
           while ((tempString = bufferedReader.readLine()) != null) {
                 output.add(tempString);
           }
           fileInputStream.close();

           return output;
     }
}