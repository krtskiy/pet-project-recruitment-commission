//package com.epam.koretskyi.commission.web;
//
//import org.apache.log4j.Logger;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * @author D.Koretskyi on 17.10.2020.
// */
//@WebServlet("/DownloadFileServlet")
//public class DownloadFileServlet extends HttpServlet {
//    private static final long serialVersionUID = 6122258794858139834L;
//
//    private static final Logger LOG = Logger.getLogger(DownloadFileServlet.class);
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String filePath = request.getParameter("path");
//        LOG.trace("Request parameter: path --> " + filePath);
//        // reads input file from an absolute path
//        File downloadFile = new File(filePath);
//        FileInputStream inStream = new FileInputStream(downloadFile);
//
//        // if you want to use a relative path to context root:
//        String relativePath = getServletContext().getRealPath("");
//        System.out.println("relativePath = " + relativePath);
//
//        // obtains ServletContext
//        ServletContext context = getServletContext();
//
//        // gets MIME type of the file
//        String mimeType = context.getMimeType(filePath);
//        if (mimeType == null) {
//            // set to binary type if MIME mapping not found
//            mimeType = "application/octet-stream";
//        }
//        System.out.println("MIME type: " + mimeType);
//
//        // modifies response
//        response.setContentType(mimeType);
//        response.setContentLength((int) downloadFile.length());
//
//        // forces download
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
//        response.setHeader(headerKey, headerValue);
//
//        // obtains response's output stream
//        OutputStream outStream = response.getOutputStream();
//
//        byte[] buffer = new byte[4096];
//        int bytesRead = -1;
//
//        while ((bytesRead = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//
//        inStream.close();
//        outStream.close();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//}
