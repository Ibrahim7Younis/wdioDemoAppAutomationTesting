package wdioDemoTest;

import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;
import wdioDemoApp.pages.MainPage;
import wdioDemoApp.utils.PropertiesFilesHandler;
import wdioDemoApp.constants.GeneralConstants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public static AndroidDriver driver;
     MainPage mainPage;
    // Load properties files
    PropertiesFilesHandler propHandler = new PropertiesFilesHandler();
    Properties generalConfigsProps = propHandler.loadPropertiesFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    Properties testDataConfigsProps = propHandler.loadPropertiesFile(GeneralConstants.TEST_DATA_CONFIG_FILE_NAME);
    // get the appium server URL
    private final String appiumServer = generalConfigsProps.getProperty(GeneralConstants.APPIUM_SERVER);
    // get the path of the capabilities file
    private final String caps = generalConfigsProps.getProperty(GeneralConstants.CAPS);

    @BeforeSuite
    public void setUpDriver() throws MalformedURLException {
        // Initialize the AndroidDriver with the Appium server URL and capabilities
        try {
            driver = new AndroidDriver(new URI(appiumServer).toURL(), setCaps());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeClass
    public void setUp() {
        // Initialize the main page
        mainPage = new MainPage(driver);
    }
    // This method sets the capabilities for the AndroidDriver
    private DesiredCapabilities setCaps(){
        // Load the capabilities from the JSON file
        String filePath = System.getProperty("user.dir") + GeneralConstants.FILE_DELIMITER + caps;
        // Create a File object for the JSON file
        File srcFile = new File(filePath);
        // Create a JSONParser object to parse the JSON file
        JSONParser parser = new JSONParser();
        JSONObject object;
        try {
            // Parse the JSON file and create a JSONObject
            object = (JSONObject) parser.parse(new FileReader(srcFile));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        // Create a DesiredCapabilities object and set the capabilities
        DesiredCapabilities caps = new DesiredCapabilities(object);
        return caps;
    }
    @AfterSuite
    public void tearDownDriver() {
            driver.quit();
    }
    // This method reads data from an Excel sheet and returns it as a 2D Object array
    protected Object[][] getSheetData(String filePath, String sheetName) throws IOException {
        // Create a FileInputStream to read the Excel file which is passed in the method parameter
        FileInputStream file = new FileInputStream(new File(filePath));
        // Create a Workbook object
        Workbook workbook = new XSSFWorkbook(file);
        // Get the specified sheet in the method parameter
        Sheet sheet = workbook.getSheet(sheetName);

        // Handle empty sheet case
        if (sheet == null || sheet.getPhysicalNumberOfRows() == 0) {
            workbook.close();
            return new Object[0][0];
        }

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        List<Object[]> data = new ArrayList<>();

        // Start from row 1 (skip header)
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                // Handle completely empty rows by filling with empty strings
                data.add(createEmptyRow(colCount));
                continue;
            }

            Object[] rowData = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                rowData[j] = getSafeCellValue(cell);
            }
            data.add(rowData);
        }
        workbook.close();
        return data.toArray(new Object[0][]);
    }
    // This method handles different cell types and returns a safe value
    private Object getSafeCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                String value = cell.getStringCellValue().trim();
                return value.isEmpty() ? "" : value;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue();
                }
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    // This method creates an empty row with the specified number of columns
    private Object[] createEmptyRow(int colCount) {
        Object[] emptyRow = new Object[colCount];
        Arrays.fill(emptyRow, "");
        return emptyRow;
    }
}