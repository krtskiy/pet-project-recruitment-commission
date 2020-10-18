package com.epam.koretskyi.commission.util;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.DBException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Class for creating report sheet files in .txt and .pdf formats
 * after closing the enrollment at the faculty.
 *
 * @author D.Koretskyi on 18.10.2020.
 */
public class FileCreator {

    private static final Logger LOG = Logger.getLogger(FileCreator.class);

    public static void createReportSheets(int facultyId, HttpServletRequest request) throws DBException {
        Faculty faculty = DBManager.getInstance().findFacultyById(facultyId);

        List<FacultyApplicationsBean> applications = DBManager.getInstance().findFacultyApplicationsByFacultyId(facultyId);
        applications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        if (applications.size() > faculty.getTotalSeats()) {
            applications = applications.subList(0, faculty.getTotalSeats());
        }

        StringBuilder fileText = new StringBuilder();

        for (FacultyApplicationsBean application : applications) {
            fileText.append(application.getUserName());
            fileText.append(" ");
            fileText.append(application.getUserSurname());
            fileText.append(", email: ");
            fileText.append(application.getUserEmail());
            fileText.append(", marks: ");
            List<UserMarksBean> marks = application.getUserMarks();
            int sum = 0;
            for (UserMarksBean mark : marks) {
                fileText.append(mark.getCriterionNameEn());
                fileText.append(" - ");
                fileText.append(mark.getMark());
                fileText.append(", ");
                sum += mark.getMark();
            }
            fileText.append("sum of marks - ");
            fileText.append(sum);
            fileText.append(System.lineSeparator());
            fileText.append(System.lineSeparator());
        }

        LOG.trace(fileText);

        File folder = new File(request.getServletContext().getRealPath(""));
        File filetxt = new File(folder, "report_sheet_faculty_" + facultyId + ".txt");
        File filepdf = new File(folder, "report_sheet_faculty_" + facultyId + ".pdf");

        // creating txt file
        try (FileWriter writer = new FileWriter(filetxt)) {
            filetxt.createNewFile();
            writer.write(fileText.toString());
            writer.flush();
        } catch (IOException e) {
            String failedTxt = "Cannot create txt report sheet";
            LOG.error(failedTxt);

            request.getSession().setAttribute("failedTxt", failedTxt);
            LOG.trace("Set the session attribute: failedTxt --> " + failedTxt);
        }
        LOG.trace("TXT report file created");

        // creating pdf file
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filepdf));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Report sheet for " + faculty.getNameEn());
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            Paragraph mainContent = new Paragraph();
            mainContent.add(fileText.toString());
            mainContent.setAlignment(Element.ALIGN_CENTER);
            document.add(mainContent);
            document.close();
            LOG.trace("PDF report file created");
        } catch (Exception e) {
            String failedPdf = "Cannot create pdf report sheet";
            LOG.error(failedPdf);

            request.getSession().setAttribute("failedPdf", failedPdf);
            LOG.trace("Set the session attribute: failedPdf --> " + failedPdf);
        }

    }
}
