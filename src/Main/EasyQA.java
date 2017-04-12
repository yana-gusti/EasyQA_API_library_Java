package Main;

import Authorization.MemebersList;
import Authorization.SignIn;
import Authorization.SignOut;
import Crashes.UploadCrash;
import Issues.*;
import Organizations.CreateOrganization;
import Organizations.DeleteOrganization;
import Organizations.UpdateOrganization;
import Projects.CreateProject;
import Projects.DeleteProject;
import Projects.UpdateProject;
import Roles.DeleteRole;
import Roles.OrganizationRole;
import Roles.ProjectRole;
import Roles.UpdateRoles;
import Statuses.CreateStatus;
import Statuses.DeleteStatus;
import Statuses.UpdateStatus;
import TestObjects.DeleteTestObject;
import TestObjects.GetTestObject;
import TestObjects.UploadTestObject;
import TestPlans.Modules.CreateModule;
import TestPlans.Modules.DeleteModule;
import TestPlans.Modules.UpdateModule;
import TestPlans.TestCases.CreateTestCase;
import TestPlans.TestCases.DeleteTestCase;
import TestPlans.TestCases.UpdateTestCase;
import TestPlans.TestPlan.CreateTestPlan;
import TestPlans.TestPlan.DeleteTestPlan;
import TestPlans.TestPlan.UpdateTestPlan;
import TestRuns.TestRun.CreateTestRun;
import TestRuns.TestRun.DeleteTestRun;
import TestRuns.TestRun.UpdateTestRun;
import TestRuns.TestRunResults.DeleteTestRunResults;
import TestRuns.TestRunResults.UpdateTestRunResults;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by yanagusti on 3/16/17.
 *
 */
public class EasyQA {


    public String url;
    SignIn userSignIn;

    String auth_token;

    /**
     * Initialize EasyQA with Base URL
     * @param _url the Base URL of EasyQA. "https://app.geteasyqa.com/" - if you use Cloud EasyQA Test Management Tool
     */
    public EasyQA(String _url){
        this.url = _url;

    }

    /**
     * Method for logging into EasyQA and generate auth_token
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param email your registered email in EasyQA
     * @param password your registered password in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with auth_token and name of user
     */

    public Map<String, String> signIn(String token, String email, String password) throws IOException, JSONException {
        userSignIn = new SignIn(url);
        Map<String, String>  user =userSignIn.signIn(token, email, password);
        auth_token = userSignIn.getAuth_token_value();
        return user;
    }

    /**
     * Method for getting the list of members of your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with names and emails of project members
     */
    public Map<String, String>  membersList(String token) throws IOException, JSONException {
        return new MemebersList(url).getMembersList(token);
    }

    /**
     * Method for logged out from EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void signOut(String token) throws IOException, JSONException {
        new SignOut(url).signOut(token, auth_token);
    }




    /**
     * Method for creating the organization in EasyQA
     * @param title the name of your organization
     * @param data additional parameters for an organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integer id of the organization
     */
    public Integer createOrganization(String title, String... data) throws IOException, JSONException {
        return new CreateOrganization(url).create(auth_token, title, data);
    }

    /**
     * Method for getting the list of the organizations in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map of Integer ids and String titles of the organizations
     */
    public Map<Integer, String> getOrganizationsList() throws IOException, JSONException {
        return new UpdateOrganization(url).getOrganizationsList(auth_token);
    }

    /**
     * Method for getting the organization in EasyQA
     * @param organization_id the id of your organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject with info about your organization
     */
    public JSONObject getOrganization(String organization_id) throws IOException, JSONException {
        return new UpdateOrganization(url).getOrganization(auth_token, organization_id);
    }

    /**
     * Method for updating the organization in EasyQA
     * @param title the name of your organization
     * @param organization_id the id of your organization
     * @param data additional parameters for an organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateOrganization(String title, String organization_id, String... data) throws IOException, JSONException {
        new UpdateOrganization(url).update(auth_token, title, organization_id, data);
    }

    /**
     * Method for deleting the organization in EasyQA
     * @param organization_id the id of the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteOrganization(String organization_id) throws IOException, JSONException {
        new DeleteOrganization(url).delete(auth_token, organization_id);
    }




    /**
     * Method for creating the project in EasyQA
     * @param organization_id an id of your organization in which you will create the project
     * @param title the name of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return an id of your project
     */
    public Integer createProject(Integer organization_id, String title ) throws IOException, JSONException {
        return new CreateProject(url).create(auth_token, organization_id, title);
    }

    /**
     * Method for getting the list of the projects in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map of Integer ids and String titles of the projects
     */
    public Map<Integer, String>  getProjectsList() throws IOException, JSONException {
        return new UpdateProject(url).getProjectsList(auth_token);
    }

    /**
     * Method for getting the project in EasyQA
     * @param project_id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject with info about your project
     */
    public JSONObject getProject(String project_id) throws IOException, JSONException {
        return new UpdateProject(url).getProject(auth_token, project_id);
    }

    /**
     * Method for updating the project in EasyQA
     * @param title the name of your project
     * @param project_id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateProject(String title, String project_id) throws IOException, JSONException {
        new UpdateProject(url).update(auth_token, title, project_id);
    }

    /**
     * Method for deleting the project in EasyQA
     * @param project_id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteProject(String project_id) throws IOException, JSONException {
        new DeleteProject(url).delete(auth_token, project_id);
    }



    /**
     * Method for creating a status for Agile board on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param name a short description of your status
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created status
     */
    public Integer createStatus(String token, String name ) throws IOException, JSONException {
        return new CreateStatus(url).create(auth_token, token, name);
    }

    /**
     * Method for getting the list of status on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and names of statuses
     */
    public Map<Integer, String>  getStatusesList(String token) throws IOException, JSONException {
        return new UpdateStatus(url).getStatusesList(auth_token, token);
    }

    /**
     * Method for getting status info by the status ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param status_id the status ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getStatus(String token, String status_id) throws IOException, JSONException {
        return new UpdateStatus(url).getStatus(auth_token, token, status_id);
    }
    /**
     * Method for updating status by the status ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param name the name of status
     * @param status_id the status ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateStatus(String token, String status_id, String name) throws IOException, JSONException {
        new UpdateStatus(url).update(auth_token, token, name, status_id);
    }

    /**
     * Method for deleting a status by the id on Issues page in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param status_id the status id
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteStatus(String token, String status_id) throws IOException, JSONException {
        new DeleteStatus(url).delete(auth_token, token, status_id);
    }




    /**
     * Method for creating a test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param title a name of your test plan
     * @param data additional parameters for a test plan, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test plan
     */
    public Integer createTestPlan(String token,  String title, String... data ) throws IOException, JSONException {
        return new CreateTestPlan(url).create(token, auth_token, title, data);
    }

    /**
     * Method for getting the list of test plans on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test plans
     */
    public Map<Integer, String> getTestPlansList(String token) throws IOException, JSONException {
        return new UpdateTestPlan(url).getTestPlansList(auth_token, token);
    }

    /**
     * Method for getting the test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestPlan(String token, String test_plan_id) throws IOException, JSONException {
        return new UpdateTestPlan(url).getTestPlan(token, auth_token, test_plan_id);
    }

    /**
     * Method for updating the test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan
     * @param title a name of your test plan
     * @param data additional parameters for a module, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateTestPlan(String token, String test_plan_id, String title, String... data) throws IOException, JSONException {
        new UpdateTestPlan(url).update(token, auth_token, test_plan_id, title, data);
    }

    /**
     * Method for deleting a test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id an id of your test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteTestPlan(String token, String test_plan_id) throws IOException, JSONException {
        new DeleteTestPlan(url).delete(auth_token, token, test_plan_id);
    }





    /**
     * Method for creating a test in the selected module for the test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param module_id the id of the module in which you will create the test case
     * @param title a name of your test case
     * @param data additional parameters for a test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test case
     */
    public Integer createTestCase(String token, String module_id, String title, String... data ) throws IOException, JSONException {
       return new CreateTestCase(url).create(token, auth_token, module_id, title, data);
    }

    /**
     * Method for getting the list of test cases of the selected module on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param module_id the id of the module
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test cases
     */

    public Map<Integer, String> getTestCasesListOfModule(String token, String module_id) throws IOException, JSONException {
        return new UpdateTestCase(url).getTestCasesListOfModule(auth_token, token, module_id);
    }

    /**
     * Method for getting the list of test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test cases
     */
    public Map<Integer, String> getTestCasesListOfTestPlan(String token, String test_plan_id ) throws IOException, JSONException {
        return new UpdateTestCase(url).getTestCasesListOfTestPlan(auth_token, token, test_plan_id);
    }

    /**
     * Method for getting the test case on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_case_id the id of the test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestCase(String token, String test_case_id) throws IOException, JSONException {
        return new UpdateTestCase(url).getTestCase(token, auth_token, test_case_id);
    }

    /**
     * Method for updating the test case on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_case_id the id of the test case
     * @param data additional parameters for the test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateTestCase(String token, String test_case_id, String... data) throws IOException, JSONException {
        new UpdateTestCase(url).update(token, auth_token, test_case_id, data);
    }

    /**
     * Method for deleting a test case from the selected module on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_case_id an id of your test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteTestCase(String token, String test_case_id) throws IOException, JSONException {
        new DeleteTestCase(url).delete(auth_token, token, test_case_id);
    }




    /**
     * Method for creating a module for the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan in which you will create the module
     * @param title a name of your module
     * @param data additional parameters for a module, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created module
     */
    public Integer createModule(String token, String test_plan_id, String title, String... data ) throws IOException, JSONException {
        return new CreateModule(url).create(token, auth_token, test_plan_id, title, data);
    }

    /**
     * Method for getting the list of modules of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of modules
     */
    public Map<Integer, String> getModulesList(String token, String test_plan_id) throws IOException, JSONException {
       return new UpdateModule(url).getModulesList(auth_token, token, test_plan_id);
    }

    /**
     * Method for getting the module of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param module_id the id of the module
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getModule(String token, String module_id) throws IOException, JSONException {
        return new UpdateModule(url).getModule(token, auth_token, module_id);
    }

    /**
     * Method for updating the module of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param module_id the id of the module
     * @param title a name of your module
     * @param data additional parameters for a module, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateModule(String token, String module_id, String title, String... data) throws IOException, JSONException {
        new UpdateModule(url).update(token, auth_token, module_id, title, data);
    }

    /**
     * Method for deleting a module from the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param module_id an id of your module
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteModule(String token, String module_id) throws IOException, JSONException {
        new DeleteModule(url).delete(auth_token, token, module_id);
    }






    /**
     * Method for creating a test run with all test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan from which you want to get all test cases
     * @param title a name of your test run
     * @param assigner_id the id of the project member, with role owner, admin, project_admin or tester
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test run
     */
    public Integer createTestRunIncludeAllTestCases(String token, String title, Integer test_plan_id, Integer assigner_id,
                                                 String... data) throws IOException, JSONException {
        return new CreateTestRun(url).createIncludeAllTestCases(token, auth_token, title, test_plan_id, assigner_id, data);

    }

    /**
     * Method for creating a test run with selected test cases on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_case_ids the array of ids of the test cases
     * @param title a name of your test run
     * @param assigner_id the id of the project member, with role owner, admin, project_admin or tester
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test run
     */
    public Integer createTestRunISelectTestCases(String token, String title, Integer assigner_id,
                                              Integer test_case_ids[], String... data) throws IOException, JSONException {
        return new CreateTestRun(url).createSelectTestCases(token, auth_token, title, assigner_id, test_case_ids, data);

    }

    /**
     * Method for getting the list of test runs on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test runs
     */
    public Map<Integer, String> getTestRunsList(String token) throws IOException, JSONException {
        return new UpdateTestRun(url).getTestRunsList(auth_token, token);
    }

    /**
     * Method for getting the test run on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_id the id of the test run
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestRun(String token, String test_run_id) throws IOException, JSONException {
        return new UpdateTestRun(url).getTestRun(token, auth_token, test_run_id);
    }

    /**
     * Method for updating a test run with all test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_plan_id the id of the test plan from which you want to get all test cases
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateTestRunIncludeAllTestCases(String token, String test_run_id, Integer test_plan_id, String data) throws IOException, JSONException {
        new UpdateTestRun(url).updateIncludeAllTestCases(token, auth_token, test_run_id, test_plan_id, data);
    }

    /**
     * Method for updating a test run with selected test cases on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_case_ids the array of ids of the test cases
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateTestRunSelectTestCases(String token, String test_run_id, Integer test_case_ids[], String... data) throws IOException, JSONException {
        new UpdateTestRun(url).updateSelectTestCases(token, auth_token, test_run_id, test_case_ids, data);
    }

    /**
     * Method for deleting test cases from selected test run on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_results_ids the array of ids of test run results.
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteTestCasesFromTestRun(String token, String test_run_id, Integer test_run_results_ids[], String... data) throws IOException, JSONException {
        new UpdateTestRun(url).deleteTestCasesFromTestRun(token, auth_token, test_run_id, test_run_results_ids, data);
    }

    /**
     * Method for deleting a test run on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_id an id of your test run
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteTestRun(String token, String test_run_id) throws IOException, JSONException {
        new DeleteTestRun(url).delete(auth_token, token, test_run_id);
    }





    /**
     * Method for getting the list of test run results on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_id the id of your test run
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and statuses of test run results (of test cases which are added to the test run)
     */
    public Map<Integer, String>  getTestRunResultsList(String token, String test_run_id) throws IOException, JSONException {
        return new UpdateTestRunResults(url).getTestRunResultsList(auth_token, token, test_run_id);
    }

    /**
     * Method for getting the test run result on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id the id of the test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestRunResult(String token, String test_run_result_id) throws IOException, JSONException {
        return new UpdateTestRunResults(url).getTestRunResult(token, auth_token, test_run_result_id);
    }

    /**
     * Method for updating a test run result to Pass status on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id an id of your test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updatePassStatusForTestCase(String token, String test_run_result_id) throws IOException, JSONException {
        new UpdateTestRunResults(url).update(token, auth_token, test_run_result_id, "pass");
    }

    /**
     * Method for updating a test run result to Fail status on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id an id of your test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateFailStatusForTestCase(String token, String test_run_result_id) throws IOException, JSONException {
        new UpdateTestRunResults(url).update(token, auth_token, test_run_result_id, "fail");
    }

    /**
     * Method for updating a test run result to Blocked status on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id an id of your test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateBlockStatusForTestCase(String token, String test_run_result_id) throws IOException, JSONException {
        new UpdateTestRunResults(url).update(token, auth_token, test_run_result_id, "block");
    }

    /**
     * Method for updating a test run result to Untested status on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id an id of your test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateUntestStatusForTestCase(String token, String test_run_result_id) throws IOException, JSONException {
        new UpdateTestRunResults(url).update(token, auth_token, test_run_result_id, "untested");
    }

    /**
     * Method for deleting a test run result (return untested status) on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param test_run_result_id an id of your test run result (test case which is added to the test run and has some status)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteTestRunResult(String token, String test_run_result_id) throws IOException, JSONException {
        new DeleteTestRunResults(url).delete(auth_token, token, test_run_result_id);
    }




    /**
     * Method for adding user to your organization (assign him role Admin)
     * @param user_id an id of the user to which you want to assign the role (add to organization)
     * @param organization_id the id of your organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneAdmin(Integer user_id, String organization_id) throws IOException, JSONException {
        new OrganizationRole(url).assign(auth_token, user_id, organization_id, "admin" );
    }

    /**
     * Method for adding user to your organization (assign him role User)
     * @param user_id an id of the user to which you want to assign the role (add to organization)
     * @param organization_id the id of your organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneUser(Integer user_id, String organization_id) throws IOException, JSONException {
        new OrganizationRole(url).assign(auth_token, user_id, organization_id, "user" );
    }

    /**
     * Method for deleting the role of the member in EasyQA
     * @param role_id the id of the member role
     * @throws IOException for incorrect parsing of the server response
     */
    public void deleteRole(String role_id) throws IOException {
        new DeleteRole(url).delete(role_id, auth_token);
    }




    /**
     * Method for adding user to your project (assign him role Tester)
     * @param user_id an id of the user to which you want to assign the role (add to project)
     * @param organization_id an id of your organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneTester(Integer user_id, String organization_id, String token) throws IOException, JSONException {
        new ProjectRole(url).assign(auth_token, user_id, organization_id, token, "tester");
    }

    /**
     * Method for adding user to your project (assign him role Developer)
     * @param user_id an id of the user to which you want to assign the role (add to project)
     * @param organization_id an id of your organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneDeveloper(Integer user_id, String organization_id, String token) throws IOException, JSONException {
        new ProjectRole(url).assign(auth_token, user_id, organization_id, token, "developer");
    }

    /**
     * Method for adding user to your project (assign him role Viewer)
     * @param user_id an id of the user to which you want to assign the role (add to project)
     * @param organization_id an id of your organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneViewer(Integer user_id, String organization_id, String token) throws IOException, JSONException {
        new ProjectRole(url).assign(auth_token, user_id, organization_id, token, "viewer");
    }

    /**
     * Method for adding user to your project (assign him role Project Manager)
     * @param user_id an id of the user to which you want to assign the role (add to project)
     * @param organization_id an id of your organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void assigneProjectManager(Integer user_id, String organization_id, String token) throws IOException, JSONException {
        new ProjectRole(url).assign(auth_token, user_id, organization_id, token, "project_manager");
    }


    /**
     * Method for getting all roles in the organization
     * @param organization_id an id of the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with ids and titles of organization roles
     */
    public Map<Integer, String> getOrganizationRolesList(String organization_id) throws IOException, JSONException {
        return new UpdateRoles(url).getOrganizationRolesList(organization_id, auth_token);
    }

    /**
     * Method for getting all roles in the organization
     * @param organization_id an id of the organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with ids and titles of organization roles
     */
    public Map<Integer, String> getProjectRolesList(String organization_id, String token) throws IOException, JSONException {
        return new UpdateRoles(url).getProjectRolesList(organization_id, auth_token, token);
    }

    /**
     * Method for getting role in EasyQA
     * @param role_id an id of the role
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject with the server response
     */
    public JSONObject getRole(String role_id) throws IOException, JSONException {
        return new UpdateRoles(url).getRole(auth_token, role_id);
    }

    /**
     * Method for updating the role of the user to Admin in the organization in EasyQA
     * @param user_id an id of the user to which you want to assign the role (add to organization)
     * @param role_id the id of your role in the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateOrganizationRoleToAdmin(String role_id, String user_id) throws IOException, JSONException {
        new UpdateRoles(url).updateOrganizationRole(role_id, auth_token, user_id, "admin");
    }

    /**
     * Method for updating the role of the user to User in the organization in EasyQA
     * @param user_id an id of the user to which you want to assign the role (add to organization)
     * @param role_id the id of your role in the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateOrganizationRoleToUser(String role_id, String user_id) throws IOException, JSONException {
        new UpdateRoles(url).updateOrganizationRole(role_id, auth_token, user_id, "user");
    }

    /**
     * Method for updating the role of the user to Tester in your project
     * @param role_id an id of the role in the project
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateProjectRoleToTester(String role_id, String token) throws IOException, JSONException {
        new UpdateRoles(url).updateProjectRole(role_id, auth_token, token, "tester");
    }

    /**
     * Method for updating the role of the user to developer in your project
     * @param role_id an id of the role in the project
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateProjectRoleToDeveloper(String role_id, String token) throws IOException, JSONException {
        new UpdateRoles(url).updateProjectRole(role_id, auth_token, token, "developer");
    }

    /**
     * Method for updating the role of the user to viewer in your project
     * @param role_id an id of the role in the project
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateProjectRoleToViewer(String role_id, String token) throws IOException, JSONException {
        new UpdateRoles(url).updateProjectRole(role_id, auth_token, token, "viewer");
    }

    /**
     * Method for updating the role of the user to project manager in your project
     * @param role_id an id of the role in the project
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateProjectRoleToProject_Manager(String role_id, String token) throws IOException, JSONException {
        new UpdateRoles(url).updateProjectRole(role_id, auth_token, token, "project_manager");
    }



    /**
     * Method for creating an issue without attachments on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param summary a short description of your issue
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created issue
     */
    public Integer createIssue(String token, String summary, String... data) throws IOException, JSONException {
        return new CreateIssue(url).createIssue(token, auth_token, summary, data);
    }

    /**
     * Method for creating an issue with attachments on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param summary a short description of your issue
     * @param files a list of files for attachments
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created issue
     */
    public Integer createIssueWithAttachment(String token, String summary, ArrayList<File> files, String... data) throws IOException, JSONException {
        return new CreateIssue(url).createIssueWithAttachments(token, auth_token, summary, files, data);
    }

    /**
     * Method for creating an issue without attachments anonymously for some Test Object on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param summary a short description of your issue
     * @param deviceSerial serial number of your device
     * @param deviceModel model name of you device
     * @param osVersion operation system version of your phone
     * @param test_object_id an id of the test object for which you create an issue
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created issue
     */
    public Integer createIssueAnonymously(String token, String summary, String deviceSerial, String deviceModel, String osVersion, String test_object_id, String data) throws IOException, JSONException {

       return new CreateIssue(url).createIssueFromDevice(token, summary, deviceSerial, deviceModel, osVersion, test_object_id, data);
    }

    /**
     * Method for creating an issue with attachments anonymously for some Test Object on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param summary a short description of your issue
     * @param deviceSerial serial number of your device
     * @param deviceModel model name of you device
     * @param osVersion operation system version of your phone
     * @param test_object_id an id of the test object for which you create an issue
     * @param files a list of files for attachments
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created issue
     */
    public Integer createIssueAnonymouslyWithAttachments(String token, String summary, String deviceSerial, String deviceModel,
                                      String osVersion, String test_object_id, ArrayList<File> files, String... data) throws IOException, JSONException {

        return new CreateIssue(url).createIssueFromDeviceWithAttachment(token, summary, deviceSerial, deviceModel, osVersion, test_object_id, files, data);
    }

    /**
     * Method for getting all issues in the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and summaries of issues
     */
    public Map<Integer, String>  getIssuesList(String token) throws IOException, JSONException {
        return new GetIssues(url).getIssueList(token, auth_token);
    }

    /**
     * Method for adding attachment to the issue by the unique issue ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique issue ID
     * @param files a list of files for attachments
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void addAttachmentToIssueByUniqueID(String token, String id, ArrayList<File> files) throws IOException, JSONException {

        new IssueAttachments(url).createAttachment(token, auth_token, id , files);
    }

    /**
     * Method for adding attachment to the issue by the issue ID in the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the issue ID in the project
     * @param files a list of files for attachments
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void addAttachmentToIssueByProjectID(String token, String id, ArrayList<File> files) throws IOException, JSONException {

        new IssueAttachments(url).createAttachmentByIdInProject(token, auth_token, id, files);
    }

    /**
     * Method for deleting the attachment from the issue by the unique attachment ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique ID of attachment
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteAttachmentByID(String token, String id) throws IOException, JSONException {

        new IssueAttachments(url).deleteAttachmentByID(token, auth_token, id);
    }

    /**
     * Method for getting issue info with mobile build as a test object in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param packageName name of application package
     * @param buildVersionCode code version of your build
     * @param buildVersionName version name of your build
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return string result of the server response
     */
    public String getIssueWithMobileBuild(String token, String packageName, String buildVersionCode, String buildVersionName) throws IOException, JSONException {

        return new GetIssues(url).getIssueInfoWithMobileBuild(token, packageName, buildVersionCode, buildVersionName);
    }

    /**
     * Method for getting issue info with a link of the tested website as a test object in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param link the link of tested website as a test object
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return string result of the server response
     */
    public String getIssueWithSite(String token, String link) throws IOException, JSONException {

        return new GetIssues(url).getIssueInfoWithSite(token, auth_token, link);
    }

    /**
     * Method for getting issue info by the unique issue ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique issue ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return string result of the server response
     */
    public String getIssueByUniqueID(String token, String id) throws IOException, JSONException {

        return new GetIssues(url).getIssueByUniqueID(token, auth_token, id);
    }
    /**
     * Method for getting issue info by the issue ID in the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the issue ID in the project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return string result of the server response
     */
    public String getIssueByProjectID(String token, String id) throws IOException, JSONException {

        return new GetIssues(url).getIssueByProjectID(token, auth_token, id);
    }

    /**
     * Method for updating the issue by the unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique ID of issue
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateIssueByUniqueID(String token, String id, String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssue(token, auth_token, id, data);
    }

    /**
     * Method for updating the issue by ID in the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id_in_project ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void updateIssueByProjectID(String token, String id_in_project, String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueByIdInProject(token, auth_token, id_in_project, data);
    }




    /**
     * Method for updating status of the issue to Submitted by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToSubmitted(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "1", data);
    }

    /**
     * Method for updating status of the issue to To Be Discuss by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToBeDiscuss(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "2", data);
    }

    /**
     * Method for updating status of the issue to Reopened by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToReopened(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "3", data);
    }

    /**
     * Method for updating status of the issue to In Progress by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToInProgress(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "4", data);
    }

    /**
     * Method for updating status of the issue to Code Review by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToCodeReview(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "5", data);
    }

    /**
     * Method for updating status of the issue to QA Review by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToQAReview(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "6", data);
    }

    /**
     * Method for updating status of the issue to Closed by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToClosed(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, "7", data);
    }

    /**
     * Method for updating status of the issue to Optional status by unique ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id unique issue ID in EasyQA
     * @param status_id id of the status which is created by user
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToOptionalStatus(String token, String id, String status_id, String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatus(token, auth_token, id, status_id, data);
    }





    /**
     * Method for updating status of the issue by ID in the project to Submitted in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToSubmittedByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "1", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to To Be Discuss in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToBeDiscussByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "2", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to Reopened in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToReopenedByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "3", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to In Progress in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToInProgressByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "4", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to Code Review in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToCodeReviewByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "5", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to QA Review in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToQAReviewByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "6", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to Closed in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToClosedByIdInProject(String token, String id,  String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, "7", data);
    }

    /**
     * Method for updating status of the issue by ID in the project to Optional Status in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id ID in the project
     * @param status_id id of the status which is created by user
     * @param data additional parameters for an issue
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void changeIssueStatusToOptionalStatusByIdInProject(String token, String id, String status_id, String... data) throws IOException, JSONException {

        new UpdateIssue(url).updateIssueStatusByIdInProject(token, auth_token, id, status_id, data);
    }






    /**
     * Method for deleting an issue by the unique issue ID on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique issue ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteByUniqueID(String token, String id) throws IOException, JSONException {

        new DeleteIssue(url).deleteByUniqueID(token, auth_token, id);
    }

    /**
     * Method for deleting an issue by the issue ID in the project on Issues page in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the issue ID in the project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void deleteByProjectID(String token, String id) throws IOException, JSONException {

        new DeleteIssue(url).deleteByProjectID(token, auth_token, id);
    }





    /**
     * Method for uploading a crash which was appeared on mobile phone
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param packageName name of application package
     * @param buildVersionCode code version of your build
     * @param buildVersionName version name of your build
     * @param deviceSerial serial number of your device
     * @param deviceModel model name of you device
     * @param osVersion operation system version of your phone
     * @param createdAt date of the crash creation
     * @param file log file of crash
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return success message or the server response
     */
    public String uploadCrash(String token, String packageName, String buildVersionCode,
                            String buildVersionName, String deviceSerial, String deviceModel, String osVersion, String createdAt,
                            File file) throws IOException, JSONException {

       return new UploadCrash(url).uploadCrash(token, packageName, buildVersionCode, buildVersionName, deviceSerial,
                deviceModel, osVersion, createdAt, file);
    }





    /**
     * Method for adding the test object with a link of the tested website on Test Objects page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param link the link of tested website
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integert id of created test object
     */
    public Integer uploadLink(String token, String link) throws IOException, JSONException {

        return new UploadTestObject(url).uploadLink(auth_token, token, link);
    }

    /**
     * Method for adding the test object with a link of the tested website on Test Objects page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param file the file of mobile build, can be .apk or .ipa
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integert id of created test object
     */
    public Integer uploadBuild(String token, File file) throws IOException, JSONException {

        return new UploadTestObject(url).uploadBuild(token, auth_token, file);
    }

    /**
     * Method for getting all links of test objects of the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and links of test objects
     */
    public Map<Integer, String> getTestObjectsLinks(String token) throws IOException, JSONException {

       return new GetTestObject(url).getTestObjectsLinks(token);
    }
    /**
     * Method for getting test objects of the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and links of test objects
     */
    public JSONArray getTestObjectsListFullInfo(String token) throws IOException, JSONException {

        return new GetTestObject(url).getTestObjectListFullInfo(token, auth_token);
    }


    /**
     * Method for getting test object info by ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the unique ID of the test object
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestObject(String token, String id) throws IOException, JSONException {

        return new GetTestObject(url).getTestObject(auth_token, token, id);
    }

    /**
     * Method for deleting a test object by the ID in the project on Test Objects page in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param id the test object ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void delete(String token, String id) throws IOException, JSONException {
        new DeleteTestObject(url).delete(auth_token, token, id);

    }
}
