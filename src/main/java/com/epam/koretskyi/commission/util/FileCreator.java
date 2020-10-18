package com.epam.koretskyi.commission.util;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.DBException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Class for creating report sheet files in .txt .pdf or .xlsx formats.
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

        StringBuilder content = new StringBuilder();

        for (FacultyApplicationsBean application : applications) {
            content.append(application.getUserName());
            content.append(" ");
            content.append(application.getUserSurname());
            content.append(", email: ");
            content.append(application.getUserEmail());
            content.append(", marks: ");
            List<UserMarksBean> marks = application.getUserMarks();
            int sum = 0;
            for (UserMarksBean mark : marks) {
                content.append(mark.getCriterionNameEn());
                content.append(" - ");
                content.append(mark.getMark());
                content.append(", ");
                sum += mark.getMark();
            }
            content.append("sum of marks - ");
            content.append(sum);
            content.append(System.lineSeparator());
            content.append(System.lineSeparator());
        }

        LOG.trace(content);

        File folder = new File(request.getServletContext().getRealPath(""));
        File fileTxt = new File(folder, "report_sheet_faculty_" + facultyId + ".txt");
        File filePdf = new File(folder, "report_sheet_faculty_" + facultyId + ".pdf");
        File fileXlsx = new File(folder, "report_sheet_faculty_" + facultyId + ".xlsx");

        // creating txt file
        try (FileWriter writer = new FileWriter(fileTxt)) {
            fileTxt.createNewFile();
            writer.write(content.toString());
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
            PdfWriter.getInstance(document, new FileOutputStream(filePdf));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Report sheet for " + faculty.getNameEn());
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            Paragraph mainContent = new Paragraph();
            mainContent.add(content.toString());
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

        // creating excel file
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report sheet for faculty #" + facultyId);

        int rowCount = 0;
        int headerCellCount = 0;

        Row headerRow = sheet.createRow(rowCount++);

        Cell nameCell = headerRow.createCell(headerCellCount++);
        nameCell.setCellValue("Name");

        Cell surnameCell = headerRow.createCell(headerCellCount++);
        surnameCell.setCellValue("Surname");

        Cell emailCell = headerRow.createCell(headerCellCount++);
        emailCell.setCellValue("Email");

        List<Criterion> criteria = faculty.getCriteria();
        for (Criterion criterion : criteria) {
            Cell criterionCell = headerRow.createCell(headerCellCount++);
            criterionCell.setCellValue(criterion.getNameEn());
        }

        Cell markSumCell = headerRow.createCell(headerCellCount++);
        markSumCell.setCellValue("Sum of marks");

        for (FacultyApplicationsBean application : applications) {
            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell userNameCell = row.createCell(columnCount++);
            userNameCell.setCellValue(application.getUserName());

            Cell userSurnameCell = row.createCell(columnCount++);
            userSurnameCell.setCellValue(application.getUserSurname());

            Cell userEmailCell = row.createCell(columnCount++);
            userEmailCell.setCellValue(application.getUserEmail());

            List<UserMarksBean> marks = application.getUserMarks();
            int sum = 0;
            for (UserMarksBean mark : marks) {
                Cell userMarkCell = row.createCell(columnCount++);
                userMarkCell.setCellValue(mark.getMark());
                sum += mark.getMark();
            }

            Cell userMarkSumCell = row.createCell(columnCount++);
            userMarkSumCell.setCellValue(sum);
        }

        try (FileOutputStream outputStream = new FileOutputStream(fileXlsx)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            String failedXlsx = "Cannot create xlsx report sheet";
            LOG.error(failedXlsx);

            request.getSession().setAttribute("failedXlsx", failedXlsx);
            LOG.trace("Set the session attribute: failedXlsx --> " + failedXlsx);
        }
    }

}
