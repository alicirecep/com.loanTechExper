Feature: As an administrator, I want to be able to access users with balances through the API connection.

  Scenario: When a GET request with valid authorization information is sent to the 'api/users/with-balance'
  endpoint, it should be verified that the returned status code is 200, and the remark information in the
  response indicates "success"

    Given The API user sets "api/users/with-balance" path parameters
    And The API user saves the response from the api users with-balance endpoint with valid authorization information
    #Apı kulanıcısı api users with-balance endpointinden donen responseı geçerli authorization bilgisi ile kaydeder
    Then The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"


  Scenario: When an invalid authorization information is used to send a GET request to the
  'api/users/with-balance' endpoint, it should be verified that the returned status code is 401,
  and the error information in the response body indicates "Unauthorized request"

    Given The API user sets "api/users/with-balance" path parameters
    Then The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized


  Scenario Outline: The information returned in the response with ID(x) should be verified, including
  firstname, lastname, username, image, email, country_code, mobile, ref_by, balance

    Given The API user sets "api/users/with-balance" path parameters
    And The API user saves the response from the api users with-balance endpoint with valid authorization information
    Then Verify the information of the one with the index <dataIndex> in the API user response body: "<firstname>", "<lastname>", "<username>", "<email>", "<country_code>", "<mobile>", <ref_by>, "<balance>"

    Examples:
      | dataIndex | firstname | lastname | username  | email              | country_code | mobile       | ref_by | balance         |
      | 0         | kaan      | user1    | kaanuser1 | kaanuser1@abcd.com | TR           | 903456789123 | 0      | 100000.00000000 |
