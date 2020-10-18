package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author D.Koretskyi on 17.10.2020.
 */
public class WriteToFileCommand extends Command {
    private static final long serialVersionUID = -9198821548382832292L;

    private static final Logger LOG = Logger.getLogger(WriteToFileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        int file = Integer.parseInt(request.getParameter("file"));
        LOG.trace("Request parameter: file --> " + file);

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        Faculty faculty = DBManager.getInstance().findFacultyById(facultyId);

        List<FacultyApplicationsBean> applications = DBManager.getInstance().findFacultyApplicationsByFacultyId(facultyId);
        applications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        if (applications.size() > faculty.getTotalSeats()) {
            applications = applications.subList(0, faculty.getTotalSeats());
        }
        String forward = Path.PAGE_ERROR;
        StringBuilder newstr = new StringBuilder();

        for (FacultyApplicationsBean application : applications) {
            newstr.append(application.getUserName());
            newstr.append(" ");
            newstr.append(application.getUserSurname());
            newstr.append(", email: ");
            newstr.append(application.getUserEmail());
            newstr.append(", marks: ");
            List<UserMarksBean> marks = application.getUserMarks();
            int sum = 0;
            for (UserMarksBean mark : marks) {
                newstr.append(mark.getCriterionNameEn());
                newstr.append(" - ");
                newstr.append(mark.getMark());
                newstr.append(", ");
                sum += mark.getMark();
            }
            newstr.append("sum of marks - ");
            newstr.append(sum);
            newstr.append(System.lineSeparator());
            newstr.append(System.lineSeparator());
        }

        System.out.println(newstr.toString());
        if (file == 0) {
            File folder = new File(request.getServletContext().getRealPath(""));
            File filetxt = new File(folder, "report_sheet_faculty_" + facultyId + ".txt");
            filetxt.createNewFile();
            FileWriter writer = new FileWriter(filetxt);
            writer.write(newstr.toString());
            writer.flush();
            writer.close();
            forward = "DownloadFileServlet?path=" + filetxt;
        }
        if (file == 1) {
            File folder = new File(request.getServletContext().getRealPath(""));
            File filepdf = new File(folder, "report_sheet_faculty_" + facultyId + ".pdf");
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filepdf));
                document.open();
                Paragraph p = new Paragraph();
                p.add("Report sheet for " + faculty.getNameEn());
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                Paragraph mainContent = new Paragraph();
                mainContent.add(newstr.toString());
                mainContent.setAlignment(Element.ALIGN_CENTER);
                document.add(mainContent);
                document.close();
                System.out.println("Done");
            } catch (Exception e) {
                e.printStackTrace();
            }
            forward = "DownloadFileServlet?path=" + filepdf;
        }
        LOG.debug("Command finished");
        return forward;
    }
}
