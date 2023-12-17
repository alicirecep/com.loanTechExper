package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import pojos.LoanplansUpdatePOJO;
import pojos.LoansRejectPojo;
import utilities.ReusableMethods;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class API_AdminStepdefinitions {
    Response response;
    HashMap reqBody;
    JsonPath jsonPath;
    JSONObject requestBody;
    LoanplansUpdatePOJO requestBodyPojo;
    LoansRejectPojo reqBodyPojo;

    //********************************* api/categories/list *************************************************
    @Given("The API user saves the response from the api categories list endpoint with the valid authorization information")
    public void the_apı_user_saves_the_response_from_the_api_categories_list_endpoint_with_the_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }

    @Then("The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserRecordsTheResponseWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        assertTrue(ReusableMethods.tryCatchGet().contains("status code: 401, reason phrase: Unauthorized"));
    }

    @Then("The API user records the response with invalid authorization information verifies that the status code is '401' and confirms that the reason phrase is Unauthorized")
    public void theAPIUserRecordsTheResponseWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheReasonPhraseIsUnauthorized() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //********************************* api/categories/details/{{id}} ***********************************
    @And("The API user records the response from the api categories details endpoint with the valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiCategoriesDetailsEndpointWithTheValidAuthorizationInformation() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //********************************* api/categories/add **********************************************
    @And("The API user prepares a POST request containing the correct data to send to the api categories add endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheCorrectDataToSendToTheApiCategoriesAddEndpoint() {
        reqBody = new HashMap<>();
        reqBody.put("name", "Car Loan");
        reqBody.put("description", "If you want to buy a car, this loan is for you.");
    }

    @When("The API user sends a POST request and records the response returned from the api categories add endpoint with valid authorization information")
    public void TheAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiCategoriesAddEndpointWithValidAuthorizationInformation() {
        ReusableMethods.postResponse("admin", reqBody);
    }

    @And("The API user prepares a POST request with incomplete data to send to the api categories add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithIncompleteDataToSendToTheApiCategoriesAddEndpoint() {
        reqBody = new HashMap<>();
        reqBody.put("description", "If you want to buy a car, this loan is for you.");
    }

    @And("The API user prepares a POST request without data to send to the api categories add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiCategoriesAddEndpoint() {
        reqBody = new HashMap<>();
    }

    @When("The API user sends a POST request and records the response returned from the api categories add endpoint with invalid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiCategoriesAddEndpointWithInvalidAuthorizationInformation() {
        ReusableMethods.postResponse("invalidToken",reqBody);
    }

    @Then("The API user verifies that the error information in the response body is {string}")
    public void theAPIUserVerifiesThatTheErrorInformationInTheResponseBodyIs(String error) {
        ReusableMethods.response.then()
                                   .assertThat()
                                      .body("message.error[0]", equalTo(error));
    }
    @Then("The API user verifies that the id information at index {int} in the response body is {int}")
    public void the_apı_user_verifies_that_the_id_information_at_index_in_the_response_body_is(int index, int valueId) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(valueId,jsonPath.getInt("data["+index+"].id"));
    }
    //***************************************************************************************************

    //********************************* api/categories/update/{{id}} ************************************
    @And("The API user prepares a POST request containing the correct data to send to the api categories update endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheCorrectDataToSendToTheApiCategoriesUpdateEndpoint() {
        reqBody = new HashMap<>();
        reqBody.put("name", "Home Loan 2");
        reqBody.put("description", "Updated loan description.");
    }

    @When("The API user sends a POST request and records the response returned from the api categories update endpoint with valid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiCategoriesUpdateEndpointWithValidAuthorizationInformation() {
        ReusableMethods.postResponse("admin", reqBody);
    }

    @When("The API user sends a POST request and records the response returned from the api categories update endpoint with invalid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_categories_update_endpoint_with_invalid_authorization_information() {
        ReusableMethods.postResponse("invalidToken",reqBody);
    }
    @And("The API user prepares a POST request containing the name data to send to the api categories update endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheNameDataToSendToTheApiCategoriesUpdateEndpoint() {
        reqBody = new HashMap<>();
        reqBody.put("name", "Car Loan 35");
    }

    @And("The API user prepares a POST request without data to send to the api categories update endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiCategoriesUpdateEndpoint() {
        reqBody = new HashMap<>();
    }

    @And("The API user verifies that the name information in the response body is {string}")
    public void theAPIUserVerifiesThatTheNameInformationInTheResponseBodyIs(String valueName) {
        jsonPath = ReusableMethods.response.jsonPath();

        assertEquals(valueName, jsonPath.getString("data[0].name"));
    }
    //***************************************************************************************************

    //********************************* api/categories/status/{{id}} ************************************
    @And("The API user records the response from the api categories status endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiCategoriesStatusEndpointWithValidAuthorizationInformation() {
        ReusableMethods.patchResponse("admin");
    }
    @Then("The API user records the response from the api categories status endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserRecordsTheResponseFromTheApiCategoriesStatusEndpointWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        assertTrue(ReusableMethods.tryCatchPatch().contains("status code: 401, reason phrase: Unauthorized"));
    }
    @And("The API user verifies that the status information in the response body is {string}")
    public void theAPIUserVerifiesThatTheStatusInformationInTheResponseBodyIs(String valueStatus) {
        jsonPath = ReusableMethods.response.jsonPath();

        assertEquals(valueStatus, jsonPath.getString("data[0].status"));
    }
    //***************************************************************************************************

    //********************************* api/categories/delete/{{id}} ************************************
    @And("The API user records the response from the api categories delete endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiCategoriesDeleteEndpointWithValidAuthorizationInformation() {
        ReusableMethods.deleteResponse("admin");
    }

    @Then("The API user records the response from the api categories delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void the_apı_user_records_the_response_from_the_api_categories_delete_endpoint_with_invalid_authorization_information_verifies_that_the_status_code_is_and_confirms_that_the_error_information_is_unauthorized() {
        assertTrue(ReusableMethods.tryCatchDelete().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //************************************** api/loanplans/list *****************************************
    @And("The API user records the response from the api loanplans list endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiLoanplansListEndpointWithValidAuthorizationInformation() {
        ReusableMethods.getResponse("admin");
    }

    @Then("Verify the information of the one with the index {int} in the API user response body: {int}, {int}, {string}, {string}, {string}, {string}, {string}, {int}, {int}, {string}, {string}, {string}, {int}, {string}, {string}, {int}, {int}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, int category_id, int form_id, String name, String title, String minimum_amount, String maximum_amount, String per_installment, int installment_interval, int total_installment, String application_fixed_charge, String application_percent_charge, String instruction, int delay_value, String fixed_charge, String percent_charge, int is_featured, int status, String created_at, String updated_at) {
        jsonPath = ReusableMethods.response.jsonPath();

        assertEquals(category_id, jsonPath.getInt("data[" + dataIndex + "].category_id"));
        assertEquals(form_id, jsonPath.getInt("data[" + dataIndex + "].form_id"));
        assertEquals(name, jsonPath.getString("data[" + dataIndex + "].name"));
        assertEquals(title, jsonPath.getString("data[" + dataIndex + "].title"));
        assertEquals(minimum_amount, jsonPath.getString("data[" + dataIndex + "].minimum_amount"));
        assertEquals(maximum_amount, jsonPath.getString("data[" + dataIndex + "].maximum_amount"));
        assertEquals(per_installment, jsonPath.getString("data[" + dataIndex + "].per_installment"));
        assertEquals(installment_interval, jsonPath.getInt("data[" + dataIndex + "].installment_interval"));
        assertEquals(total_installment, jsonPath.getInt("data[" + dataIndex + "].total_installment"));
        assertEquals(application_fixed_charge, jsonPath.getString("data[" + dataIndex + "].application_fixed_charge"));
        assertEquals(application_percent_charge, jsonPath.getString("data[" + dataIndex + "].application_percent_charge"));
        assertEquals(instruction, jsonPath.getString("data[" + dataIndex + "].instruction"));
        assertEquals(delay_value, jsonPath.getInt("data[" + dataIndex + "].delay_value"));
        assertEquals(fixed_charge, jsonPath.getString("data[" + dataIndex + "].fixed_charge"));
        assertEquals(percent_charge, jsonPath.getString("data[" + dataIndex + "].percent_charge"));
        assertEquals(is_featured, jsonPath.getInt("data[" + dataIndex + "].is_featured"));
        assertEquals(status, jsonPath.getInt("data[" + dataIndex + "].status"));
        assertEquals(created_at, jsonPath.getString("data[" + dataIndex + "].created_at"));
        assertEquals(updated_at, jsonPath.getString("data[" + dataIndex + "].updated_at"));
    }
    //***************************************************************************************************

    //************************************** api/loanplans/details/{{id}} ********************************
    @And("The API user records the response from the api loanplans details endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiLoanplansDetailsEndpointWithValidAuthorizationInformation() {
        ReusableMethods.getResponse("admin");
    }

    //***************************************************************************************************

    //***************************************** api/loanplans/add ***************************************
    @And("The API user prepares a POST request containing the correct data to send to the api loanplans add endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheCorrectDataToSendToTheApiLoanplansAddEndpoint() {
        requestBody = new JSONObject();
        requestBody.put("category_id", 1);
        requestBody.put("name", "Car Loan 9 ");
        requestBody.put("title", "Car Loan 9");
        requestBody.put("total_installment", 20);
        requestBody.put("installment_interval", 20);
        requestBody.put("per_installment", "4.00");
        requestBody.put("minimum_amount", "2000.00000000");
        requestBody.put("maximum_amount", "5000.00000000");
        requestBody.put("delay_value", 25);
        requestBody.put("fixed_charge", "100.00000000");
        requestBody.put("percent_charge", "1.00000000");
        requestBody.put("is_featured", 0);
        requestBody.put("application_fixed_charge", "20.00000000");
        requestBody.put("application_percent_charge", "3.00000000");
        requestBody.put("instruction", "Car Loan Plan 9");
    }

    @When("The API user sends a POST request and records the response returned from the api loanplans add endpoint with valid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiLoanplansAddEndpointWithValidAuthorizationInformation() {
        ReusableMethods.postResponse("admin", requestBody.toString());
    }

    @When("The API user sends a POST request and records the response returned from the api loanplans add endpoint with invalid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_loanplans_add_endpoint_with_invalid_authorization_information() {
        ReusableMethods.postResponse("invalidToken",requestBody.toString());
    }
    @Then("The API user prepares a POST request with incomplete data to send to the api loanplans add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithIncompleteDataToSendToTheApiLoanplansAddEndpoint() {
        requestBody = new JSONObject();
        requestBody.put("per_installment", "4.00");
        requestBody.put("minimum_amount", "2000.00000000");
        requestBody.put("maximum_amount", "5000.00000000");
        requestBody.put("delay_value", 25);
        requestBody.put("fixed_charge", "100.00000000");
        requestBody.put("percent_charge", "1.00000000");
        requestBody.put("is_featured", 0);
        requestBody.put("application_fixed_charge", "20.00000000");
        requestBody.put("application_percent_charge", "3.00000000");
        requestBody.put("instruction", "Car Loan Plan 9");
    }

    @Then("The API user prepares a POST request without data to send to the api loanplans add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiLoanplansAddEndpoint() {
        requestBody = new JSONObject();
    }
    //***************************************************************************************************

    //************************************ api/loanplans/update/{{id}} ***********************************
    @And("The API user prepares a POST request containing the correct data to send to the api loanplans update endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheCorrectDataToSendToTheApiLoanplansUpdateEndpoint() {
        requestBodyPojo = new LoanplansUpdatePOJO(11, "Personal Finance Loan ", "Personal Finance Loan");
    }

    @When("The API user sends a POST request and records the response returned from the api loanplans update endpoint with valid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiLoanplansUpdateEndpointWithValidAuthorizationInformation() {
        ReusableMethods.postResponse("admin", requestBodyPojo);
    }

    @When("The API user sends a POST request and records the response returned from the api loanplans update endpoint with invalid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_loanplans_update_endpoint_with_invalid_authorization_information() {
        ReusableMethods.postResponse("invalidToken",requestBodyPojo);
    }
    @And("The API user prepares a POST request without data to send to the api loanplans update endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiLoanplansUpdateEndpoint() {
        requestBodyPojo = new LoanplansUpdatePOJO();
    }

    @And("The API user verifies that the title information in the response body is {string}")
    public void theAPIUserVerifiesThatTheTitleInformationInTheResponseBodyIs(String valueTitle) {
        jsonPath = ReusableMethods.response.jsonPath();

        assertEquals(valueTitle, jsonPath.getString("data[0].title"));
    }
    //***************************************************************************************************

    //************************************ api/loanplans/status/{{id}} **********************************
    @And("The API user records the response from the api loanplans update endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiLoanplansUpdateEndpointWithValidAuthorizationInformation() {
        ReusableMethods.patchResponse("admin");
    }

    @Then("The API user records the response from the api loanplans status endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void the_apı_user_records_the_response_from_the_api_loanplans_status_endpoint_with_invalid_authorization_information_verifies_that_the_status_code_is_and_confirms_that_the_error_information_is_unauthorized() {
        assertTrue(ReusableMethods.tryCatchPatch().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //************************************ api/loanplans/delete/{{id}} **********************************
    @And("The API user records the response from the api loanplans delete endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiLoanplansDeleteEndpointWithValidAuthorizationInformation() {
        ReusableMethods.deleteResponse("admin");
    }

    @Then("The API user records the response from the api loanplans delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized'")
    public void the_apı_user_records_the_response_from_the_api_loanplans_delete_endpoint_with_invalid_authorization_information_verifies_that_the_status_code_is_and_confirms_that_the_error_information_is_unauthorized() {
        assertTrue(ReusableMethods.tryCatchDelete().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //**************************************** api/subscriber/list **************************************
    @Given("The API user records the response from the api subscriber list endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_subscriber_list_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }

    @Then("Verify the information of the one with the index {int} in the API user response body: {string}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, String email, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(email,jsonPath.getString("data["+dataIndex+"].email"));
        assertEquals(created_at,jsonPath.getString("data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data["+dataIndex+"].updated_at"));
    }
    //***************************************************************************************************

    //********************************* api/subscriber/details/{{id}} ***********************************
    @Given("The API user records the response from the api subscriber details endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_subscriber_details_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("The API user verifies the content of the data in the response body which includes {int}, {string}, {string},{string}")
    public void the_apı_user_verifies_the_content_of_the_data_in_the_response_body_which_includes(int id, String email, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(id,jsonPath.getInt("data.id"));
        assertEquals(email,jsonPath.getString("data.email"));
        assertEquals(created_at,jsonPath.getString("data.created_at"));
        assertEquals(updated_at,jsonPath.getString("data.updated_at"));

    }
    //***************************************************************************************************

    //************************************* api/subscriber/add ******************************************
    @Given("The API user prepares a POST request containing the correct data to send to the api subscriber add endpoint")
    public void the_apı_user_prepares_a_post_request_containing_the_correct_data_to_send_to_the_api_subscriber_add_endpoint() {
        reqBody=new HashMap<>();
        reqBody.put("email","megenc@gmail.com");
    }
    @When("The API user sends a POST request and records the response returned from the api subscriber add endpoint with valid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_subscriber_add_endpoint_with_valid_authorization_information() {
        ReusableMethods.postResponse("admin",reqBody);
    }

    @And("The API user prepares a POST request with incorrect data to send to the api subscriber add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithIncorrectDataToSendToTheApiSubscriberAddEndpoint() {
        reqBody=new HashMap<>();
        reqBody.put("mail","agenc@gmail.com");
    }

    @And("The API user prepares a POST request without data to send to the api subscriber add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiSubscriberAddEndpoint() {
        reqBody=new HashMap<>();
    }

    @When("The API user sends a POST request and records the response returned from the api subscriber add endpoint with invalid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiSubscriberAddEndpointWithInvalidAuthorizationInformation() {
        ReusableMethods.postResponse("invalidToken",reqBody);
    }
    @Then("The API user verifies that the id information in the response body is {int}")
    public void the_apı_user_verifies_that_the_id_information_in_the_response_body_is(int valueId) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(valueId,jsonPath.getInt("data.id"));
    }
    //***************************************************************************************************

    //********************************* api/subscriber/update/{{id}} ************************************
    @Given("The API user prepares a POST request containing the correct data to send to the api subscriber update endpoint")
    public void the_apı_user_prepares_a_post_request_containing_the_correct_data_to_send_to_the_api_subscriber_update_endpoint() {
        requestBody=new JSONObject();
        requestBody.put("email","ayilmaz@gmail.com");
    }
    @When("The API user sends a POST request and records the response returned from the api subscriber update endpoint with valid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_subscriber_update_endpoint_with_valid_authorization_information() {
        ReusableMethods.postResponse("admin",requestBody.toString());
    }

    @And("The API user prepares a POST request without data to send to the api subscriber update endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiSubscriberUpdateEndpoint() {
        requestBody=new JSONObject();
    }

    @And("The API user prepares a POST request with incorrect data to send to the api subscriber update endpoint")
    public void theAPIUserPreparesAPOSTRequestWithIncorrectDataToSendToTheApiSubscriberUpdateEndpoint() {
        requestBody=new JSONObject();
        requestBody.put("mail","ayilmaz@gmail.com");
    }

    @When("The API user sends a POST request and records the response returned from the api subscriber update endpoint with invalid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiSubscriberUpdateEndpointWithInvalidAuthorizationInformation() {
        ReusableMethods.postResponse("invalidToken",requestBody.toString());
    }
    @Then("The API user verifies that the email information in the response body is {string}")
    public void the_apı_user_verifies_that_the_email_information_in_the_response_body_is(String email) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(email,jsonPath.getString("data.email"));
    }
    //***************************************************************************************************

    //********************************* api/subscriber/delete/{{id}} ************************************
    @And("The API user records the response from the api subscriber delete endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiSubscriberDeleteEndpointWithValidAuthorizationInformation() {
        ReusableMethods.deleteResponse("admin");
    }

    @Then("The API user records the response from the api subscriber delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserRecordsTheResponseFromTheApiSubscriberDeleteEndpointWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        assertTrue(ReusableMethods.tryCatchDelete().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //*************************************** api/tickets/list ******************************************
    @And("The API user records the response from the api tickets list endpoint with valid authorization information")
    public void theAPIUserRecordsTheResponseFromTheApiTicketsListEndpointWithValidAuthorizationInformation() {
        ReusableMethods.getResponse("admin");
    }
    @Then("Verify the information of the one with the index {int} in the API user response body: {int}, {string}, {string}, {string}, {string}, {int}, {int}, {string}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, int user_id, String name, String email, String ticket, String subject, int status, int priority, String last_reply, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(user_id,jsonPath.getInt("data["+dataIndex+"].user_id"));
        assertEquals(name,jsonPath.getString("data["+dataIndex+"].name"));
        assertEquals(email,jsonPath.getString("data["+dataIndex+"].email"));
        assertEquals(ticket,jsonPath.getString("data["+dataIndex+"].ticket"));
        assertEquals(subject,jsonPath.getString("data["+dataIndex+"].subject"));
        assertEquals(status,jsonPath.getInt("data["+dataIndex+"].status"));
        assertEquals(priority,jsonPath.getInt("data["+dataIndex+"].priority"));
        assertEquals(last_reply,jsonPath.getString("data["+dataIndex+"].last_reply"));
        assertEquals(created_at,jsonPath.getString("data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data["+dataIndex+"].updated_at"));
    }
    //***************************************************************************************************

    //********************************* api/tickets/details/{{id}} **************************************
    @Given("The API user records the response from the api tickets details endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_tickets_details_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("The API user verifies the content of the data in the response body which includes {int}, {int}, {string}, {string}, {string}, {string}, {int}, {int}, {string}, {string}, {string}")
    public void the_apı_user_verifies_the_content_of_the_data_in_the_response_body_which_includes(int id, int user_id, String name, String email, String ticket, String subject, int status, int priority, String last_reply, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(id,jsonPath.getInt("data[0].id"));
        assertEquals(user_id,jsonPath.getInt("data[0].user_id"));
        assertEquals(name,jsonPath.getString("data[0].name"));
        assertEquals(email,jsonPath.getString("data[0].email"));
        assertEquals(ticket,jsonPath.getString("data[0].ticket"));
        assertEquals(subject,jsonPath.getString("data[0].subject"));
        assertEquals(status,jsonPath.getInt("data[0].status"));
        assertEquals(priority,jsonPath.getInt("data[0].priority"));
        assertEquals(last_reply,jsonPath.getString("data[0].last_reply"));
        assertEquals(created_at,jsonPath.getString("data[0].created_at"));
        assertEquals(updated_at,jsonPath.getString("data[0].updated_at"));
    }
    //***************************************************************************************************

    //************************************* api/tickets/pending *****************************************
    @Given("The API user records the response from the api tickets pending endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_tickets_pending_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //************************************* api/tickets/closed ******************************************
    @Given("The API user records the response from the api tickets closed endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_tickets_closed_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //************************************ api/tickets/answered *****************************************
    @Given("The API user records the response from the api tickets answered endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_tickets_answered_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //********************************** api/tickets/delete/{{id}} **************************************
    @Given("The API user records the response from the api tickets delete endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_tickets_delete_endpoint_with_valid_authorization_information() {
        ReusableMethods.deleteResponse("admin");
    }

    @Then("The API user records the response from the api tickets delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserRecordsTheResponseFromTheApiTicketsDeleteEndpointWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        assertTrue(ReusableMethods.tryCatchDelete().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //*************************************** api/loans/list ********************************************
    @Given("The API user records the response from the api loans list endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_list_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("Verify the information of the one with the index {int} in the API user response body: {string}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {int}, {string}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, String loan_number, int user_id, int plan_id, String amount, String per_installment, int installment_interval, int delay_value, String charge_per_installment, String delay_charge, int given_installment, int total_installment, int status, String approved_at, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(loan_number,jsonPath.getString("data.data["+dataIndex+"].loan_number"));
        assertEquals(user_id,jsonPath.getInt("data.data["+dataIndex+"].user_id"));
        assertEquals(plan_id,jsonPath.getInt("data.data["+dataIndex+"].plan_id"));
        assertEquals(amount,jsonPath.getString("data.data["+dataIndex+"].amount"));
        assertEquals(per_installment,jsonPath.getString("data.data["+dataIndex+"].per_installment"));
        assertEquals(installment_interval,jsonPath.getInt("data.data["+dataIndex+"].installment_interval"));
        assertEquals(delay_value,jsonPath.getInt("data.data["+dataIndex+"].delay_value"));
        assertEquals(charge_per_installment,jsonPath.getString("data.data["+dataIndex+"].charge_per_installment"));
        assertEquals(delay_charge,jsonPath.getString("data.data["+dataIndex+"].delay_charge"));
        assertEquals(given_installment,jsonPath.getInt("data.data["+dataIndex+"].given_installment"));
        assertEquals(total_installment,jsonPath.getInt("data.data["+dataIndex+"].total_installment"));
        assertEquals(status,jsonPath.getInt("data.data["+dataIndex+"].status"));
        assertEquals(approved_at,jsonPath.getString("data.data["+dataIndex+"].approved_at"));
        assertEquals(created_at,jsonPath.getString("data.data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data.data["+dataIndex+"].updated_at"));

        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].admin_feedback"));
        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].due_notification_sent"));
    }
    //***************************************************************************************************

    //********************************* api/loans/details/{{id}} ****************************************
    @Given("The API user records the response from the api loans details endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_details_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("The API user verifies the content of the data in the response body which includes {int}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {int}, {string}, {string}, {string}")
    public void the_apı_user_verifies_the_content_of_the_data_in_the_response_body_which_includes(int id, String loan_number, int user_id, int plan_id, String amount, String per_installment, int installment_interval, int delay_value, String charge_per_installment, String delay_charge, int given_installment, int total_installment, int status, String approved_at, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(id,jsonPath.getString("data[0].id"));
        assertEquals(loan_number,jsonPath.getString("data[0].loan_number"));
        assertEquals(user_id,jsonPath.getInt("data[0].user_id"));
        assertEquals(plan_id,jsonPath.getInt("data[0].plan_id"));
        assertEquals(amount,jsonPath.getString("data[0].amount"));
        assertEquals(per_installment,jsonPath.getString("data[0].per_installment"));
        assertEquals(installment_interval,jsonPath.getInt("data[0].installment_interval"));
        assertEquals(delay_value,jsonPath.getInt("data[0].delay_value"));
        assertEquals(charge_per_installment,jsonPath.getString("data[0].charge_per_installment"));
        assertEquals(delay_charge,jsonPath.getString("data[0].delay_charge"));
        assertEquals(given_installment,jsonPath.getInt("data[0].given_installment"));
        assertEquals(total_installment,jsonPath.getInt("data[0].total_installment"));
        assertEquals(status,jsonPath.getInt("data[0].status"));
        assertEquals(approved_at,jsonPath.getString("data[0].approved_at"));
        assertEquals(created_at,jsonPath.getString("data[0].created_at"));
        assertEquals(updated_at,jsonPath.getString("data[0].updated_at"));

        assertEquals(null,jsonPath.get("data[0].admin_feedback"));
        assertEquals(null,jsonPath.get("data[0].due_notification_sent"));
    }
    //***************************************************************************************************

    //************************************ api/loans/pending ********************************************
    @Given("The API user records the response from the api loans pending endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_pending_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("Verify the information of the one with the index {int} in the API user response body: {string}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {int}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, String loan_number, int user_id, int plan_id, String amount, String per_installment, int installment_interval, int delay_value, String charge_per_installment, String delay_charge, int given_installment, int total_installment, int status, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(loan_number,jsonPath.getString("data.data["+dataIndex+"].loan_number"));
        assertEquals(user_id,jsonPath.getInt("data.data["+dataIndex+"].user_id"));
        assertEquals(plan_id,jsonPath.getInt("data.data["+dataIndex+"].plan_id"));
        assertEquals(amount,jsonPath.getString("data.data["+dataIndex+"].amount"));
        assertEquals(per_installment,jsonPath.getString("data.data["+dataIndex+"].per_installment"));
        assertEquals(installment_interval,jsonPath.getInt("data.data["+dataIndex+"].installment_interval"));
        assertEquals(delay_value,jsonPath.getInt("data.data["+dataIndex+"].delay_value"));
        assertEquals(charge_per_installment,jsonPath.getString("data.data["+dataIndex+"].charge_per_installment"));
        assertEquals(delay_charge,jsonPath.getString("data.data["+dataIndex+"].delay_charge"));
        assertEquals(given_installment,jsonPath.getInt("data.data["+dataIndex+"].given_installment"));
        assertEquals(total_installment,jsonPath.getInt("data.data["+dataIndex+"].total_installment"));
        assertEquals(status,jsonPath.getInt("data.data["+dataIndex+"].status"));
        assertEquals(created_at,jsonPath.getString("data.data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data.data["+dataIndex+"].updated_at"));

        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].admin_feedback"));
        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].due_notification_sent")); jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(loan_number,jsonPath.getString("data.data["+dataIndex+"].loan_number"));
        assertEquals(user_id,jsonPath.getInt("data.data["+dataIndex+"].user_id"));
        assertEquals(plan_id,jsonPath.getInt("data.data["+dataIndex+"].plan_id"));
        assertEquals(amount,jsonPath.getString("data.data["+dataIndex+"].amount"));
        assertEquals(per_installment,jsonPath.getString("data.data["+dataIndex+"].per_installment"));
        assertEquals(installment_interval,jsonPath.getInt("data.data["+dataIndex+"].installment_interval"));
        assertEquals(delay_value,jsonPath.getInt("data.data["+dataIndex+"].delay_value"));
        assertEquals(charge_per_installment,jsonPath.getString("data.data["+dataIndex+"].charge_per_installment"));
        assertEquals(delay_charge,jsonPath.getString("data.data["+dataIndex+"].delay_charge"));
        assertEquals(given_installment,jsonPath.getInt("data.data["+dataIndex+"].given_installment"));
        assertEquals(total_installment,jsonPath.getInt("data.data["+dataIndex+"].total_installment"));
        assertEquals(status,jsonPath.getInt("data.data["+dataIndex+"].status"));
        assertEquals(created_at,jsonPath.getString("data.data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data.data["+dataIndex+"].updated_at"));

        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].admin_feedback"));
        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].due_notification_sent"));
        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].approved_at"));
    }
    //***************************************************************************************************

    //************************************ api/loans/running ********************************************
    @Given("The API user records the response from the api loans running endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_running_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //************************************** api/loans/paid *********************************************
    @Given("The API user records the response from the api loans paid endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_paid_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //************************************ api/loans/rejected *******************************************
    @Given("The API user records the response from the api loans rejected endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_rejected_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    //***************************************************************************************************

    //****************************** api/loans/installments/{{id}} **************************************
    @Given("The API user records the response from the api loans installments endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_installments_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("Verify the information of the one with the index {int} in the API user response body: {int}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, int loan_id, String delay_charge, String installment_date) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(loan_id,jsonPath.getInt("data.data["+dataIndex+"].loan_id"));
        assertEquals(delay_charge,jsonPath.getString("data.data["+dataIndex+"].delay_charge"));
        assertEquals(installment_date,jsonPath.getString("data.data["+dataIndex+"].installment_date"));

        assertEquals(null,jsonPath.get("data.data["+dataIndex+"].given_at"));
    }
    //***************************************************************************************************

    //********************************* api/loans/approve/{{id}} ****************************************
    @Given("The API user records the response from the api loans approve endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_approve_endpoint_with_valid_authorization_information() {
        ReusableMethods.patchResponse("admin");
    }
    @Then("The API user records the response from the api loans approve endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void the_apı_user_records_the_response_from_the_api_loans_approve_endpoint_with_invalid_authorization_information_verifies_that_the_status_code_is_and_confirms_that_the_error_information_is_unauthorized() {
        assertTrue(ReusableMethods.tryCatchPatch().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //********************************* api/loans/reject/{{id}} *****************************************
    @Given("The API user prepares a POST request containing the correct data to send to the api loans reject endpoint")
    public void the_apı_user_prepares_a_post_request_containing_the_correct_data_to_send_to_the_api_loans_reject_endpoint() {
        reqBodyPojo=new LoansRejectPojo("Bank info is wrong.");
    }
    @When("The API user sends a POST request and records the response returned from the api loans reject endpoint with valid authorization information")
    public void the_apı_user_sends_a_post_request_and_records_the_response_returned_from_the_api_loans_reject_endpoint_with_valid_authorization_information() {
        ReusableMethods.postResponse("admin",reqBodyPojo);
    }

    @And("The API user prepares a POST request without data to send to the api loans reject endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheApiLoansRejectEndpoint() {
        reqBodyPojo=new LoansRejectPojo();
    }

    @When("The API user sends a POST request and records the response returned from the api loans reject endpoint with invalid authorization information")
    public void theAPIUserSendsAPOSTRequestAndRecordsTheResponseReturnedFromTheApiLoansRejectEndpointWithInvalidAuthorizationInformation() {
        ReusableMethods.postResponse("invalidToken",reqBodyPojo);
    }
    //***************************************************************************************************

    //********************************* api/loans/delete/{{id}} *****************************************
    @Given("The API user records the response from the api loans delete endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_loans_delete_endpoint_with_valid_authorization_information() {
        ReusableMethods.deleteResponse("admin");
    }
    @Then("The API user records the response from the api loans delete endpoint with invalid authorization information verifies that the status code is {string} and confirms that the error information is Unauthorized")
    public void the_apı_user_records_the_response_from_the_api_loans_delete_endpoint_with_invalid_authorization_information_verifies_that_the_status_code_is_and_confirms_that_the_error_information_is_unauthorized(String string) {
        assertTrue(ReusableMethods.tryCatchDelete().contains("status code: 401, reason phrase: Unauthorized"));
    }
    //***************************************************************************************************

    //************************************* api/deposit/list ********************************************
    @Given("The API user records the response from the api deposit list endpoint with valid authorization information")
    public void the_apı_user_records_the_response_from_the_api_deposit_list_endpoint_with_valid_authorization_information() {
        ReusableMethods.getResponse("admin");
    }
    @Then("Verify the information of the one with the index {int} in the API user response body: {int}, {int}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {int}, {int}, {int}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_index_in_the_apı_user_response_body(int dataIndex, int user_id, int method_code, String amount, String method_currency, String charge, String rate, String final_amo, String btc_amo, String trx, int payment_try, int status, int from_api, String created_at, String updated_at) {
        jsonPath=ReusableMethods.response.jsonPath();

        assertEquals(user_id,jsonPath.getInt("data.data["+dataIndex+"].user_id"));
        assertEquals(method_code,jsonPath.getInt("data.data["+dataIndex+"].method_code"));
        assertEquals(amount,jsonPath.getString("data.data["+dataIndex+"].amount"));
        assertEquals(method_currency,jsonPath.getString("data.data["+dataIndex+"].method_currency"));
        assertEquals(charge,jsonPath.getString("data.data["+dataIndex+"].charge"));
        assertEquals(rate,jsonPath.getString("data.data["+dataIndex+"].rate"));
        assertEquals(final_amo,jsonPath.getString("data.data["+dataIndex+"].final_amo"));
        assertEquals(btc_amo,jsonPath.getString("data.data["+dataIndex+"].btc_amo"));
        assertEquals(trx,jsonPath.getString("data.data["+dataIndex+"].trx"));
        assertEquals(payment_try,jsonPath.getInt("data.data["+dataIndex+"].payment_try"));
        assertEquals(status,jsonPath.getInt("data.data["+dataIndex+"].status"));
        assertEquals(from_api,jsonPath.getInt("data.data["+dataIndex+"].from_api"));
        assertEquals(created_at,jsonPath.getString("data.data["+dataIndex+"].created_at"));
        assertEquals(updated_at,jsonPath.getString("data.data["+dataIndex+"].updated_at"));

        assertEquals("",jsonPath.getString("data.data["+dataIndex+"].btc_wallet"));
        assertEquals(null,jsonPath.getString("data.data["+dataIndex+"].admin_feedback"));
    }
    //***************************************************************************************************
}
