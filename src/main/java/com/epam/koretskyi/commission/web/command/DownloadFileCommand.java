package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.exception.Messages;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Command that sends the user a file of the report, depending on the selected format.
 *
 * @author D.Koretskyi on 18.10.2020.
 */
public class DownloadFileCommand extends Command {
    private static final long serialVersionUID = -7514071729526728500L;

    private static final Logger LOG = Logger.getLogger(DownloadFileCommand.class);

    private File downloadFile;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, AppException {
        LOG.debug("Command starts");

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        String fileType = request.getParameter("file");

        File filePath = new File(request.getServletContext().getRealPath(""));

        if ("txt".equals(fileType)) {
            downloadFile = new File(filePath, "report_sheet_faculty_" + facultyId + ".txt");
        }

        if ("pdf".equals(fileType)) {
            downloadFile = new File(filePath, "report_sheet_faculty_" + facultyId + ".pdf");
        }
        if ("xlsx".equals(fileType)) {
            downloadFile = new File(filePath, "report_sheet_faculty_" + facultyId + ".xlsx");
        }

        try (FileInputStream inStream = new FileInputStream(downloadFile)) {
            // gets MIME type of the file
            String mimeType = request.getServletContext().getMimeType(filePath.getAbsolutePath());
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // obtains response's output stream
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            outStream.close();
        } catch (IOException e) {
            LOG.error(Messages.ERR_CANNOT_DOWNLOAD_FILE);
            throw new AppException(Messages.ERR_CANNOT_DOWNLOAD_FILE, e);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_VIEW_REPORT_SHEET + "&facultyId=" + facultyId;
    }
}
