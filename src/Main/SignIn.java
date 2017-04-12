package Main;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yanagusti on 3/16/17.
 */
public class SignIn {
    public static String url = "http://qa_dashboard.test.thinkmobiles.com:8085";
    public static String token = "NcS6OjfvocJwEJmYnjcCnhpIadKyx9tv";
    public static String email = "yana.gusti@thinkmobiles.com";
    public static String password = "111111";




    public static void main (String args []) throws IOException, JSONException {
        ArrayList <File> files = new ArrayList<>();
        files.add(0, new File("./lib/jpeg.png"));
        files.add(1, new File("./lib/test.jpg"));
//        files.add(2, new File("/Users/yanagusti/Downloads/jpeg-home1 copy 2.png"));
//        files.add(3, new File("/Users/yanagusti/Downloads/jpeg-home1 copy.png"));

        EasyQA easyQA = new EasyQA(url);

        easyQA.signIn(token, email, password);
//        easyQA.membersList(token);
//        easyQA.createIssue(token, "testFromIDEA333", "description", "testtesttest", "issue_type", "task", "priority", "low");
//        easyQA.createIssueFromDevice(token, "testFromDevice34324", "test", "test",
//                "8.0", "52");
//        easyQA.createIssueWithAttachment(token, "test556", files);
//        easyQA.getTestObject(token, "52");
//        easyQA.uploadCrash(token, "com.thinkmobiles.android.easyqa",
//                "8",
//                "1.1.8_debug",
//                "1234567",
//                "Nexus 5X API 23",
//                "7.0",
//                "1473334063084",
//                "Android-test-log.txt",
//                "H4sIAAAAAAAAAJ1STU8CMRA966/ogcOamIaVRYTbZl0DiYBuxIO3oTvuFrrt2hYi/95C+QiIieE0\n09d5r/NeOoMlUAGyoNlCWl5h+s2wtlzJHklfBglJs2yckXGSTLIsfbwl/fg9JU+T0fUVWMJURW3J\n5bxSUy7QUJC5VjynCGb1BTRmli+5XaXu+BpT1Frp4AScuQ167fDmEkFgDI1pRM3mWdWwdYlqI6R6\nIc8KRpEX3PGVoX3XC9S03NQEhJgCmwc7eEPrtLp/8nJuarCsHDojUOAxsdv+xXtWqnbXwpVg23uv\n0cPxLNT13thbqRFyWgE/+NpiPv4o7Hj2bP8fNH4KZJYO0ZYqp1wu1RyDETg2Eg8e0t09yqVFLUGs\nN/1YFcriQHLb8OOxzGNdmHVGbut1xoeRbVB39//V9GZOBe5DJ/ADXWh+4tQCAAA=\n");
//        easyQA.addAttachmentToIssueByProjectID(token, "210", files);
//        easyQA.deleteAttachmentByID(token, "1629");
//            easyQA.deleteOrganization("387");
//            easyQA.getOrganizationsList();
//            easyQA.getTestCasesListOfModule();

//            easyQA.getOrganization("389");
//            easyQA.createOrganization("fromIDEA888888");
//        easyQA.membersList(token);
        easyQA.getTestRunResultsList(token, "132");
//        easyQA.getModulesList(token, "35");
//        easyQA.createTestRunIncludeAllTestCases(token, "fromIDEAALL", 35, 19);
//        easyQA.getTestCasesListOfModule(token, "60");
//        Integer testcases[]={72, 73, 74};
//        easyQA.getTestRunResultsList(token, "152");

//        easyQA.createTestRunISelectTestCases(token, "FROMIDEASELECT", 35, 19, testcases);
//        easyQA.updatePassStatusForTestCase(token, "1404");

//        easyQA.getIssuesList(token);
//        easyQA.uploadBuild(token, new File("./lib/app-debug1.1.8 (1).apk"));
//        easyQA.getStatusesList(token);
//        easyQA.changeIssueStatusToInProgressByIdInProject(token, "210");

//            easyQA.updateOrganization("fromIDEA22222222", "386");
//        easyQA.createProject(389, "FROMIDEA");
//        Integer id = easyQA.createIssue(token, "testFromIDEA1234");
//        easyQA.deleteIssue(token, "6195");
//        easyQA.getIssues(token);
//        easyQA.getIssueWithMobileBuild(token, "com.thinkmobiles.android.easyqa", "10", "1.0.1_debug");
//        easyQA.getIssueWithSite(token, "http://qa_dashboard.test.thinkmobiles.com:8085/");
//        easyQA.getIssueByID(token, "6197");

//        Integer id = easyQA.createTestPlan(token, "testFromIDEA111");
//        easyQA.updateTestPlan(token, "testFromIDEA123", id);
//        easyQA.getTestPlans(token);
//        easyQA.createModule(token, "testFromIDEA", id);
//        Date dates = new Date();
//        String date = String.valueOf(dates.getTime());
//        easyQA.uploadCrash(token, "com.thinkmobiles.android.easyqa", "10", "1.0.1_debug",
//                "1234567", "Nexus 5X API 23", "8", date, "Android-test-log.txt",
//                "test");
//        easyQA.deleteTestPlan(token, 259);
//        easyQA.createOrganization("fromIDEA3");
//        easyQA.deleteOrganization(371);
//        easyQA.signOut(token);

    }


}
